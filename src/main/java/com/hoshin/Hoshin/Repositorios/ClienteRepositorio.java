package com.hoshin.Hoshin.Repositorios;
import com.hoshin.Hoshin.models.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ClienteRepositorio extends JpaRepository<cliente, Long> {

    cliente findByEmail(String email);

}
