package com.example.demo.service;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.RegisterResponse;
import com.example.demo.entity.User;

public interface UserService {

    RegisterResponse registerUser(RegisterRequest request);

    User getUserByUsername(String username);
}
