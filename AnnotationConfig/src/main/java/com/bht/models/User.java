package com.bht.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;


public class User {
    // All of the configuration of warning message
    // is configure inside messages.properties !
    // Not throw warning in the object class / model file !


    // Annotation limit the min of the field
    //@Min(1)
    @JsonProperty("id")
    private int id;


    // Annotation check the field is not empty
    //@NotEmpty
    @JsonProperty("username")
    private String username;

    // Annotation limit the max and min of the field
    //@Length(min = 6, max = 20)
    @JsonProperty("password")
    private String password;


    // Annotation check if the input String is in correct email format
    //@Email
    @JsonProperty("email")
    private String email;


    // create an empty-list of hobbies
    @JsonProperty("hobbies")
    private List<String> hobbies;


    // Gender of user: male or female
    @JsonProperty("gender")
    private boolean gender;


    // Avatar path
    @JsonProperty("hasAvatar")
    private boolean hasAvatar;


    @JsonProperty("avatarPath")
    private String avatarPath;


    // a boolean value to store if user accept the agreement or not !
    // check if user accept the page's aggreement !
    //@AssertTrue
    @JsonProperty("acceptAgreement")
    private boolean acceptAgreement;


    public User() {
        username = "";
        password = "";
        email = "";
        id = 0;
        hobbies = Collections.emptyList();
        gender = false;
        hasAvatar = false;
        avatarPath = "unknown.jpg";
        acceptAgreement = false;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public boolean isAcceptAgreement() {
        return acceptAgreement;
    }

    public void setAcceptAgreement(boolean acceptAgreement) {
        this.acceptAgreement = acceptAgreement;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean getHasAvatar() {
        return hasAvatar;
    }

    public void setHasAvatar(boolean hasAvatar) {
        this.hasAvatar = hasAvatar;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
