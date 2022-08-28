package com.example.springbootdemoproject.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository){
      return args -> {
        Student martin = new Student(
                "martin",
                "martion@outlook.com",
                LocalDate.of(2000,11,8)
        );
          Student may = new Student(
                  "may",
                  "may@outlook.com",
                  LocalDate.of(2009,11,8)
          );
           studentRepository.saveAll(List.of(martin,may));

      };
    };

}
