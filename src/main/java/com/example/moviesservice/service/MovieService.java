package com.example.moviesservice.service;

import com.example.moviesservice.model.dto.MovieDto;
import com.example.moviesservice.model.entity.MovieEntity;
import com.example.moviesservice.repository.MoviesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class MovieService {

    private final MoviesRepository moviesRepository;

    @CacheEvict(value = "movieCache", allEntries = true)
    @Transactional
    public MovieEntity saveMovie(MovieDto movieDto, Long id) {

        MovieEntity movieEntity = MovieEntity.builder()
            .id(id)
            .title(movieDto.getTitle())
            .director(movieDto.getDirector())
            .stars(movieDto.getStars())
            .year(movieDto.getYear())
            .build();

        moviesRepository.save(movieEntity);
        log.info("movie with id [{}] was saved", movieEntity.getId());
        return movieEntity;
    }

    @Cacheable("movieCache")
    @Transactional(readOnly = true)
    public MovieDto getMovieById(Long id) {
        MovieEntity movieEntity = moviesRepository.findMovieEntityById(id);
        if (movieEntity == null) {
            log.info("No movie found with this id [{}]", id);
        }
        log.info("Found movie with id [{}]", id);
        return MovieDto.builder()
            .title(movieEntity.getTitle())
            .director(movieEntity.getDirector())
            .year(movieEntity.getYear())
            .stars(movieEntity.getStars())
            .build();
    }
}
