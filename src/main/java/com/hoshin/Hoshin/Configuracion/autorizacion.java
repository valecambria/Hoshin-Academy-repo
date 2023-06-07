package com.hoshin.Hoshin.Configuracion;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration

public class autorizacion extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests() // si quito esta linea TODOS SE AUTORIZAN( no hay restricciones/seguridad)

                .antMatchers("/rest/**", "/h2-console/**", "/index/**").hasAuthority("ADMIN")
                .antMatchers("/pagoYkyc.html", "/improvisado.html").hasAuthority("CLIENTE")
                .antMatchers(HttpMethod.POST, " /api/clientes" ).hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/index.html", "/register.html" , "/login.html").permitAll();


        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("contraseña")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");


        http.csrf().disable();


        // deshabilita frameOptions para que se pueda acceder a h2-console

        http.headers().frameOptions().disable();

        // si el usuario no está autenticado , envíe una respuesta de falla de autenticación

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // si el inicio de sesión es exitoso, borra las banderas que solicitan autenticación

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));



        // si falla el inicio de sesión,  envía una respuesta de falla de autenticación

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        //  si el cierre de sesión es exitoso,  envíe una respuesta exitosa

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());


    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
    }

}
