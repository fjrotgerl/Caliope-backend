package com.caliope.data.repositories;

import com.caliope.data.entities.CancionesMeGusta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CancionesMeGustaRepository extends CrudRepository<CancionesMeGusta, Integer> {

    @Query(value = "SELECT cancion_fk FROM canciones_megusta WHERE usuario_fk = :userId", nativeQuery = true)
    List<Integer> getLikedSongsByUserId(@Param("userId") String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO canciones_megusta (cancion_fk, usuario_fk) VALUES (:cancionId, :userId)", nativeQuery = true)
    Integer addNewLikeSong(@Param("userId") String userId, @Param("cancionId") Integer cancionId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM canciones_megusta WHERE usuario_fk = :userId AND cancion_fk = :cancionId", nativeQuery = true)
    int deleteByUsuarioAndCancion(String userId, Integer cancionId);

    @Query(value = "SELECT usuario_fk FROM canciones_megusta WHERE cancion_fk = :cancionId AND usuario_fk = :userId",nativeQuery = true)
    String isThisSongLikedByTheUser(@Param("cancionId") Integer cancionId, @Param("userId") String userId);


}
