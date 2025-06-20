package com.pooji.ebs.service;


import com.pooji.ebs.entities.User;
import com.pooji.ebs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public User loginUser(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
