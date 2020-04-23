/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.controler;

import fty.api.model.Movie;
import fty.api.service.MovieService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author utilisateur
 */
@RestController
@RequestMapping("movies")
public class MovieControler {

    private MovieService movieService;

    public MovieControler(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping()
    public Movie createMovie(Movie newMovie) {
        return movieService.createMovie(newMovie);
    }

    @PutMapping()
    public Movie updateMovie(Movie updateMovie) {
        return movieService.updateMovie(updateMovie);
    }

    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping()
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    public List<Movie> searchMoviesFromTMDBByKeyword(String keyword) {
        return movieService.searchMoviesFromTMDBByKeyword(keyword);
    }
}
