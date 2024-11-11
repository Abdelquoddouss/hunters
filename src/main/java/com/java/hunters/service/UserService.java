package com.java.hunters.service;

import com.java.hunters.domain.User;
import com.java.hunters.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public User save(User user){
        return userRepo.save(user);
    }
}
