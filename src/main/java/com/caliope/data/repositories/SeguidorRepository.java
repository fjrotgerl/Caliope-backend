package com.caliope.data.repositories;

import com.caliope.data.entities.Seguidor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeguidorRepository extends CrudRepository<Seguidor, Integer> {

    @Query(value = "SELECT usuario_seguidor_fk FROM seguidor WHERE usuario_fk = :username", nativeQuery = true)
    List<String> getSeguidoresDeUsuario(String username);

    @Query(value = "SELECT usuario_fk FROM seguidor WHERE usuario_seguidor_fk = :username", nativeQuery = true)
    List<String> getSeguidosDeUsuario(String username);

}
