package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.User;
import com.example.exercisejpa.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllUser(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body("User added");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id , @RequestBody @Valid User user , Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        Boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.status(200).body("User updated");
        }
        return ResponseEntity.status(400).body("Invalid id");

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Boolean isDeletes = userService.deleteUser(id);
        if(isDeletes){
            return ResponseEntity.status(200).body("User deleted");
        }
        return ResponseEntity.status(400).body("Invalid id");
    }

    @PutMapping("/buy/{userId}/{productId}/{merchantId}/{quantity}")
    public ResponseEntity buyProduct(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer merchantId, @PathVariable Integer quantity) {

        String buyProduct = userService.buyProduct(userId, productId, merchantId, quantity);
        if (buyProduct.equals("OK")) {
            return ResponseEntity.status(HttpStatus.OK).body("purchase successful");
        }
        if(buyProduct.equals("balance is not enough")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("balance is not enough");

        }
        if(buyProduct.equals("unavailable")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product not available");

        }
        if(buyProduct.equals("productId and merchantId not found")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("productId and merchantId not found");

        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user id not found");

    }
}
