package com.example.moviesservice.controller;

import com.example.moviesservice.JwtProvider;
import com.example.moviesservice.model.dto.MovieDto;
import com.example.moviesservice.repository.MoviesRepository;
import com.example.moviesservice.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class MovieController {

    private final MovieService movieService;
    private final JwtProvider jwtProvider;

    @PostMapping("/addMovie/{id}")
    public ResponseEntity<HttpStatus> addMovie(@PathVariable(value = "id") Long id, @RequestHeader String token, @RequestBody MovieDto movie) {
        var isTokenValid = jwtProvider.validateToken(token);
        if (!isTokenValid) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        movieService.saveMovie(movie, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long id, @RequestHeader String token) {
        var isTokenValid = jwtProvider.validateToken(token);
        if (!isTokenValid) {
            return (ResponseEntity<MovieDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        MovieDto movieDto = movieService.getMovieById(id);
        return ResponseEntity.ok(movieDto);
    }
}
