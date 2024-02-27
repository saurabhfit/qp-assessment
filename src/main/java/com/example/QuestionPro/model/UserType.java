package com.example.QuestionPro.model;

import lombok.Data;

public enum UserType {
    USER("User"),
    ADMIN("Admin");
    private String userType;
    UserType(String userType){
        this.userType = userType;
    }

    public String getUserType(UserType userType){
        return userType.name();
    }
}
