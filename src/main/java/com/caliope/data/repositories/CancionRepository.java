package com.caliope.data.repositories;

import com.caliope.data.entities.Cancion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CancionRepository extends CrudRepository<Cancion, Integer> {

    Optional<Cancion> findById(Integer integer);

}
