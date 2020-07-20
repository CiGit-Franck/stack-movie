/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fty.api.model.Actor;
import fty.api.model.Director;
import fty.api.model.Genre;
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

/**
 *
 * @author utilisateur
 */
@Service
public class MovieServiceImpl extends JSonService implements MovieService {

    private final String URL_TMDB_MOVIE_SEARCH = "https://api.themoviedb.org/3/search/movie?query={keyword}&language={lang}&api_key={key}";
    private final String URL_TMDB_MOVIE_ID = "https://api.themoviedb.org/3/movie/{Id}?api_key={key}&language={lang}";
    private final String URL_TMDB_POSTER_ID = "https://api.themoviedb.org/3/movie/{Id}/images?api_key={key}";
    private final String URL_TMDB_ACTORS_ID = "https://api.themoviedb.org/3/movie/{Id}/credits?api_key={key}";
    private final String URL_OMDB_MOVIE_ID = "http://www.omdbapi.com/?i={Id}&apikey={key}";

    private static final String TMDB_FIELD_RESULTS = "results";
    private static final String TMDB_FIELD_ID = "id";
    private static final String TMDB_FIELD_IDIMDB = "imdb_id";
    private static final String TMDB_FIELD_TITLE = "title";
    private static final String TMDB_FIELD_STORY = "overview";
    private static final String TMDB_FIELD_RELEASE = "release_date";
    private static final String TMDB_FIELD_POSTER = "posters";
    private static final String TMDB_FIELD_RATIO = "aspect_ratio";
    private static final String TMDB_FIELD_PATH = "file_path";
    private static final String TMDB_FIELD_CAST = "cast";
    private static final String TMDB_FIELD_NAME = "name";
    private static final String TMDB_FIELD_RATE = "vote_average";
    private static final String TMDB_FIELD_GENRES = "genres";

    private static final String OMDB_FIELD_VOTES = "imdbVotes";
    private static final String OMDB_FIELD_RATE = "imdbRating";

    private MovieRepository movieRepository;
    private ActorRepository actorRepository;
    private GenreRepository genreRepository;
    private DirectorRepository directorRepository;

    private Map<String, String> args = new HashMap<>();
    private Map<String, String> argsOmdb = new HashMap<>();

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

        this.argsOmdb.put("key", "95ffd4d");
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
        this.args.put("keyword", keyword);

        List<Movie> moviesSearch = new ArrayList<>();
        String movieImdb = this.restTemplate.getForObject(URL_TMDB_MOVIE_SEARCH, String.class, this.args);

        if (movieImdb != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonMovie = mapper.readTree(movieImdb);

                if (checkNode(jsonMovie, TMDB_FIELD_RESULTS)) {
                    for (Object returns : jsonMovie.get(TMDB_FIELD_RESULTS)) {
                        ObjectMapper mapperReturns = new ObjectMapper();
                        JsonNode jsonMovieItem = mapperReturns.readTree(returns.toString());

                        Movie movieItem = getMovieSearchByImdbID(((Integer) jsonMovieItem.get(TMDB_FIELD_ID).asInt()).toString());

                        if (movieItem != null && movieItem.getTitle() != null) {
                            moviesSearch.add(movieItem);
                        }
                    }
                }
            } catch (JsonProcessingException ex) {
                Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return moviesSearch;
    }

