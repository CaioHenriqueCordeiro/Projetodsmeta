package com.devsuperior.dsmeta.resource;

import com.devsuperior.dsmeta.entities.User;
import com.devsuperior.dsmeta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/listAll")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/validUser")
    public ResponseEntity<Boolean> validUser(@RequestParam String login,
                                            @RequestParam String pass){

        return userService.validUser(login, pass);
    }
}
