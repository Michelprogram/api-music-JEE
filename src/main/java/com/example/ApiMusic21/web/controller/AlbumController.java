package com.example.ApiMusic21.web.controller;

import com.example.ApiMusic21.dao.AlbumDao;
import com.example.ApiMusic21.dao.ArtistDao;
import com.example.ApiMusic21.dao.GenreDao;
import com.example.ApiMusic21.model.Album;
import com.example.ApiMusic21.model.Artist;
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
@RequestMapping("/Album")
public class AlbumController {

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private GenreDao genreDao;

    @GetMapping(value = "/")
    public List<Album> getAllAlbum(){
        return albumDao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Album getAlbumId(@PathVariable int id){
        Album a = albumDao.findById(id);

        if( a == null) throw new ItemIntrouvable("L'album avec l'id " + id + " est introuvable.");

        return a;
    }

    @GetMapping(value = "/name/{name}")
    public List<Album> findByNamme(@PathVariable String name){
        name = name.toLowerCase(Locale.ROOT);
        return albumDao.findByNameContaining(name);
    }

    @GetMapping(value = "/price/{price}")
    public List<Album> findByPrice(@PathVariable float price){
        return albumDao.albumByPrice(price);
    }

    @GetMapping(value = "/year/{year}")
    public List<Album> findByYear(@PathVariable String year){
        return albumDao.albumByYear(year);
    }

    @GetMapping(value = "/tracks/{track}")
    public List<Album> findByTrack(@PathVariable int track){
        return albumDao.albumByTrack(track);
    }

    @PostMapping(value = "/add/{artistName}")
    public Album addAlbum(@PathVariable String artistName, @RequestBody Album album){

        Artist artist = artistDao.findByName(artistName.toLowerCase(Locale.ROOT));

        if (artist == null)
            throw new ItemIntrouvable("L'artiste avec le nom "+ artistName.toLowerCase(Locale.ROOT)+" n'éxiste pas dans la base");

        album.setArtist(artist);

        Album a = albumDao.save(album);


        return a;
    }

    @PutMapping(value = "/addGenre/{idAlbum}/{idGenre}")
    public Album addGenre(@PathVariable("idAlbum") int idAlbum,@PathVariable("idGenre") int idGenre){

        Album album = albumDao.findById(idAlbum);
        Genre genre = genreDao.findById(idGenre);

        if (album == null || genre == null)
            throw new ItemIntrouvable("L'album avec l'id "+ idAlbum +" n'éxiste pas. Ou le genre avec l'id "+idGenre+"n'éxiste pas.");

        album.getGenre().add(genre);

        Album update = albumDao.save(album);

        if (update == null)
            throw new ItemIntrouvable("Impossible de d'ajouter à l'album "+album.getName()+" le genre"+genre.getName());

        return update;
    }
}
