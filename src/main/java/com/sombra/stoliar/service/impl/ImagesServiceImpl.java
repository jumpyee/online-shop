package com.sombra.stoliar.service.impl;

import com.sombra.stoliar.service.ImagesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Value("${app.upload.images.location}")
    private String imagesPath;

    @Override
    public String saveImage(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            Path imagesDir = Paths.get(imagesPath);
            Path imagePath = Files.createTempFile(imagesDir, "item_", "");
            Files.write(imagePath, bytes);
            return imagePath.getFileName().toString();
        } catch (Exception e) {
            //log this
        }
        return null;
    }
}
