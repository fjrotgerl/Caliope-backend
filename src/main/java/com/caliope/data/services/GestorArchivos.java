package com.caliope.data.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GestorArchivos {

    @Value("${fichero.subida}")
    private String directorio;

    @Value("${fichero.formatos}")
    private String formatos;

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

    public byte[] obtenerCancion(String username, String nombreCancion) {

        File file = new File("src/main/resources/files/" + username + "/mis_canciones/" + nombreCancion + ".mp3");

        byte[] b = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        }
        catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }

        return b;
    }

    public boolean comprobarFormatosValidos(MultipartFile file) {
        String[] formatosArray = this.formatos.split(",");

        for (String formato : formatosArray) {
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
