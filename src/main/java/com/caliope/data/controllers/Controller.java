package com.caliope.data.controllers;

import com.caliope.data.repositories.UsuarioRepository;
import com.caliope.data.services.GestorArchivos;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @RequestMapping(value = "/obtenerCancion/{username}/{nombreCancion}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] obtenerCancion(@PathVariable("username") String username, @PathVariable("nombreCancion") String nombreCancion) {
        return this.gestorArchivos.obtenerCancion(username,nombreCancion);
    }
}
