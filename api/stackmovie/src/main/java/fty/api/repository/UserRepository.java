/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.repository;

import fty.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author utilisateur
 */
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select * from user where mail = :mail and password = :password")
//    User getUserWithLogin(@Param("mail") String mail, @Param("password") String password);
}
