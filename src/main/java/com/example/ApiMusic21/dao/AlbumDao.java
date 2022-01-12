package com.example.ApiMusic21.dao;

import com.example.ApiMusic21.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumDao extends JpaRepository<Album, Integer> {
    Album findById(int id);

    List<Album> findByNameContaining(String name);

    //Find artist with all different genre

    /*
    @Query("SELECT id, numberTrack, name, date, price FROM Album a WHERE a.prix > :prixLimit")
    List<Album> albumByPrice(@Param("prixLimit") int prix);

     */
}
