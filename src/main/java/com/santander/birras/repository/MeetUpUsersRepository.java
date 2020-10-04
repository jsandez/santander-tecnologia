package com.santander.birras.repository;

import com.santander.birras.model.MeetUpUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetUpUsersRepository extends JpaRepository<MeetUpUsers,Long> {

    public MeetUpUsers findMeetUpUsersByMeetUpIdAndUsername(Long meetUpId, String username);

    @Query("SELECT muu.username FROM MeetUpUsers muu WHERE muu.meetUpId = ?1")
    public List<String> getUsersFromMeetUp(Long meetupId);
}
