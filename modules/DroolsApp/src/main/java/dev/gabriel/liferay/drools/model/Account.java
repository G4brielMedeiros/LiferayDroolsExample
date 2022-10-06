package dev.gabriel.liferay.drools.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    String name;
    int age;
    String country;
    String state;
    String position;
    boolean canDrive;
    List<String> requiredDocuments = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isCanDrive() {
        return canDrive;
    }

    public void setCanDrive(boolean canDrive) {
        this.canDrive = canDrive;
    }

    public List<String> getNecessaryDocuments() {
        return requiredDocuments;
    }

    public void addNecessaryDocuments(String document) {
        this.requiredDocuments.add(document);
    }
}
