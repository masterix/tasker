package com.ps.tasks.boundary;

import com.ps.tasks.StorageConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path path;

    public FileSystemStorageService(StorageConfig storageConfig) {
        this.path = Paths.get(storageConfig.getPath());
    }

    @Override
    public String saveFile(Long taskId, MultipartFile file) throws IOException {
        Path targetPath = path.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetPath.toString();
    }

    @Override
    public Resource loadFile(String filename) throws MalformedURLException {
        return new UrlResource(path.resolve(filename).toUri());
    }
}
