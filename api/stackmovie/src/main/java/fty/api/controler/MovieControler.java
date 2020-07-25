/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.controler;

import fty.api.model.Movie;
import fty.api.service.MovieService;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author utilisateur
 */
@RestController
@RequestMapping("movies")
@CrossOrigin("*")
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
System.out.println("[controler:getMovieById] with "+movieId);
        return movieService.getMovieById(movieId);
    }
    
    @GetMapping("/imdb/{imdbId}")
    public Movie getMovieByImdbId(@PathVariable String imdbId){
//System.out.println("[controler:getMovieByImdbId] with "+imdbId);
        return movieService.getMovieByImdbId(imdbId);
    }

    @GetMapping()
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/search/{keyword}")
    public List<Movie> searchMoviesFromTMDBByKeyword(@PathVariable String keyword) {
//System.out.println("[controler:searchMoviesFromTMDBByKeyword] with "+keyword);
        return movieService.searchMoviesFromTMDBByKeyword(keyword);
    }
}
