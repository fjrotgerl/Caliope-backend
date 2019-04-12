package com.caliope.data.controllers;

import com.caliope.data.repositories.UsuarioRepository;
import com.caliope.data.services.GestorArchivos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Controller
public class Controller {

    private GestorArchivos gestorArchivos;
    private UsuarioRepository usuarioRepository;

    public Controller (GestorArchivos gestorArchivos, UsuarioRepository usuarioRepository) {
        this.gestorArchivos    = gestorArchivos;
        this.usuarioRepository = usuarioRepository;
    }

    @RequestMapping(value = "/subirCancion", method = RequestMethod.POST)
    public ResponseEntity<?> subirCancion(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {

        if (!this.usuarioRepository.findById(username).isPresent()) {
            return new ResponseEntity<Object>("El usuario no se encuentra o no existe", HttpStatus.BAD_REQUEST);
        }

        if (file == null || file.getSize() == 0 || file.getName().equals("") || file.isEmpty()) {
            return new ResponseEntity<Object>("Selecciona un archivo", HttpStatus.BAD_REQUEST);
        }

        if (!this.gestorArchivos.comprobarFormatosValidos(file)) {
            return new ResponseEntity<Object>("Formato incorrecto", HttpStatus.BAD_REQUEST);
        }

        this.gestorArchivos.subir(file,username);

        return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);

    }
}
