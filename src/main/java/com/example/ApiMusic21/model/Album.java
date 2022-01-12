package com.example.ApiMusic21.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album {

    @Id
    private Integer id;

    @NotNull
    private Integer numberTrack;

    @NotNull
    private String name;

    @NotNull
    private String date;

    @NotNull
    private Double price;


    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Set<Genre> genre;

    public Album(){}

    public Album(Integer id, Integer numberTrack, String name, String date, Double price, Set<Genre> genre, Artist artist){
        this.id = id;
        this.numberTrack = numberTrack;
        this.name = name;
        this.date = date;
        this.price = price;
        this.genre = genre;
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Set<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberTrack() {
        return numberTrack;
    }

    public void setNumberTrack(Integer numberTrack) {
        this.numberTrack = numberTrack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
