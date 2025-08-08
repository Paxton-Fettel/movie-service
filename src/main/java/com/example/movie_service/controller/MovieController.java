package com.example.movie_service.controller;

import com.example.movie_service.entity.Movie;
import com.example.movie_service.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<?> getMovieByImdbId(@PathVariable String imdbId) {
        Movie movie = movieService.getMovieByImdbId(imdbId);
        if (movie != null) return ResponseEntity.ok(movie);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
