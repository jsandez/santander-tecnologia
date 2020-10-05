package com.santander.birras.controller;

import com.santander.birras.controller.request.CheckInRequest;
import com.santander.birras.controller.request.CreateUserRequest;
import com.santander.birras.controller.request.InviteUsersRequest;
import com.santander.birras.controller.request.SubscribeRequest;
import com.santander.birras.controller.response.GetBoxOfBeersResponse;
import com.santander.birras.exception.MeetUpNotExist;
import com.santander.birras.exception.UserAlreadyExistException;
import com.santander.birras.exception.UserIsNotInMeetup;
import com.santander.birras.exception.UserNotExistException;
import com.santander.birras.service.MeetUpService;
import com.santander.birras.service.MeetUpUsersService;
import com.santander.birras.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private MeetUpUsersService meetUpUsersService;

    @Autowired
    private MeetUpService meetUpService;

    @PostMapping(value = "/create")
    @ApiOperation(value = "Creacion de usuarios",
            notes = "Creacion de usuarios del sistema, con su respectivo rol",
            response = String.class)
    public ResponseEntity createUser(@RequestBody CreateUserRequest request) {
        try {
            userServiceImpl.createUser(request);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Usuario " + request.getUsername() + " creado con exito", HttpStatus.OK);
    }

    @PostMapping(value = "/invite")
    @ApiOperation(value = "Invitacion de usuarios",
            notes = "Invitacion de usuarios a meetup",
            response = String.class)
    public ResponseEntity inviteUsers(@RequestBody InviteUsersRequest request) {
        try {
            meetUpService.validateMeetup(request.getMeetUpId());
            userServiceImpl.validateUsers(request.getUsers());
            for (String username: request.getUsers()) {
                if (!meetUpUsersService.userIsInMeetUp(request.getMeetUpId(),username)) {
                    meetUpUsersService.addUserToMeetup(request.getMeetUpId(), username);
                }
            }
        } catch (MeetUpNotExist | UserNotExistException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Usuarios agregados a meetup " + request.getMeetUpId(),HttpStatus.OK);
    }

    @PostMapping(value = "/subscribe")
    @ApiOperation(value = "Suscripcion de usuarios",
            notes = "Suscripcion de usuarios a una meetup",
            response = String.class)
    public ResponseEntity subscribeMeetup(@RequestBody SubscribeRequest request) {
        try {
            meetUpService.validateMeetup(request.getMeetUpId());
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            meetUpUsersService.addUserToMeetup(request.getMeetUpId(),userDetails.getUsername());
        } catch (MeetUpNotExist e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @PostMapping(value = "/checkin")
    @ApiOperation(value = "Checkin de usuarios",
            notes = "Checkin de usuarios para avisar que asistieron a cierta meetup",
            response = String.class)
    public ResponseEntity checkInMeetup(@RequestBody CheckInRequest request) {
        try {
            meetUpService.validateMeetup(request.getMeetUpId());
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            meetUpUsersService.checkInUser(request.getMeetUpId(),userDetails.getUsername());
        } catch (MeetUpNotExist | UserIsNotInMeetup e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
