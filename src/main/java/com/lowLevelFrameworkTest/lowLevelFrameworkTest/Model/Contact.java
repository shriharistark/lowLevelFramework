package com.lowLevelFrameworkTest.lowLevelFrameworkTest.Model;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaAnnotations.OmegaEntity;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaAnnotations.OmegaID;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaAnnotations.OmegaIndex;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
@OmegaEntity
public class Contact {

    @OmegaID
    private String ID;

    @OmegaIndex
    private String firstName;

    @OmegaIndex
    private String lastName;

    @OmegaIndex
    private String mobileNumber;

    @OmegaIndex
    private int age;

    @OmegaIndex
    private boolean active;

    public Contact() {
        this.ID = (UUID.randomUUID().toString());
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
