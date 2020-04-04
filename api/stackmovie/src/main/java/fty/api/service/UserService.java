/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.service;

import fty.api.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author utilisateur
 */
@Service
public interface UserService {
    User createUser(User newUser);
    User updateUser(User updateUser);
    List<User> getUsers();
    User getUser(Long id);
}
