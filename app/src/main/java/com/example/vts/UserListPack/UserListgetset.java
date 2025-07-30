package com.example.vts.UserListPack;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserListgetset {

    @SerializedName("Users")
    @Expose
    private List<User> users = null;
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
