package com.example.seminar3REST.helloController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/ciao")
    public String ciao() {
        return "Ciao";
    }
}
