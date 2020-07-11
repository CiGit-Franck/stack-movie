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
    
    @Column()
    @NotNull
    private String title;

    @Column()
    private String date;

    @Column()
    private String story;

    @Column()
    private Float imdbRating;

    @Column()
    private Integer imdbVote;

    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Director> directors = new HashSet<Director>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Genre> genres = new HashSet<Genre>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Actor> actors = new HashSet<Actor>();

    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie{" + "idMovie=" + idMovie + ", title=" + title + ", date=" + date + ", story=" + story + ", imdbRating=" + imdbRating + ", imdbVote=" + imdbVote + '}';
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

    public Set<Director> getDirectors() {
        return directors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Actor> getActors() {
        return actors;
    }

}
