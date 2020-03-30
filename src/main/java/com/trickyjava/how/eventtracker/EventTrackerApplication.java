package com.trickyjava.how.eventtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EventTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventTrackerApplication.class, args);
    }

}
