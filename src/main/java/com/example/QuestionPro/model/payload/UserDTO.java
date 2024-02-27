package com.example.QuestionPro.model.payload;

import com.example.QuestionPro.model.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private UserType userType;
    private String name;
    private String address;
}
