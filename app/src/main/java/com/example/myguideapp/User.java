package com.example.myguideapp;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String name;
    private String surname;
    private String email;
    private List<Sight> favourSights;

    public List<Sight> getFavourSights() {
        return favourSights;
    }

    public void setFavourSights(List<Sight> favourSights) {
        this.favourSights = favourSights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void addSight(Sight sight){
        if (favourSights == null){
            favourSights = new ArrayList<>();
        }
        favourSights.add(sight);
    }

    public User(String name, String surname, String email, List<Sight> favourSights) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        if (favourSights != null){
            this.favourSights = favourSights;
        }
        else {
            this.favourSights = new ArrayList<>();
        }

    }
}
