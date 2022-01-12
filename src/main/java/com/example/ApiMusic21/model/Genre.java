package com.example.ApiMusic21.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genre {

    @Id
    private Integer id;

    @NotNull
    private String name;

    public Genre(){}

    public Genre(Integer id, String name){
        this.id = id;
        this.name = name;
    }

}
