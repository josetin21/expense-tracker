package com.josetin.expense_tracker.controller;

import com.josetin.expense_tracker.dto.request.CreateUserRequest;
import com.josetin.expense_tracker.dto.request.RegisterUserRequest;
import com.josetin.expense_tracker.dto.response.UserResponse;
import com.josetin.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUserRequest request){
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public List<UserResponse> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
