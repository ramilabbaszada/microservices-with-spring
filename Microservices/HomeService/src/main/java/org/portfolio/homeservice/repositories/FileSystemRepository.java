package org.portfolio.homeservice.repositories;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Repository
public class FileSystemRepository {
    @Value("${folderPathForImages}")
    private String folderPathForImages;
    private Path destinationFolder;

    @SneakyThrows
    public String createFile(String fileName, byte[] imageData){
        destinationFolder=Paths.get(folderPathForImages);
        Path destinationPath = destinationFolder.resolve(fileName);
        if (!Files.exists(destinationFolder)) {
            Files.createDirectories(destinationFolder);
        }
        Files.write(destinationPath,imageData);
        return fileName;
    }
    @SneakyThrows
    public boolean updateFile(String fileName,byte[] imageData){
        destinationFolder=Paths.get(folderPathForImages);
        Path destinationPath = destinationFolder.resolve(fileName);
        if (Files.exists(destinationPath)) {
            Files.write(destinationPath,imageData);
            return true;
        }
        return false;
    }

    @SneakyThrows
    public boolean deleteFile(String fileName){
        destinationFolder=Paths.get(folderPathForImages);
        Path destinationPath = destinationFolder.resolve(fileName);
        if (Files.exists(destinationPath)) {
            Files.delete(destinationPath);
            return true;
        }
        return false;
    }

    @SneakyThrows
    public byte[] readFileAsByteArray(String fileName){
        destinationFolder=Paths.get(folderPathForImages);
        Path destinationPath = destinationFolder.resolve(fileName);
        if (Files.exists(destinationPath)) {
            return Files.readAllBytes(destinationPath);
        }
        return null;
    }

}
