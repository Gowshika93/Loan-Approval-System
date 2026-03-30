package com.bank.loan.document_notification_service.service;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public class StorageService {

    @Value("${storage.location}")
    private String storageLocation;

    @PostConstruct
    public void init() throws IOException {
        Path root = Paths.get(storageLocation);
        if (!Files.exists(root)) Files.createDirectories(root);
    }

    public String store(MultipartFile file, Long documentId) throws IOException {
        String filename = documentId + "_" + Path.of(file.getOriginalFilename()).getFileName().toString();
        Path dest = Paths.get(storageLocation).resolve(filename);
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
        }
        return dest.toAbsolutePath().toString();
    }
}
