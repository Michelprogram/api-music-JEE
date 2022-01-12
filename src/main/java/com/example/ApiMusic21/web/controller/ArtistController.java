package com.example.ApiMusic21.web.controller;

import com.example.ApiMusic21.dao.ArtistDao;
import com.example.ApiMusic21.model.Artist;
import com.example.ApiMusic21.model.Genre;
import com.example.ApiMusic21.web.execptions.ArtisteIntrouvable;
import com.example.ApiMusic21.web.execptions.GenreIntrouvable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class ArtistController {

    @Autowired
    private ArtistDao artistDao;

    @GetMapping(value = "/Artistes")
    public List<Artist> getAllArtistes(){
        return artistDao.findAll();
    }

    @GetMapping(value = "/Artistes/id/{id}")
    public Artist getArtisteId(@PathVariable int id){
        Artist a = artistDao.findById(id);

        if( a == null) throw new ArtisteIntrouvable("L'artiste avec l'id " + id + " est introuvable.");

        return a;
    }

    @GetMapping(value = "/Artistes/name/{name}")
    public List<Artist> getArtistByName(@PathVariable String name){
        name = name.toLowerCase(Locale.ROOT);
        return artistDao.findByNameContaining(name);
    }

    @GetMapping(value = "/Artistes/pays/{pays}")
    public List<Artist> getArtistByPays(@PathVariable String pays){
        System.out.println("oui");
        pays = pays.toLowerCase(Locale.ROOT);
        return artistDao.findByPaysContaining(pays);
    }

    @PostMapping(value = "/Artistes")
    public ResponseEntity<Void> addArtist(@RequestBody Artist artist){

        Artist a = artistDao.save(artist);

        if (a == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(a.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/Artistes/id/{id}")
    public Artist removeArtist(@PathVariable int id){
        Artist a = artistDao.findById(id);

        if (a == null) throw new ArtisteIntrouvable("L'artiste avec l'id " + id + " est introuvable.");

        artistDao.delete(a);

        return a;
    }

    @PutMapping(value = "/Artistes/id/{id}")
    public Artist updateGenre(@PathVariable int id, @RequestBody Artist artist){
        Artist a = artistDao.findById(id);

        if (a == null) throw new GenreIntrouvable("Le genre avec l'id " + id + " est introuvable.");

        a.setName(artist.getName());
        a.setPays(artist.getPays());

        artistDao.save(a);

        return a;

    }
}