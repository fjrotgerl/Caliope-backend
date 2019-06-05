package com.caliope.data.repositories;

import com.caliope.data.entities.Seguidor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SeguidorRepository extends CrudRepository<Seguidor, Integer> {

    @Query(value = "SELECT usuario_seguidor_fk FROM seguidor WHERE usuario_fk = :username", nativeQuery = true)
    List<String> getSeguidoresDeUsuario(String username);

    @Query(value = "SELECT usuario_fk FROM seguidor WHERE usuario_seguidor_fk = :username", nativeQuery = true)
    List<String> getSeguidosDeUsuario(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO seguidor (usuario_fk, usuario_seguidor_fk) VALUES (:usuarioSeguido,:usuarioSeguidor)", nativeQuery = true)
    void followUser(@Param("usuarioSeguido") String usuarioSeguido, @Param("usuarioSeguidor") String usuarioSeguidor);

}
