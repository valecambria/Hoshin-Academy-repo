package com.hoshin.Hoshin.servicios;

import com.hoshin.Hoshin.DTO.clienteDTO;
import com.hoshin.Hoshin.models.cliente;

import java.util.List;

public interface serviciosCliente {

    public List<clienteDTO> getClienteDTO();

    public clienteDTO getClienteId(long id);

    public cliente findByEmail(String email);

    public void guardarCliente(cliente cliente);


}
