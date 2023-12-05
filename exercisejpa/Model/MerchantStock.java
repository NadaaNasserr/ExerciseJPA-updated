package com.example.exercisejpa.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "productId must not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private Integer productId;



    @Column(columnDefinition = "varchar(25) not null")
    @NotNull(message = "merchantId must not be empty")
    private Integer merchantId;



    @Column(columnDefinition = "int not null")
    @Min(value = 10 , message = "stock must be more than 10 at start")
    @NotNull(message = "stock must not be empty")
    private Integer stock;

}
