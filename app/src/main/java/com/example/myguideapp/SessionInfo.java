package com.example.myguideapp;

import java.util.ArrayList;
import java.util.List;

public abstract class SessionInfo {
    private static List<User> allUsers = new ArrayList<>();

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(List<User> allUsers) {
        SessionInfo.allUsers = allUsers;
    }

    public static void addUser(User user){
        if (allUsers == null){
            allUsers = new ArrayList<>();
        }
        allUsers.add(user);
    }

}
