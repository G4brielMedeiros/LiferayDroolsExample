package dev.gabriel.liferay.drools.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    String country;
    String position;
    boolean carDriver;
    List<String> requiredDocuments = new ArrayList<>();

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isCarDriver() {
        return carDriver;
    }

    public void setCanDrive(boolean carDriver) {
        this.carDriver = carDriver;
    }

    public List<String> getRequiredDocuments() {
        return requiredDocuments;
    }

    public void addRequiredDocument(String document) {
        this.requiredDocuments.add(document);
    }
}
