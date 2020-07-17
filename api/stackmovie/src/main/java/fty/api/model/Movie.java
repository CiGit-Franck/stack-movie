/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author utilisateur
 */
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @SequenceGenerator(name = "movie_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;

    @Column(nullable = false, length = 10)
    @NotNull
    private String idImdb;

    @Column()
    @NotNull
    private String title;

    @Column()
    private String date;

    @Column(length = 255)
    private String posterUrl;

    @Column(length = 1000)
    private String story;

    @Column()
    private Float imdbRating;

    @Column()
    private Integer imdbVote;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Director> directors = new ArrayList<Director>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Genre> genres = new ArrayList<Genre>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Actor> actors = new ArrayList<Actor>();

    public Movie() {
    }

    public Movie(String idImdb, String title) {
        this.idImdb = idImdb;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{" + "idMovie=" + idMovie + ", idImdb=" + idImdb + ", title=" + title + ", date=" + date + ", imdbRating=" + imdbRating + ", imdbVote=" + imdbVote + '}';
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getIdImdb() {
        return idImdb;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getStory() {
        return story;
    }

    public Float getImdbRating() {
        return imdbRating;
    }

    public Integer getImdbVote() {
        return imdbVote;
    }

    public User getUser() {
        return user;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public void setIdImdb(String idImdb) {
        this.idImdb = idImdb;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setImdbRating(String imdbRating) {
        setImdbRating(Float.valueOf(imdbRating));
    }

    public void setImdbRating(Float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbVote(Integer imdbVote) {
        this.imdbVote = imdbVote;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

}
