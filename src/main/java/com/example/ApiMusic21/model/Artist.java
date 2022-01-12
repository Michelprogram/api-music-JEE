package com.example.ApiMusic21.model;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
public class Artist {

    @Id
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String pays;

    public Artist(){

    }

    public Artist(Integer id, String name, String pays){
        this.id = id;
        this.name = name;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

}
