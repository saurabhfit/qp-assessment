package com.example.QuestionPro.model;

import lombok.Data;

@Data
public class ValidationException extends Exception {
    public ValidationException(){
        super();
    }
    public ValidationException(String message){
        super(message);
    }
}
