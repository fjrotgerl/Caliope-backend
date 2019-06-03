package com.caliope.data.repositories;

import com.caliope.data.entities.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findUsuarioByUsername(String username);

    Usuario findUsuarioByEmail(String email);

    Usuario findUsuarioByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET usuario_token = :token WHERE username = :username ;", nativeQuery = true)
    int setUsuarioToken(@Param("token") String token, @Param("username") String username);


    
}
