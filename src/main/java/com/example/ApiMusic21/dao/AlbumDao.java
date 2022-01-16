package com.example.ApiMusic21.dao;

import com.example.ApiMusic21.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumDao extends JpaRepository<Album, Integer> {
    Album findById(int id);

    List<Album> findByNameContaining(String name);

    //Find artist with all different genre

    @Query("SELECT a FROM Album a WHERE a.price >= :prixLimit")
    List<Album> albumByPrice(@Param("prixLimit") float prix);

    @Query("SELECT a FROM Album a WHERE a.dateAlbum >= :year")
    List<Album> albumByYear(@Param("year") String year);

    @Query("SELECT a FROM Album a WHERE a.numberTrack >= :track")
    List<Album> albumByTrack(@Param("track") int track);

}
