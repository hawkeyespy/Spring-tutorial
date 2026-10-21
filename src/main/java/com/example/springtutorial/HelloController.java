package com.example.springtutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Greeting hello() {
        return new Greeting("Hello, World!");
    }

    @GetMapping("/hi")
    public Greeting hi() {
        return new Greeting("Hi, World!");
    }

    @GetMapping("/hey")
    public Greeting hey() {
        return new Greeting("Hey, World!");
    }

    public record Greeting(String message) {
    }
}
