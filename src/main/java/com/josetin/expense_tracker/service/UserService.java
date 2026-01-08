package com.josetin.expense_tracker.service;

import com.josetin.expense_tracker.dto.request.CreateUserRequest;
import com.josetin.expense_tracker.dto.request.RegisterUserRequest;
import com.josetin.expense_tracker.dto.response.UserResponse;
import com.josetin.expense_tracker.entity.User;
import com.josetin.expense_tracker.exception.ResourceNotFoundException;
import com.josetin.expense_tracker.mapper.UserMapper;
import com.josetin.expense_tracker.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterUserRequest request){

        if(userRepo.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        if(userRepo.existsByEmail(request.getEmail())){
            throw  new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepo.save(user);
    }

    public List<UserResponse> getAllUser(){
        return userRepo.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not Found"));
        return UserMapper.toResponse(user);
    }
}
