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
@Table(name = "user")
public class User {

    @Id
    @SequenceGenerator(name = "user_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column()
    @NotNull
    private String firstName;

    @Column()
    @NotNull
    private String lastName;

    @Column()
    @NotNull
    private String mail;
    
    @Column()
    @NotNull
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_movie",
            joinColumns
            = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idMmovie")
    )
    private List<Movie> moviesSeen;
}
