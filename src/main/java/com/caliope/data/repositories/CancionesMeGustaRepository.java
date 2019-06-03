package com.caliope.data.repositories;

import com.caliope.data.entities.CancionesMeGusta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CancionesMeGustaRepository extends CrudRepository<CancionesMeGusta, Integer> {

    @Query(value = "SELECT cancion_fk FROM canciones_megusta WHERE usuario_fk = :userId", nativeQuery = true)
    List<Integer> getLikedSongsByUserId(@Param("userId") String username);

}
