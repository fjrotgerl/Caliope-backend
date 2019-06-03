package com.caliope.data.repositories;

import com.caliope.data.entities.Cancion;
import com.caliope.data.entities.PlaylistCanciones;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaylistCancionesRepository extends CrudRepository<PlaylistCanciones, Integer> {

   @Query(value = "SELECT Cancion.* FROM playlist_canciones PlaylistC " +
            "INNER JOIN cancion Cancion " +
            "INNER JOIN playlist Playlist " +
            "INNER JOIN usuario Usuario " +
            "WHERE PlaylistC.cancion_fk = Cancion.id AND PlaylistC.playlist_fk = Playlist.id AND Playlist.usuario_fk = usuario.username " +
            "AND Usuario.username = :username " +
            "AND Playlist.nombre = :nombrePlaylist ;", nativeQuery = true)
    List<Cancion> getCancionesFromPlaylist(String username, String nombrePlaylist);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO playlist_canciones (playlist_fk, cancion_fk) VALUES (:playlistId, :cancionId)", nativeQuery = true)
    Integer addSongToPlaylist(@Param("playlistId") Integer playlistId, @Param("cancionId") Integer cancionId);
}
