package com.caliope.data.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GestorArchivos {

    @Value("${fichero.subida}")
    private String directorio;

    public void subir(MultipartFile file, String username) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String directorioSinArchivo = this.directorio + "//" + username + "//mis_canciones//";
            Path path = Paths.get(directorioSinArchivo);

            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            path = Paths.get(directorioSinArchivo + file.getOriginalFilename());

            Files.write(path,bytes);
        }
    }

}
