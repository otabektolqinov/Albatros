package com.example.albartros.utils;

import com.example.albartros.config.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3Service s3Service;

    public String handleFileUpload(MultipartFile file) throws IOException {
        validateFile(file);
        String generatedName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        return s3Service.uploadFile(
                file.getBytes(),
                file.getContentType(),
                generatedName);
    }


    public void validateFile(MultipartFile file) {
        if (file.getSize() > 10 * 1024 * 1024) { // 10 MB limit
            throw new RuntimeException("File size exceeds the maximum limit");
        }

    }
}
