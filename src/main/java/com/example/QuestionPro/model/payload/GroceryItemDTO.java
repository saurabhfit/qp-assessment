package com.example.QuestionPro.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroceryItemDTO {
    private String name;
    private Double price;
    private Integer inventoryLevel;
}
