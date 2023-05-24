package com.example.myguideapp;

import com.yandex.mapkit.geometry.Point;

import java.io.Serializable;

public class Sight implements Serializable {
    private String name;
    private int yearOfBuild;
    private String description;
    private String workHours;
    //public Point p;
    private double coordinates1;
    private double coordinates2;
    private String imagesDir;

    public String getImagesDir() {
        return imagesDir;
    }

    public void setImagesDir(String imagesDir) {
        this.imagesDir = imagesDir;
    }

    public double getCoordinates1() {
        return coordinates1;
    }

    public void setCoordinates1(double coordinates1) {
        this.coordinates1 = coordinates1;
    }

    public double getCoordinates2() {
        return coordinates2;
    }

    public void setCoordinates2(double coordinates2) {
        this.coordinates2 = coordinates2;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBuild() {
        return yearOfBuild;
    }

    public void setYearOfBuild(int yearOfBuild) {
        this.yearOfBuild = yearOfBuild;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sight(String imagesDir, String name, double coordinates1, double coordinates2,
                 String workHours, int yearOfBuild, String description) {
        this.imagesDir = imagesDir;
        this.name = name;
        this.coordinates1 = coordinates1;
        this.coordinates2 = coordinates2;
        this.workHours = workHours;
        this.yearOfBuild = yearOfBuild;
        this.description = description;
    }
}
