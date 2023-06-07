package com.hoshin.Hoshin.Configuracion;

import com.hoshin.Hoshin.models.cliente;
import com.hoshin.Hoshin.servicios.serviciosCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class autenticacion extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    com.hoshin.Hoshin.servicios.serviciosCliente serviciosCliente;

    @Bean
    public PasswordEncoder contraseñaEncriptada() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override

    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(email-> {
            cliente cliente = serviciosCliente.findByEmail(email);

            if (cliente != null) {
                if (cliente.getEmail().contains("ADMIN")) {
                    return new User(cliente.getEmail(), cliente.getContraseña(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                } else {

                    return new User(cliente.getEmail(), cliente.getContraseña(),
                            AuthorityUtils.createAuthorityList("CLIENTE"));
                }
            } else{
                throw new UsernameNotFoundException("Unknown email" + email);
            }


        });
    }

}
