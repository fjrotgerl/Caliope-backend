package com.caliope.data.repositories;

import com.caliope.data.entities.Playlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {

    @Query(value = "SELECT * FROM playlist WHERE usuario_fk = :usuarioId", nativeQuery = true)
    List<Playlist> getAllUserPlaylists(@Param("usuarioId") String usuarioId);

}
