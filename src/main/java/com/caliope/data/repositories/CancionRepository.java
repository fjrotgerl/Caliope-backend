package com.caliope.data.repositories;

import com.caliope.data.entities.Cancion;
import org.springframework.data.repository.CrudRepository;

public interface CancionRepository extends CrudRepository<Cancion, Integer> {

}
