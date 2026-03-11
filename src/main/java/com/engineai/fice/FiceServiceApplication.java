package com.engineai.fice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class FiceServiceApplication {

    public static void main(String[] args) {

        // 👇 ESTA LINEA ARREGLA LA HORA
        TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));

        SpringApplication.run(FiceServiceApplication.class, args);
    }

}