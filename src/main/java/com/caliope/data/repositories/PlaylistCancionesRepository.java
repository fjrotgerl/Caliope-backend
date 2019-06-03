package com.caliope.data.repositories;

import com.caliope.data.entities.PlaylistCanciones;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaylistCancionesRepository extends CrudRepository<PlaylistCanciones, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO playlist_canciones (playlist_fk, cancion_fk) VALUES (:playlistId, :cancionId)", nativeQuery = true)
    Integer addSongToPlaylist(@Param("playlistId") Integer playlistId, @Param("cancionId") Integer cancionId);

    @Query(value = "SELECT cancion_fk FROM playlist_canciones WHERE playlist_fk = :playlistId", nativeQuery = true)
    List<Integer> getAllPlaylistSong(@Param("playlistId") Integer playlistId);
}
