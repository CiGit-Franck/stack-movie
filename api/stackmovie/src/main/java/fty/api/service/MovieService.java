/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.service;

import fty.api.model.Movie;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author utilisateur
 */
@Service
public interface MovieService {
    
    Movie createMovie(Movie newMovie);

    Movie updateMovie(Movie updateMovie);
    
    Movie getMovieById(String movieId);
    
    List<Movie> getMovies();
    
    List<Movie> searchMoviesFromTMDBByKeyword(String keyword);
}