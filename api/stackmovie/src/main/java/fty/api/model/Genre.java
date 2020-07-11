/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author utilisateur
 */
@Entity
@Table(name = "genre")
public class Genre {
    
    @Id
    @SequenceGenerator(name = "genre_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenre;

    @Column()
    @NotNull
    private String name;
    
    @ManyToOne
    private Movie movie;

    public Genre() {
    }

    @Override
    public String toString() {
        return "Genre{" + "idGenre=" + idGenre + ", name=" + name + '}';
    }

    public Long getIdGenre() {
        return idGenre;
    }

    public String getName() {
        return name;
    }

    public Movie getMovie() {
        return movie;
    }
}
