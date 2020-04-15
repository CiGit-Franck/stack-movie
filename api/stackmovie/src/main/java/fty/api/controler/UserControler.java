/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fty.api.controler;

import fty.api.model.User;
import fty.api.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author utilisateur
 */
@RestController
@RequestMapping("users")
@CrossOrigin("*")
public class UserControler {
    
    private UserService userService;

    public UserControler(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }
    
    @PostMapping()
    public User createUser(@RequestBody @Valid User newUser) {
        return userService.createUser(newUser);
    }
    
    @PutMapping()
    public User updateUser(User updateUser) {
        return userService.updateUser(updateUser);
    }
//    
//    @PostMapping()
//    public User getUserWithLogin(String login, String password) {
//        return userService.getUserWithLogin(login, password);
//    }
}
