package com.example.QuestionPro.exception;

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
