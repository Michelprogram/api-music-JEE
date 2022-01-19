package com.example.ApiMusic21.web.controller;
import org.springframework.web.bind.annotation.*;



@RestController
public class MainController {

    @GetMapping(value = "/")
    public @ResponseBody String HelloWorld(){
        return "Hello world";
    }

}
