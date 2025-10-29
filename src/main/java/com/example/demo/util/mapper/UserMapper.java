package com.example.demo.util.mapper;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.RegisterResponse;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "myFeedback", ignore = true)
    @Mapping(target = "invitations", ignore = true)
    User toUser(RegisterRequest request);

    RegisterResponse toRegisterResponse(User user);
}
