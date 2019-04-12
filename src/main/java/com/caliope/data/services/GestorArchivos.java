package com.caliope.data.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestorArchivos {

    @Value("${fichero.subida}")
    private String directorio;

    public void subir(MultipartFile file, String username) {
        try {
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
            Files.write(path, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean comprobarFormatosValidos(MultipartFile file) {
        List<String> formatos = new ArrayList<>();

        formatos.add("mp3");

        for (String formato : formatos) {
            if (formato.equals(getFileExtension(file))) {
                return true;
            }
        }

        return false;
    }

    private String getFileExtension(MultipartFile file) {

        return FilenameUtils.getExtension(file.getOriginalFilename());

    }

}
