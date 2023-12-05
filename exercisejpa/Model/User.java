package com.example.exercisejpa.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "username must not be empty")
    @Size(min = 6 , message ="username must be more than 5 length long" )
    private String username;



    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "password must not be empty")
    // @Size(min = 7 , message ="password must be more than 6 length long" )
    @Pattern( regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$" , message = "password  must have\n" + "characters and digit and minimum 6 length")
    private String password;


    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "email must not be empty")
    @Email(message = "must be valid email")
    private String email;


    @Column(columnDefinition = "varchar(25) check(role='Admin' or role='Customer' )")
    @NotEmpty(message = "role must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$" , message = "role must be enter Admin or Customer ")
    private String role;


    @NotNull(message = "balance must not be empty")
    private double balance;


    @Column(columnDefinition = "int not null")
    @NegativeOrZero(message = "The initial value is zero ")
    private Integer countOfPurchases;
}
