package com.sombra.stoliar.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImagesService {
    String saveImage(MultipartFile multipartFile);
}
