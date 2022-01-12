package com.example.ApiMusic21.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Artist {

    @Id
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Set<Genre> Genre;

    @NotNull
    private String pays;

    /*
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private ArrayList<Album> album;

     */

    public Artist(){

    }

    public Artist(Integer id, String name, Set<Genre> Genre, String pays){
        this.id = id;
        this.name = name;
        this.Genre = Genre;
        this.pays = pays;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Genre> getGenre() {
        return Genre;
    }

    public void setGenre(Set<Genre> genre) {
        Genre = genre;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    /*
    public ArrayList<Album> getAlbum() {
        return album;
    }

    public void setAlbum(ArrayList<Album> album) {
        this.album = album;
    }
     */
}
