package com.example.ApiMusic21.web.execptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemIntrouvable extends RuntimeException{
    public ItemIntrouvable(String message){
        super(message);
    }
}
