package com.caliope.data.repositories;

import com.caliope.data.entities.Cancion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import java.util.Optional;

public interface CancionRepository extends CrudRepository<Cancion, Integer> {

    @Query(value = "SELECT id FROM cancion WHERE usuario_fk = :userId", nativeQuery = true)
    List<Integer> getSongsFromUserById(@Param("userId") String userId);

    @Override
    Iterable<Cancion> findAllById(Iterable<Integer> iterable);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cancion WHERE id = :cancionId", nativeQuery = true)
    Integer deleteSongById(@Param("cancionId") Integer cancionId);
    Optional<Cancion> findById(Integer integer);

    @Query(value = "SELECT cancion_path FROM cancion WHERE id = :cancionId", nativeQuery = true)
    String getSongFilenameByCancionId(@Param("cancionId") Integer cancionId);

    @Query(value = "SELECT usuario_fk FROM cancion WHERE id = :cancionId", nativeQuery = true)
    String getSongAutorByCancionId(@Param("cancionId") Integer cancionId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cancion (nombre, duracion, cancion_path, usuario_fk, genero_fk, fecha_registro) VALUES (:nombre, :duracion, :cancion_path, :usuario_fk, :genero_fk, :fecha_registro)", nativeQuery = true)
    Integer addCancion(@Param("nombre") String nombre, @Param("duracion") Integer duracion, @Param("cancion_path") String cancion_path, @Param("usuario_fk") String userId, @Param("genero_fk") String genero, @Param("fecha_registro") String fechaRegistro);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cancion SET reproducciones = ((SELECT reproducciones WHERE id = :idCancion) + 1) WHERE id = :idCancion", nativeQuery = true)
    void addOneRepro(@Param("idCancion") Integer idCancion);

}
