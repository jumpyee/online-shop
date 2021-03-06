package com.stoliar.petproject.gadgetshop.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

    @Value("${com.cloudinary.cloud_name}")
    private String cloudName;
    @Value("${com.cloudinary.api_key}")
    private String apiKey;
    @Value("${com.cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
    }
}
