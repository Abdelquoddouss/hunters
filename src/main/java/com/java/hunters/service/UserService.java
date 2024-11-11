package com.java.hunters.service;

import com.java.hunters.domain.User;
import com.java.hunters.exception.DuplicateResourceException;
import com.java.hunters.exception.EmailAlreadyExisteException;
import com.java.hunters.repository.UserRepo;
import com.java.hunters.utils.PasswordUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    public User addSUser(User user) {

        return userRepo.save(user);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }
}
