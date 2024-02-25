package com.example.QuestionPro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="GroceryItem")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GroceryItem {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false, name="inventory_level")
    private int inventoryLevel;

}
