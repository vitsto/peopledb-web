package com.neutrinosys.peopledbweb.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class FileStorageRepository {
    @Value("${STORAGE_FOLDER}")
    private String storageFolder;
    public void save(String originalFilename, InputStream inputStream) {
        try {
            Path filePath = Path.of(storageFolder).resolve(originalFilename).normalize();
            BufferedInputStream fis = new BufferedInputStream(inputStream);
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filePath.toFile()));
            fos.write(fis.readAllBytes());
//            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
