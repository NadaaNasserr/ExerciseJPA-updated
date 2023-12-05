package com.example.exercisejpa.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {



        @Id
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Integer id;



        @Column(columnDefinition = "varchar(20) not null ")
        @NotEmpty(message = "name must not be empty")
        @Size(min = 4 , message = "name must more than 3 length long")
        private String name;

    }

