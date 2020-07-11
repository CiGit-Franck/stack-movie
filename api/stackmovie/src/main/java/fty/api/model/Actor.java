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
@Table(name = "actor")
public class Actor {

    @Id
    @SequenceGenerator(name = "actor_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActor;

    @Column()
    @NotNull
    private String firstName;

    @Column()
    @NotNull
    private String lastName;
    
    @ManyToOne
    private Movie movie;

    public Actor() {
    }

    @Override
    public String toString() {
        return "Actor{" + "idActor=" + idActor + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }

    public Long getIdActor() {
        return idActor;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Movie getMovie() {
        return movie;
    }
}
