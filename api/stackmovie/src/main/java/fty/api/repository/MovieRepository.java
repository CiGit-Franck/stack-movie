/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.repository;

import fty.api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author utilisateur
 */
public interface MovieRepository extends JpaRepository<Movie, String> {
    
}
