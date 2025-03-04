package com.personal.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products_collection")
public class Product {

    @Id
    private String id;
    private String name;
    private int quantity;
    private double price;
    private Date dob;
}
