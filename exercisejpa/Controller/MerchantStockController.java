package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.MerchantStock;
import com.example.exercisejpa.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/merchantStock")
public class MerchantStockController {


    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStock() {

        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getAllMerchantStock());
    }


    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String isAdded = merchantStockService.addMerchantStock(merchantStock);
        if (isAdded.equals("OK")) {
            return ResponseEntity.status(200).body("Merchant Stock added");
        }
        if (isAdded.equals("merchant id not found")) {
            return ResponseEntity.status(400).body("merchant id not found");
        } else return ResponseEntity.status(400).body("product id not found");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String isUpdated = merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdated.equals("OK")) {
            return ResponseEntity.status(200).body("Merchant Stock updated");
        }

        if (isUpdated.equals("merchant id not found")) {
            return ResponseEntity.status(400).body("merchant id not found");
        } else return ResponseEntity.status(400).body("product id not found");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        Boolean isDeletes = merchantStockService.deleteMerchantStock(id);
        if (isDeletes) {
            return ResponseEntity.status(200).body("Merchant Stock deleted");
        }
        return ResponseEntity.status(400).body("Invalid id");
    }
//
//    @PutMapping("/addStocks/{productId}/{merchantId}/{amount}")
//    public ResponseEntity addStocks(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int amount) {
//
//        boolean addedStocks = merchantStockService.addStocks(productId, merchantId, amount);
//
//        if (addedStocks) {
//            return ResponseEntity.status(HttpStatus.OK).body("stock added successfully");
//        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong  Id");
////        if (addedStocks.equals("Stoke added")) {
////            return ResponseEntity.status(HttpStatus.OK).body("stock added successfully");
////        } else if (addedStocks.equals("merchant Id not found")) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant Id");
////        } else
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product Id");
////    }
@PutMapping("/addStocks/{productId}/{merchantId}/{amount}")
public ResponseEntity addStocks(@PathVariable Integer productId, @PathVariable Integer merchantId, @PathVariable Integer amount) {

    String addedStocks = merchantStockService.addStocks(productId, merchantId, amount);
    if (addedStocks.equals("Stoke added")) {
        return ResponseEntity.status(HttpStatus.OK).body("stock added successfully");
    } else if (addedStocks.equals("merchant Id not found")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant Id");
    } else
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product Id");
}


}





