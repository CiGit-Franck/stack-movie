/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.service;

import fty.api.model.User;
import fty.api.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author utilisateur
 */
@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User updateUser) {
        return userRepository.save(updateUser);
    }

    @Override
    public User getUserWithLogin(String mail, String password) {
        return userRepository.getUserWithLogin(mail, password);
    }
    
}
