package com.caliope.data.repositories;

import com.caliope.data.entities.Seguidor;
import com.caliope.data.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeguidorRepository extends CrudRepository<Seguidor, Integer> {

    @Query("SELECT usuario_seguidor_fk FROM seguidor WHERE usuario_fk = :#{#nombreUsuario}")
    List<Usuario> getSeguidoresDeUsuario(@Param("nombreUsuario") String nombreUsuario);

}
