package com.josetin.expense_tracker.mapper;

import com.josetin.expense_tracker.dto.request.CreateUserRequest;
import com.josetin.expense_tracker.dto.response.UserResponse;
import com.josetin.expense_tracker.entity.User;

public class UserMapper {

    public static User toEntity(CreateUserRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        return user;
    }

    public static UserResponse toResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        return response;
    }
}
