package com.sombra.stoliar.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateService {

    public String currentDate() {
        Date date = new Date();
        return date.toString();
    }

}
