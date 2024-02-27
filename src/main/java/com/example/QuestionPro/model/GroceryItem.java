package com.example.QuestionPro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="GroceryItem")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("item_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false, name="inventory_level")
    private int inventoryLevel;

}
