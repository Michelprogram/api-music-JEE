package com.example.ApiMusic21.web.controller;

import com.example.ApiMusic21.dao.AlbumDao;
import com.example.ApiMusic21.model.Album;
import com.example.ApiMusic21.model.Artist;
import com.example.ApiMusic21.web.execptions.ItemIntrouvable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/Album")
public class AlbumController {

    @Autowired
    private AlbumDao albumDao;

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
    public List<Album> findByPrice(@PathVariable double price){
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

}
