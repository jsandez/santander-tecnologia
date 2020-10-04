package com.santander.birras.service;

import com.santander.birras.controller.request.CreateUserRequest;
import com.santander.birras.exception.UserAlreadyExistException;
import com.santander.birras.exception.UserNotExistException;
import com.santander.birras.model.User;
import com.santander.birras.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null ) {
            throw new UsernameNotFoundException("El usuario no existe en la base");
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(),authorityList);

        return userDetails;
  }

    @Override
    public void createUser(CreateUserRequest request) throws UserAlreadyExistException {
        if (userExist(request.getUsername())) {
            throw new UserAlreadyExistException("El usuario ya existe en la base de datos");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }

    @Override
    public boolean userExist(String username) {
        return (userRepository.findByUsername(username) != null);
    }

    @Override
    public void validateUsers(List<String> usernames) throws UserNotExistException {
        for(String u: usernames) {
            if (!userExist(u)) {
                throw new UserNotExistException("El usuario " + u + " no existe");
            }
        }
    }
}
