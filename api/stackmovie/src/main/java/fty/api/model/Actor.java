/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author utilisateur
 */
@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @NotNull
    private Long idActor;

    @Column()
    @NotNull
    private String name;
    
    @JsonIgnore
    @ManyToOne
    private Movie movie;

    public Actor() {
    }

    public Actor(Long idActor, String name) {
        this.idActor = idActor;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor{" + "idActor=" + idActor + ", name=" + name + '}';
    }

    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
