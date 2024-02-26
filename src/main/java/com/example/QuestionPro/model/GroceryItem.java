package com.example.QuestionPro.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false, name="inventory_level")
    private int inventoryLevel;

}
