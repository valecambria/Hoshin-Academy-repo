package com.hoshin.Hoshin.Controladores;


import com.hoshin.Hoshin.DTO.clienteDTO;
import com.hoshin.Hoshin.models.cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@RestController
public class clienteControlador {

     @Autowired
     private com.hoshin.Hoshin.servicios.serviciosCliente serviciosCliente;

    @Autowired
    private PasswordEncoder contraseñaEncriptada;


    @RequestMapping("/api/clientes") //@RequestMaping es una peticion asociada a una ruta
    public List<clienteDTO> getClients() {
        return serviciosCliente.getClienteDTO();

    }

    @RequestMapping("/api/clientes/{id}") // end point
    public clienteDTO getClienteId(@PathVariable Long id) { //Trae lo que varia de la ruta , en este caso el id

        return serviciosCliente.getClienteId(id);

    }


    @PostMapping("/api/clientes")

    public ResponseEntity<Object> register(

            @RequestParam String primerNombre, @RequestParam String apellido,

            @RequestParam String email, @RequestParam String contraseña) {


        if (primerNombre.isEmpty()) {
            return new ResponseEntity<>("Name missing", HttpStatus.FORBIDDEN);
        }

        if ( apellido.isEmpty()) {
            return new ResponseEntity<>("LastName missing", HttpStatus.FORBIDDEN);
        }

        if (contraseña.isEmpty()) {
            return new ResponseEntity<>("Email missing", HttpStatus.FORBIDDEN);
        }


        if (serviciosCliente.findByEmail(email) != null) {
            return new ResponseEntity<>("email already in use", HttpStatus.FORBIDDEN);

        }

        cliente nuevoCliente = new cliente(primerNombre, apellido, email, contraseñaEncriptada.encode(contraseña), FALSE);
        serviciosCliente.guardarCliente(nuevoCliente);

        return new ResponseEntity<>( HttpStatus.CREATED);

    }

    @PatchMapping("/api/clientes/current")

    public ResponseEntity<?> desactivarBoton( Authentication authentication
    ){

        cliente clienteautenticado = serviciosCliente.findByEmail(authentication.getName());

        if(clienteautenticado != null){

            clienteautenticado.setKyc(TRUE);
        }

        serviciosCliente.guardarCliente(clienteautenticado);


        return new ResponseEntity<>("cambio hecho", HttpStatus.CREATED);
    }


}
