package com.santander.birras.service;

import com.santander.birras.exception.UserIsNotInMeetup;
import com.santander.birras.model.MeetUpUsers;
import com.santander.birras.repository.MeetUpUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetUpUsersServiceImpl implements MeetUpUsersService {

    @Autowired
    MeetUpUsersRepository meetUpUsersRepository;

    @Override
    public void addUserToMeetup(Long meetUpId,String username) {
        List<String> users = meetUpUsersRepository.getUsersFromMeetUp(meetUpId);
        if ((users != null) && (!users.contains(username))) {
            MeetUpUsers newMeetUpUsers = new MeetUpUsers();
            newMeetUpUsers.setMeetUpId(meetUpId);
            newMeetUpUsers.setUsername(username);
            meetUpUsersRepository.save(newMeetUpUsers);
        }
    }

    @Override
    public Boolean userIsInMeetUp(Long meetUpId, String username) {
        List<String> users = meetUpUsersRepository.getUsersFromMeetUp(meetUpId);
        return users.contains(username);
    }

    @Override
    public void createMeetupUsers(Long meetUpId, List<String> username) {
        for (String u: username) {
            MeetUpUsers meetUpUsers = new MeetUpUsers();
            meetUpUsers.setMeetUpId(meetUpId);
            meetUpUsers.setUsername(u);
            meetUpUsersRepository.save(meetUpUsers);
        }
    }

    @Override
    public void checkInUser(Long meetUpId, String username) throws UserIsNotInMeetup {
        if (!userIsInMeetUp(meetUpId,username)) {
             throw new UserIsNotInMeetup("El usuario " + username + " no esta en la meetup " + " id");
        }
        MeetUpUsers meetUpUsers = meetUpUsersRepository.findMeetUpUsersByMeetUpIdAndUsername(meetUpId,username);
        meetUpUsers.setCheckIn(1);
        meetUpUsersRepository.save(meetUpUsers);
    }

    @Override
    public List<String> getUsersFromMeetup(Long meetUpId) {
        return meetUpUsersRepository.getUsersFromMeetUp(meetUpId);
    }

}
