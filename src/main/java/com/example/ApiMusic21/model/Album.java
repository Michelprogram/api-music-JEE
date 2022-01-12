package com.example.ApiMusic21.model;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Album {

    @Id
    private Integer id;

    @NotNull
    private Integer numberTrack;

    @NotNull
    private String name;

    @DateTimeFormat
    private String date;

    public Album(){}

    public Album(Integer id, Integer numberTrack, String name, String date){
        this.id = id;
        this.numberTrack = numberTrack;
        this.name = name;
        this.date = date;

    }

}
