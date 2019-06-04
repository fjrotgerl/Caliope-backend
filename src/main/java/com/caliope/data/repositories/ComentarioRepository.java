package com.caliope.data.repositories;

import com.caliope.data.entities.Comentario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComentarioRepository extends CrudRepository<Comentario, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comentario (mensaje, usuario_fk, cancion_fk) VALUES (:mensaje, :userId, :cancionId)", nativeQuery = true)
    void addComentarioACancion(@Param("mensaje") String mensaje, @Param("userId") String userId, @Param("cancionId") Integer cancionId);

    @Query(value = "SELECT mensaje FROM comentario WHERE cancion_fk = :cancionId", nativeQuery = true)
    List<Comentario> getComentariosByCancionId(@Param("cancionId") Integer cancionId);

}
