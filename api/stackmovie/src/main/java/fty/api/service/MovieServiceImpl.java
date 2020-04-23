/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.service;

import fty.api.model.Movie;
import fty.api.repository.MovieRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author utilisateur
 */
@Service
public class MovieServiceImpl implements MovieService {
    
    private MovieRepository movieRepository;

    private Map<String, String> args = new HashMap<>();
    
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @Override
    public Movie updateMovie(Movie updateMovie) {
        return movieRepository.save(updateMovie);
    }

    @Override
    public Movie getMovieById(String movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.get();
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMoviesFromTMDBByKeyword(String keyword) {
        List<Movie> moviesSearch = new ArrayList<>();
        return moviesSearch;
    }
    
}
