package com.example.ApiMusic21.dao;

import com.example.ApiMusic21.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistDao extends JpaRepository<Artist, Integer> {

    Artist findById(int id);

    List<Artist> findByPaysContaining(String name);

    List<Artist> findByNameContaining(String name);
}
