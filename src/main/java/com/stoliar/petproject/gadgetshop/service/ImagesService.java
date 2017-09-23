package com.stoliar.petproject.gadgetshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImagesService {
    String saveImage(MultipartFile multipartFile);

    boolean isValidImage(MultipartFile image);
}
