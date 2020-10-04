package com.santander.birras.service;

import com.santander.birras.exception.UserIsNotInMeetup;

import java.util.List;

public interface MeetUpUsersService {

    public void addUserToMeetup(Long meetupId,String username);
    public Boolean userIsInMeetUp(Long meetUpId, String username);
    public void createMeetupUsers(Long meetUpId, List<String> username);
    public void checkInUser(Long meetUpId, String username) throws UserIsNotInMeetup;
    public List<String> getUsersFromMeetup(Long meetUpId);
}
