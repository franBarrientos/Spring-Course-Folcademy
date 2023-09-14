package com.saludo.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class SaludoController {

    @GetMapping("/hello")
    public String hello(){
        return "Hi my name is fran and this is my first API!";
    }
    @GetMapping("/hola")
    public String hola(){
        return "Hola soy fran y esta es mi primera API!";
    }

    @PostMapping("/goodbye")
    public String goodbye(){
        return "Goodbye!!";
    }
    @PostMapping("/hastaLuego")
    public String hastaLuego(){
        return "hasta luego!!";
    }





}
