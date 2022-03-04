package com.beshton.audio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beshton.audio.entities.*;
import com.beshton.audio.repos.*;

import java.time.LocalDateTime;


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AudioRepository employeeRepository, SoundRepository orderRepository,
                                   MusicRepository saleRepository) {

        return args -> {
            employeeRepository.save(new Audio("Bilbo", "Baggins", "burglar"));
            employeeRepository.save(new Audio("Frodo", "Baggins", "thief"));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

            orderRepository.save(new Sound("MacEmployee Pro", Status.COMPLETED));
            orderRepository.save(new Sound("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
            saleRepository.save(new Music("Mixer", "Casey", "Jian", "Kitchen",
                    20L, "61801", "KitchenAid", "A101010", true,
                    "Almost new", "pending", LocalDateTime.now().toString(), 10898L, 1893L,
                    "/Users/keziah/test.jpg"));
            saleRepository.findAll().forEach(sale -> {
                log.info("Preloaded " + sale);
            });
        };
    }
}