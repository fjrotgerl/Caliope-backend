package com.caliope.data.repositories;

import com.caliope.data.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findUsuarioByGoogleId(String id);
    Usuario findUsuarioByUsername(String username);

}
