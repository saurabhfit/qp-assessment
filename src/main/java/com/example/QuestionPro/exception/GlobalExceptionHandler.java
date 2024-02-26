package com.example.QuestionPro.exception;

import com.example.QuestionPro.model.payload.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponsePayload> handleGlobalException(Exception e){
        ResponsePayload payload = new ResponsePayload();
        payload.setResponseMessage(e.getMessage());
        payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
        ResponseEntity<ResponsePayload> response = new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponsePayload> handleGlobalException(ValidationException e){
        ResponsePayload payload = new ResponsePayload();
        payload.setResponseMessage("Validation failed: "+e.getMessage());
        payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
        ResponseEntity<ResponsePayload> response = new ResponseEntity<>(payload, HttpStatus.EXPECTATION_FAILED);
        return response;
    }
}
