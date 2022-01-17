package com.example.ApiMusic21.web.controller;

import com.example.ApiMusic21.dao.GenreDao;
import com.example.ApiMusic21.model.Genre;
import com.example.ApiMusic21.web.execptions.ItemIntrouvable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/Genres")
public class GenreController {

    @Autowired
    private GenreDao genreDao;

    @GetMapping(value = "/")
    public List<Genre> getAllGenres(){
        return genreDao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Genre getGenreById(@PathVariable int id){
        Genre  g = genreDao.findById(id);

        if(g==null) throw new ItemIntrouvable("Le genre avec l'id " + id + " est introuvable.");

        return g;
    }

    @GetMapping(value = "/name/{name}")
    public List<Genre> getGenreByName(@PathVariable String name){
        name = name.toLowerCase(Locale.ROOT);
        return genreDao.findByNameContaining(name);
    }

    @PostMapping(value = "/")
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

    @DeleteMapping(value = "/{id}")
    public Genre removeGenre(@PathVariable int id){
        Genre g = genreDao.findById(id);

        if (g == null)
            throw new ItemIntrouvable("Le genre avec l'id " + id + " est introuvable. Il ne peut être supprimé.");

        genreDao.delete(g);

        return g;
    }

    @PutMapping(value = "/{id}")
    public Genre updateGenre(@PathVariable int id, @RequestBody Genre genre){
        Genre g = genreDao.findById(id);

        if (g == null)
            throw new ItemIntrouvable("Le genre avec l'id " + id + " est introuvable.");

        g.setName(genre.getName());

        genreDao.save(g);
        return g;

    }
}

