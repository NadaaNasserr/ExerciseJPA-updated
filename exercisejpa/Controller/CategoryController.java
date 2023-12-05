package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.Category;
import com.example.exercisejpa.Service.CategoryService;
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
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;




    @GetMapping("/get")
    public ResponseEntity getAllCategory(){

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
    }


    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("category added");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id , @RequestBody @Valid Category category , Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = categoryService.updateCategory(id, category);
        if (isUpdated) {
            return ResponseEntity.status(200).body("category updated");
        }
        return ResponseEntity.status(400).body("Invalid id");

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        Boolean isDeletes = categoryService.deleteCategory(id);
        if(isDeletes){
            return ResponseEntity.status(200).body("Category deleted");
        }
        return ResponseEntity.status(400).body("Invalid id");
    }
}

