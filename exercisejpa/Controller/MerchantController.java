package com.example.exercisejpa.Controller;



import com.example.exercisejpa.Model.Merchant;
import com.example.exercisejpa.Service.MerchantService;
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
@RequestMapping("/api/v1/merchant")
public class MerchantController {



    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchant(){

        return ResponseEntity.status(HttpStatus.OK).body(merchantService.getAllMerchant());
    }


    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant added");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id , @RequestBody @Valid Merchant merchant , Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = merchantService.updateMerchant(id, merchant);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Merchant updated");
        }
        return ResponseEntity.status(400).body("Invalid id");

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        Boolean isDeletes = merchantService.deleteMerchant(id);
        if(isDeletes){
            return ResponseEntity.status(200).body("Merchant deleted");
        }
        return ResponseEntity.status(400).body("Invalid id");
    }
}




