package com.santander.birras.controller;

import com.santander.birras.controller.request.AuthenticationRequest;
import com.santander.birras.controller.response.AuthenticationResponse;
import com.santander.birras.security.JwtAuthenticationUtil;
import com.santander.birras.service.UserService;
import com.santander.birras.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    JwtAuthenticationUtil jwtAuthenticationUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity("Usuario o password incorrecto.",HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetails = userServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtAuthenticationUtil.generateJwtToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setJwt(jwt);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
