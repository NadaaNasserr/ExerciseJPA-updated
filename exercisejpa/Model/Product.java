package com.example.exercisejpa.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 4 , message = "name must more than 3 length long")
    private String name;


    @Column(columnDefinition = "int not null")
    @NotNull(message = "price must not be empty")
    @Positive(message = " price must be positive number")
    private double price;



    @Column(columnDefinition = "varchar(25) not null")
    @NotNull(message = "category must not be empty")
    private Integer categoryID;


    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "category name must not be empty")
    private String categoryName;
}
