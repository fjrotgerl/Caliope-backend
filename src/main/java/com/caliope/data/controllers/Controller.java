package com.caliope.data.controllers;

import com.caliope.data.entities.Usuario;
import com.caliope.data.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/getUsuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios() {
        return (List<Usuario>) this.usuarioRepository.findAll();
    }
}
