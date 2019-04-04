package com.caliope.data.repositories;

import com.caliope.data.entities.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {

}
