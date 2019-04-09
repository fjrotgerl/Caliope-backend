package com.caliope.data.controllers;

import com.caliope.data.authenticator.local.Autenticacion;
import com.caliope.data.authenticator.local.Estado;
import com.caliope.data.entities.Usuario;
import com.caliope.data.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RequestMapping(value = "/autenticador", method = RequestMethod.POST)
    public Autenticacion authenticate(@RequestBody Usuario usuario) {

        Autenticacion autenticacion = new Autenticacion();

        if (usuario != null && usuario.getUsername() != null && usuario.getContraseña() != null) {

            Optional<Usuario> userOfRepo = this.usuarioRepository.findById(usuario.getUsername());

            if (userOfRepo.isPresent() && userOfRepo.get().getContraseña().equals(usuario.getContraseña())) {

                autenticacion.setUser(userOfRepo.get());

                autenticacion.setStatus(Estado.autenticado);

            } else {

                autenticacion.setStatus(Estado.error);
            }

        } else {

            autenticacion.setStatus(Estado.error);

        }

        return autenticacion;

    }

}
