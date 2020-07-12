/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fty.api.model.Movie;
import fty.api.repository.ActorRepository;
import fty.api.repository.DirectorRepository;
import fty.api.repository.GenreRepository;
import fty.api.repository.MovieRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author utilisateur
 */
@Service
public class MovieServiceImpl implements MovieService {

    private final String URL_MOVIE_SEARCH = "https://api.themoviedb.org/3/search/movie?query={keyword}&language={lang}&api_key={key}";

    private MovieRepository movieRepository;
    private ActorRepository actorRepository;
    private GenreRepository genreRepository;
    private DirectorRepository directorRepository;

    private RestTemplate restTemplate;

    private Map<String, String> args = new HashMap<>();

    public MovieServiceImpl(
            MovieRepository movieRepository,
            ActorRepository actorRepository,
            GenreRepository genreRepository,
            DirectorRepository directorRepository,
            RestTemplateBuilder restTemplateBuilder) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
        this.restTemplate = restTemplateBuilder.build();

        this.args.put("key", "d2628396a5193eec56118853198e2102");
        this.args.put("lang", "fr-FR");
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
    static final String TMDB_FIELD_RESULTS = "results";
    static final String TMDB_FIELD_ID = "id";

    @Override
    public List<Movie> searchMoviesFromTMDBByKeyword(String keyword) {
        this.args.put("keyword", keyword);

        List<Movie> moviesSearch = new ArrayList<>();
        String movieImdb = this.restTemplate.getForObject(URL_MOVIE_SEARCH, String.class, this.args);
        if (movieImdb != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonMovie = mapper.readTree(movieImdb);
System.out.println("..."+jsonMovie);
//            if (this.checkNode(jsonMovie, TMDB_FIELD_RESULTS)) {
//                for (Object returns : jsonMovie.get(TMDB_FIELD_RESULTS)) {
//                    ObjectMapper mapperReturns = new ObjectMapper();
//                    JsonNode jsonMovieSearch = mapperReturns.readTree(returns.toString());
//
//                    Movie movieSearch = this.getMovieFromTMDBByImdbID(jsonMovieSearch.get(TMDB_FIELD_ID).asInt(), false);
//                    if (moviesSearch != null && movieSearch.getTitle() != null) {
//                        moviesSearch.add(movieSearch);
//                    }
//
//                }
//            }
            } catch (JsonProcessingException ex) {
                Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return moviesSearch;
    }

}
