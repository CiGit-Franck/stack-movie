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
@Table(name = "director")
public class Director {

    @Id
    @SequenceGenerator(name = "director_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDirector;

    @Column()
    @NotNull
    private String firstName;

    @Column()
    @NotNull
    private String lastName;
}
