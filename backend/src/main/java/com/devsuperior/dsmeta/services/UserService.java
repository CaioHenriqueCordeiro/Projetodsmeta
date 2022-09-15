package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.entities.User;
import com.devsuperior.dsmeta.repositories.UserRepository;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public ResponseEntity<Boolean> validUser(String login,
                                            String pass){

        Optional<User> userLogin = userRepository.findByLogin(login);

        if(!userLogin.isPresent()){
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(false);
        }

        User userFinal = userLogin.get();
        boolean valid = encoder.matches(pass, userFinal.getPassword());

        int status = (valid) ? HttpStatus.SC_OK : HttpStatus.SC_UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }
}
