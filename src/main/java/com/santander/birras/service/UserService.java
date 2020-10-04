package com.santander.birras.service;

import com.santander.birras.controller.request.CreateUserRequest;
import com.santander.birras.exception.UserAlreadyExistException;
import com.santander.birras.exception.UserNotExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public void createUser(CreateUserRequest request) throws UserAlreadyExistException;
    public boolean userExist(String username);
    public void validateUsers(List<String> usernames) throws UserNotExistException;
}
