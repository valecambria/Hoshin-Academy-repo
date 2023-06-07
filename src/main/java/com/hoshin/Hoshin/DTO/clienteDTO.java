package com.hoshin.Hoshin.DTO;


import com.hoshin.Hoshin.models.cliente;

import java.util.stream.Collectors;

public class clienteDTO {

    private Long  id;
    private String primerNombre;
    private String apellido;
    private String email;

    private Boolean kyc;

    public clienteDTO() {
    }

    public clienteDTO(cliente cliente) { // cliente que clintcontroller me pida ( del cliente del momento me trae tod)
        this.id = cliente.getId();
        this.primerNombre = cliente.getPrimerNombre();
        this.apellido = cliente.getApellido();
        this.email = cliente.getEmail();
        this.kyc = cliente.getKyc();
    }

    public Long getId() {
        return id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getKyc() {
        return kyc;
    }
}
