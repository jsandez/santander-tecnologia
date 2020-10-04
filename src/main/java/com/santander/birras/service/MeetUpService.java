package com.santander.birras.service;

import com.santander.birras.controller.request.CreateMeetupRequest;
import com.santander.birras.controller.response.CreateMeetupResponse;
import com.santander.birras.exception.MeetUpNotExist;
import com.santander.birras.model.MeetUp;

import java.util.List;
import java.util.Optional;

public interface MeetUpService {

    CreateMeetupResponse createMeetup(CreateMeetupRequest createMeetupRequest);
    List<MeetUp> getAllMeetups();
    MeetUp getMeetup(Long id);
    void validateMeetup(Long id) throws MeetUpNotExist;
}
