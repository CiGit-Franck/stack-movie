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

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns =
            @JoinColumn (name = "idMmovie"),
            inverseJoinColumns = @JoinColumn ( name = "idDirector")
    )
    private List<Director> directors;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns =
            @JoinColumn (name = "idMmovie"),
            inverseJoinColumns = @JoinColumn ( name = "idGenre")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns =
            @JoinColumn (name = "idMmovie"),
            inverseJoinColumns = @JoinColumn ( name = "idActor")
    )
    private List<Actor> actors;
}
