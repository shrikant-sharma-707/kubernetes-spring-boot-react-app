package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping(path = "/findAllStudent", produces = "application/json")
    List<Student> findAllStudent() {
        Faker faker = new Faker();
        List<Student> studentList = IntStream.rangeClosed(1, 10).mapToObj(value -> new Student(value, faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.phoneNumber().phoneNumber())).collect(Collectors.toList());
        return studentList;
    }

    @GetMapping(path = "/", produces = "application/json")
    ResponseEntity<Map<String, String>> info() {
        Map<String, String> map = new HashMap<>();
        map.put("version", "v1 from backend");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

record Student(int rollNo, String firstName, String lastName, String email, String phoneNumber) {
}