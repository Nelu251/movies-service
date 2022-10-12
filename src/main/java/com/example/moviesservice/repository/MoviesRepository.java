package com.example.moviesservice.repository;

import com.example.moviesservice.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<MovieEntity, Long> {
    MovieEntity findMovieEntityById(Long id);
}
