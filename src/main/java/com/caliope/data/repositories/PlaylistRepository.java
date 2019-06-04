package com.caliope.data.repositories;

import com.caliope.data.entities.Playlist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {

    @Query(value = "SELECT * FROM playlist WHERE usuario_fk = :usuarioId", nativeQuery = true)
    List<Playlist> getAllUserPlaylists(@Param("usuarioId") String usuarioId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO playlist (nombre, usuario_fk) VALUES (:nombrePlaylist, :userId)", nativeQuery = true)
    Integer createPlaylist(@Param("nombrePlaylist") String nombrePlaylist, @Param("userId") String userId);

}
