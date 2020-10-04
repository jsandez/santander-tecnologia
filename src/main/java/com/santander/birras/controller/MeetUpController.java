package com.santander.birras.controller;

import com.santander.birras.controller.request.CreateMeetupRequest;
import com.santander.birras.controller.request.GetAmountOfBeersRequest;
import com.santander.birras.controller.response.CreateMeetupResponse;
import com.santander.birras.controller.response.GetAmountOfBeersResponse;
import com.santander.birras.controller.response.GetMeetUpResponse;
import com.santander.birras.controller.response.GetWeatherOfDayResponse;
import com.santander.birras.exception.MeetUpNotExist;
import com.santander.birras.exception.UserNotExistException;
import com.santander.birras.model.MeetUp;
import com.santander.birras.service.MeetUpService;
import com.santander.birras.service.MeetUpUsersService;
import com.santander.birras.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meetup")
public class MeetUpController {

    @Autowired
    private MeetUpService meetUpService;

    @Autowired
    private MeetUpUsersService meetUpUsersService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<CreateMeetupResponse> createMeetup(@RequestBody @Valid CreateMeetupRequest createMeetupRequest) {
        CreateMeetupResponse response;
        try {
            userService.validateUsers(createMeetupRequest.getUsers());
            response = meetUpService.createMeetup(createMeetupRequest);
            meetUpUsersService.createMeetupUsers(response.getId(),createMeetupRequest.getUsers());
        } catch (UserNotExistException e ) {
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping(value = "/{meetUpId}")
    public ResponseEntity getMeetup(@PathVariable Long meetUpId) {
        GetMeetUpResponse response = new GetMeetUpResponse();
        try {
            meetUpService.validateMeetup(meetUpId);
            MeetUp meetUp = meetUpService.getMeetup(meetUpId);
            response.setId(meetUpId);
            response.setWeatherDay(meetUp.getMeetUpDay());
            response.setUsers(meetUpUsersService.getUsersFromMeetup(meetUp.getId()));
        } catch (MeetUpNotExist e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Error interno del sevidor",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllMeetups() {
        List<MeetUp> meetUps = meetUpService.getAllMeetups();
        List<GetMeetUpResponse> responses = new ArrayList<GetMeetUpResponse>();
        for (MeetUp meetUp: meetUps) {
            GetMeetUpResponse response = new GetMeetUpResponse();
            response.setId(meetUp.getId());
            response.setWeatherDay(meetUp.getMeetUpDay());
            response.setUsers(meetUpUsersService.getUsersFromMeetup(meetUp.getId()));
            responses.add(response);
        }
        return new ResponseEntity(responses,HttpStatus.OK);
    }

    @PostMapping(value = "/beers")
    public ResponseEntity getAmountOfBeersOfMeetup(@RequestBody GetAmountOfBeersRequest getAmountOfBeersRequest) {
        GetAmountOfBeersResponse response = new GetAmountOfBeersResponse();
        try {
            meetUpService.validateMeetup(getAmountOfBeersRequest.getMeetUpId());
            MeetUp meetUp = meetUpService.getMeetup(getAmountOfBeersRequest.getMeetUpId());
            response.setAmountOfBeers(meetUp.getBoxOfBeers());
        } catch (MeetUpNotExist e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Error interno del sevidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
