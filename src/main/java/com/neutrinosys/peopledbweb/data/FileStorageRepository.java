package com.neutrinosys.peopledbweb.data;

import com.neutrinosys.peopledbweb.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Repository
public class FileStorageRepository {
    @Value("${STORAGE_FOLDER}")
//    @Value("${path.to.data.file}")
    private String storageFolder;

//    public void save(String originalFilename, InputStream inputStream) {
//        try {
//            Path filePath = Path.of(storageFolder).resolve(UUID.randomUUID() + "/" + originalFilename).normalize();
//            Files.copy(inputStream, filePath);
//            inputStream.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void save(String originalFilename, InputStream inputStream) {
        Path dir = Paths.get(storageFolder);
        Path file;
        try {
//            if (!Files.exists(dir)) {
//                Files.createDirectory(dir);
//            }
            file = Files.createFile(Paths.get(dir.toString(), originalFilename));
            BufferedInputStream fis = new BufferedInputStream(inputStream);
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file.toFile()));
            fos.write(fis.readAllBytes());
            fis.close(); // no error
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }

    public Resource findByName(String filename) {
        try {
            Path filePath = Path.of(storageFolder).resolve(filename).normalize();
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new StorageException(e);
        }
    }
}
