package com.example.moviesservice.controller;

import com.example.moviesservice.model.dto.MovieDto;
//import com.example.moviesservice.repository.MoviesRepository;
import com.example.moviesservice.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/addMovie/{id}")
    public ResponseEntity<HttpStatus> addMovie(@PathVariable(value = "id") Long id, @RequestBody MovieDto movie) {
        movieService.saveMovie(movie, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long id) {
        MovieDto movieDto = movieService.getMovieById(id);
        return ResponseEntity.ok(movieDto);
    }
}
