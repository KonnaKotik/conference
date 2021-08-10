package ru.itis.conference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConferenceApplication {

    private static ConfigurableApplicationContext context;


    public static void main(String[] args) {
        SpringApplication.run(ConferenceApplication.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

}
