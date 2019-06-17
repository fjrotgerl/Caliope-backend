package com.caliope.data.repositories;

import com.caliope.data.entities.Cancion;
import com.caliope.data.entities.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findUsuarioByUsername(String username);

    Usuario findUsuarioByEmail(String email);

    @Query(value = "SELECT * FROM `usuario` WHERE `username` LIKE %:info%", nativeQuery = true)
    List<Usuario> getUsersThatContains(@Param("info") String info);

    @Modifying
    @Transactional
    void deleteUsuarioByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET username = :newUserId, email = :userEmail, nombre = :userName, apellidos = :userSurename WHERE username = :oldUserId", nativeQuery = true)
    void updateUserData(@Param("newUserId") String newUserid, @Param("userEmail") String userEmail, @Param("userName") String userName, @Param("userSurename") String userSurename, @Param("oldUserId") String oldUserId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET contraseña = :newPassword WHERE username = :userId AND contraseña = :oldPassword", nativeQuery = true)
    void updateUserPassword(@Param("newPassword") String newPassword, @Param("oldPassword") String oldPassword, @Param("userId") String userId);
    
}
