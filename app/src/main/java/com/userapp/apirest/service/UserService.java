package com.userapp.apirest.service;

import java.util.List;

import com.userapp.apirest.model.UserResponse;
import com.userapp.apirest.persistence.entity.UserEntity;

public interface UserService {
    List<UserEntity> findAll();

    UserResponse createUser(UserEntity user);
}
