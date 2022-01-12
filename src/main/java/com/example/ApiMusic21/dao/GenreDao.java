package com.example.ApiMusic21.dao;

import com.example.ApiMusic21.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreDao extends JpaRepository<Genre, Integer> {

    Genre findById(int id);

    List<Genre> findByNameContaining(String name);
}
