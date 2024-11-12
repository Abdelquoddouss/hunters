package com.java.hunters.service;

import com.java.hunters.domain.User;
import com.java.hunters.exception.DuplicateResourceException;
import com.java.hunters.exception.EmailAlreadyExisteException;
import com.java.hunters.exception.InvalidPasswordException;
import com.java.hunters.exception.ResourceNotFoundException;
import com.java.hunters.repository.UserRepo;
import com.java.hunters.utils.PasswordUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    public User addSUser(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExisteException("this email already exists");
        }
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        return userRepo.save(user);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public User login(User user) {
        User userEntity = userRepo.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("this email does not exist"));

        if (!PasswordUtil.checkPassword(user.getPassword(), userEntity.getPassword())) {
            throw new InvalidPasswordException("this password does not match ");
        }

        return userEntity;
    }

    public List<User> searchUsers(String firstName, String lastName, String email) {
        return userRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndEmailContainingIgnoreCase(firstName, lastName, email);
    }

}
