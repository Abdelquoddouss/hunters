package com.java.hunters.web.rest;


import com.java.hunters.domain.User;
import com.java.hunters.service.UserService;
import com.java.hunters.web.vm.UserResponse;
import com.java.hunters.web.vm.UserVM;
import com.java.hunters.web.vm.mappers.UserRegisterVmMappers;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRegisterVmMappers  userRegisterVmMappers;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserVM userVM) {
        User user = userRegisterVmMappers.toEntity(userVM);
        userService.addSUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> searchUsers(
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("nationality").ascending());

        Page<User> users = userService.getAll(pageable);

        List<UserResponse> usersDTO = userRegisterVmMappers.toUserResponse(users);

        return ResponseEntity.ok(usersDTO);
    }

}
