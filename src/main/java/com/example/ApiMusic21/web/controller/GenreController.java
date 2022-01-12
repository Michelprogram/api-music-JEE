package com.example.ApiMusic21.web.controller;

import com.example.ApiMusic21.dao.GenreDao;
import com.example.ApiMusic21.model.Genre;
import com.example.ApiMusic21.web.execptions.ArtisteIntrouvable;
import com.example.ApiMusic21.web.execptions.GenreIntrouvable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class GenreController {

    @Autowired
    private GenreDao genreDao;

    @GetMapping(value = "/Genres")
    public List<Genre> getAllGenres(){
        return genreDao.findAll();
    }

    @GetMapping(value = "/Genres/id/{id}")
    public Genre getGenreById(@PathVariable int id){
        Genre  g = genreDao.findById(id);

        if(g==null) throw new GenreIntrouvable("Le genre avec l'id " + id + " est introuvable.");

        return g;
    }

    @GetMapping(value = "/Genres/name/{name}")
    public List<Genre> getGenreByName(@PathVariable String name){
        name = name.toLowerCase(Locale.ROOT);
        return genreDao.findByNameContaining(name);
    }

    @PostMapping(value = "/Genres")
    public ResponseEntity<Void> addGenre(@RequestBody Genre genre){

        Genre g = genreDao.save(genre);

        if (g == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(g.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/Genres/id/{id}")
    public Genre removeGenre(@PathVariable int id){
        Genre g = genreDao.findById(id);

        if (g == null)
            throw new GenreIntrouvable("Le genre avec l'id " + id + " est introuvable. Il ne peut être supprimé.");

        genreDao.delete(g);

        return g;
    }

    @PutMapping(value = "/Genres/id/{id}")
    public Genre updateGenre(@PathVariable int id, @RequestBody Genre genre){
        Genre g = genreDao.findById(id);

        if (g == null)
            throw new GenreIntrouvable("Le genre avec l'id " + id + " est introuvable.");

        g.setName(genre.getName());

        genreDao.save(g);
        return g;

    }
}

