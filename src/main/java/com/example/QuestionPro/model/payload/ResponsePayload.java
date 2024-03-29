package com.example.QuestionPro.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePayload {

    public enum RESPONSE_STATUS { SUCCESS, FAILURE };
    private RESPONSE_STATUS responseStatus;
    private String responseMessage;
    private List<Object> responseDetails = new ArrayList<>();

    public ResponsePayload(RESPONSE_STATUS responseStatus, String responseMessage){
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }

    public void addResponseDetails(Object responseDetail){
        this.responseDetails.add(responseDetail);
    }

}
