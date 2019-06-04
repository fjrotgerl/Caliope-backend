package com.caliope.data.repositories;

import com.caliope.data.entities.CancionComentarios;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CancionComentariosRepository extends CrudRepository<CancionComentarios, Integer> {



}
