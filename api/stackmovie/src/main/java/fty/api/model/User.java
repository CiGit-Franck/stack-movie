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
    @SequenceGenerator(name = "user_seq_id", sequenceName = "user_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq_id")
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

//    public User(Long idUser, String firstName, String lastName, String mail, String password) {
//        this.idUser = idUser;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.mail = mail;
//        this.password = password;
//        this.moviesSeen = new ArrayList<>();
//    }

    public Long getIdUser() {
        return idUser;
    }

    public List<Movie> getMoviesSeen() {
        return moviesSeen;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", mail=" + mail + ", password=" + password + '}';
    }
}
