package com.caliope.data.controllers;

import com.caliope.data.services.GestorArchivos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    private GestorArchivos gestorArchivos;

    public Controller (GestorArchivos gestorArchivos) {
        this.gestorArchivos = gestorArchivos;
    }

    @RequestMapping(value = "/subirCancion", method = RequestMethod.POST)
    public ResponseEntity<?> subirCancion(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
        }

        try {
            this.gestorArchivos.subir(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);

    }
}
