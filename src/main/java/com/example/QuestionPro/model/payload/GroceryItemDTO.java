package com.example.QuestionPro.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroceryItemDTO {
    private String name;
    private Double price;
    private Integer inventoryLevel;
}
