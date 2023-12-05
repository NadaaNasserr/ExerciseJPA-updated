package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.Product;
import com.example.exercisejpa.Service.ProductService;
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
@RequestMapping("/api/v1/product")
public class ProductController {


    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProduct() {

        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
    }


    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String addProduct = productService.addProduct(product);
        if (addProduct.equals("Ok")) {

            productService.addProduct(product);
            return ResponseEntity.status(200).body("product added");
        } else return ResponseEntity.status(400).body("categnory id not fount");

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String isUpdated = productService.updateProduct(id, product);
        if (isUpdated.equals("OK")) {
            return ResponseEntity.status(200).body("product updated");
        }
        if (isUpdated.equals("category id not fount")){
            return ResponseEntity.status(400).body("category id not fount");

    }
       else return ResponseEntity.status(400).body("product id not fount");
}


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        Boolean isDeletes = productService.deleteProduct(id);
        if(isDeletes){
            return ResponseEntity.status(200).body("Product deleted");
        }
        return ResponseEntity.status(400).body("Invalid id");
    }
}



