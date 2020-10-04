package com.santander.birras.controller.request;

import java.util.List;

public class InviteUsersRequest {

    Long meetUpId;
    List<String> users;

    public Long getMeetUpId() {
        return meetUpId;
    }

    public void setMeetUpId(Long meetUpId) {
        this.meetUpId = meetUpId;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
