package com.sombra.stoliar.service.impl;

import ch.qos.logback.classic.Logger;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sombra.stoliar.service.ImagesService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImagesServiceImpl implements ImagesService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ImagesServiceImpl.class);

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String saveImage(MultipartFile multipartFile) {
        String url = null;
        try {
            Map result = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
            url = (String) result.get("url");
        } catch (IOException e) {
            logger.error("Fail to upload image to cloudinary", e);
        }
        return url;
    }
}
