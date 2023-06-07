package com.hoshin.Hoshin.servicios.implementaciones;

import com.hoshin.Hoshin.DTO.clienteDTO;
import com.hoshin.Hoshin.Repositorios.ClienteRepositorio;
import com.hoshin.Hoshin.models.cliente;
import com.hoshin.Hoshin.servicios.serviciosCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class serviciosDeImplementacionClientes implements serviciosCliente {

    @Autowired
    ClienteRepositorio clienteRepositorio;


    @Override
    public List<clienteDTO> getClienteDTO() {
        return clienteRepositorio.findAll().stream().map(cliente -> new clienteDTO(cliente)).collect(Collectors.toList());

        }


    @Override
    public clienteDTO getClienteId(long id) {
        return clienteRepositorio.findById(id).map(cliente -> new clienteDTO(cliente)).orElse(null);
    }


    @Override
    public cliente findByEmail(String email) {
        return clienteRepositorio.findByEmail(email);
    }

    @Override
    public void guardarCliente(cliente cliente) {
        clienteRepositorio.save(cliente);
    }
}