    private Movie getMovieSearchByImdbID(String imdbId) {
        this.args.put("Id", imdbId);
        String movieImdb = this.restTemplate.getForObject(URL_TMDB_MOVIE_ID, String.class, this.args);
        Movie movie = null;

        if (movieImdb != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonMovie = mapper.readTree(movieImdb);
                
                if (checkIdAndTitle(jsonMovie)) {
                    movie = new Movie(jsonMovie.get(TMDB_FIELD_IDIMDB).asText(), jsonMovie.get(TMDB_FIELD_TITLE).asText());
                    movie.setDate(jsonMovie.get(TMDB_FIELD_RELEASE).asText());
                    movie.setStory(jsonMovie.get(TMDB_FIELD_STORY).asText());
                    movie.setImdbRating(jsonMovie.get(TMDB_FIELD_RATE).asText());
                    movie.setGenres(getGenres(jsonMovie));
                }
            } catch (JsonProcessingException ex) {
                Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return movie;
    }

    private List<Genre> getGenres(JsonNode jsonNode) {
        List<Genre> genres = new ArrayList<>();

        if (checkNode(jsonNode, TMDB_FIELD_GENRES)) {

            for (Object genreObj : jsonNode.get(TMDB_FIELD_GENRES)) {
                try {
                    ObjectMapper mapperGenre = new ObjectMapper();
                    JsonNode jsonGenre = mapperGenre.readTree(genreObj.toString());
                    Genre genre = new Genre(jsonGenre.get(TMDB_FIELD_NAME).asText());
                    genres.add(genre);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return genres;

    }

    @Override
    public Movie getMovieFromTMDBByImdbID(String imdbId, boolean save) {
        return this.getMovieFormTMDB(imdbId, save);
    }

    @Override
    public Movie getMovieFromTMDBByImdbID(Integer tmdbId, boolean save) {
        String imdbId = tmdbId.toString();
        return this.getMovieFormTMDB(imdbId, save);
    }

    private boolean checkIdAndTitle(JsonNode jsonNode) {
        return (checkNode(jsonNode, TMDB_FIELD_IDIMDB) && checkNode(jsonNode, TMDB_FIELD_TITLE));
    }

//    private String getPosterFromTMDBByImdbID(String imdbId) {
//        this.args.put("Id", imdbId);
//        String movieImdb = this.restTemplate.getForObject(URL_TMDB_POSTER_ID, String.class, this.args);
//
//        if (movieImdb != null) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode jsonMovie = mapper.readTree(movieImdb);
//
//                if (jsonMovie.has(TMDB_FIELD_POSTER)) {
//                    return this.searchPosterWithRatio(jsonMovie);
//                }
//            } catch (JsonProcessingException ex) {
//                Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return null;
//    }

//    private String searchPosterWithRatio(JsonNode jsonNode) {
//        String urlImage = "http://image.tmdb.org/t/p/original";
//        for (Object poster : jsonNode.get(TMDB_FIELD_POSTER)) {
//            try {
//                ObjectMapper mapperPoster = new ObjectMapper();
//                JsonNode jsonPoster = mapperPoster.readTree(poster.toString());
//                if (jsonPoster.has(TMDB_FIELD_RATIO)
//                        && jsonPoster.has(TMDB_FIELD_PATH)
//                        && jsonPoster.get(TMDB_FIELD_RATIO).asDouble() == 0.6666666666666666) {
//
//                    return urlImage + jsonPoster.get(TMDB_FIELD_PATH).asText();
//                }
//            } catch (JsonProcessingException ex) {
//                Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return null;
//    }

    private Movie getMovieFormTMDB(String imdbId, boolean save) {
//        this.args.put("Id", imdbId);
//
//        String movieImdb = this.restTemplate.getForObject(URL_TMDB_MOVIE_ID, String.class, this.args);
//
//        if (movieImdb != null) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode jsonMovie = mapper.readTree(movieImdb);
//
//                if (checkIdAndTitle(jsonMovie)) {
//                    Movie newMovie = new Movie(jsonMovie.get(TMDB_FIELD_IDIMDB).asText(), jsonMovie.get(TMDB_FIELD_TITLE).asText());
//
//                    newMovie.setDate(jsonMovie.get(TMDB_FIELD_RELEASE).asText());
//                    newMovie.setStory(jsonMovie.get(TMDB_FIELD_STORY).asText());
////                    newMovie.setGenres(this.addGenres(jsonMovie));
//                    newMovie.setPosterUrl(this.getPosterFromTMDBByImdbID(imdbId));
//                    newMovie.setActors(this.getActorsFromTMDBByImdbID(imdbId));
////                    newMovie.setDirectors(this.getDirectorFromTMDBByImdbID(imdbId));
////                    setImdbRatingAndVote(imdbId, newMovie);
//                    System.out.println(newMovie);
//                }
//            } catch (JsonProcessingException ex) {
//                Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return null;
    }

//    private void setImdbRatingAndVote(String imdbId, Movie newMovie) {
//        argsOmdb.put("Id", imdbId);
//
//        String movieImdb = this.restTemplate.getForObject(URL_OMDB_MOVIE_ID, String.class, this.argsOmdb);
//
//        if (movieImdb != null) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode jsonMovie = mapper.readTree(movieImdb);
//
//                if (jsonMovie.has(OMDB_FIELD_RATE)
//                        && !jsonMovie.get(OMDB_FIELD_RATE).asText().equals("N/A")) {
//                    newMovie.setImdbRating(Float.parseFloat(jsonMovie.get(OMDB_FIELD_RATE).asText()));
//                }
//
//                if (jsonMovie.has(OMDB_FIELD_VOTES)
//                        && !jsonMovie.get(OMDB_FIELD_VOTES).asText().equals("N/A")) {
//                    newMovie.setImdbVote(Integer.parseInt(jsonMovie.get(OMDB_FIELD_VOTES).asText().replace(",", "")));
//                }
//            } catch (JsonProcessingException ex) {
//                Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

//    @Override
//    public List<Actor> getActorsFromTMDBByImdbID(String imdbId) {
//        this.args.put("Id", imdbId);
//
//        List<Actor> actors = new ArrayList<>();
//
//        String movieImdb = this.restTemplate.getForObject(URL_TMDB_ACTORS_ID, String.class, this.args);
//
//        if (movieImdb != null) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode jsonMovie = mapper.readTree(movieImdb);
//                if (checkNode(jsonMovie, TMDB_FIELD_CAST)) {
//                    for (Object cast : jsonMovie.get(TMDB_FIELD_CAST)) {
//                        ObjectMapper mapperCast = new ObjectMapper();
//                        JsonNode jsonActor = mapperCast.readTree(cast.toString());
//                        Actor actor = new Actor(jsonActor.get(TMDB_FIELD_ID).asLong(), jsonActor.get(TMDB_FIELD_NAME).asText());
//                        actorRepository.save(actor);
//                        actors.add(actor);
//                    }
//                }
//            } catch (JsonProcessingException ex) {
//                Logger.getLogger(MovieServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        return actors;
//    }

//    @Override
//    public List<Director> getDirectorFromTMDBByImdbID(String imdbId) {
//        this.args.put("Id", imdbId);
//
//        List<Director> directors = new ArrayList<>();
//
//        return directors;
//    }
}
