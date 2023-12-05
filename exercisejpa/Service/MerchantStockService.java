package com.example.exercisejpa.Service;


import com.example.exercisejpa.Model.MerchantStock;
import com.example.exercisejpa.Repository.MerchantStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class MerchantStockService {


    private final MerchantStockRepository merchantStockRepository;

    private final ProductService productService;
    private final MerchantService merchantService;

    public List<MerchantStock> getAllMerchantStock() {

        return merchantStockRepository.findAll();
    }


    public String addMerchantStock(MerchantStock merchantStock) {


        for (int i = 0; i < productService.getAllProduct().size(); i++) {
            if (productService.getAllProduct().get(i).getId().equals(merchantStock.getProductId())) {

                for (int j = 0; j < merchantService.getAllMerchant().size(); j++) {
                    if (merchantService.getAllMerchant().get(i).getId().equals(merchantStock.getMerchantId())) {
                        merchantStockRepository.save(merchantStock);
                        return "OK";
                    }

                }
                return "merchant id not found";
            }
        }
        return "product id not found";

    }

    public String updateMerchantStock(Integer id, MerchantStock merchantStock) {

        MerchantStock oldMerchantStock = merchantStockRepository.getById(id);
        for (int i = 0; i < productService.getAllProduct().size(); i++) {
            if (productService.getAllProduct().get(i).getId().equals(merchantStock.getProductId())) {

                for (int j = 0; j < merchantService.getAllMerchant().size(); j++) {
                    if (merchantService.getAllMerchant().get(i).getId().equals(merchantStock.getMerchantId())) {
                        oldMerchantStock.setProductId(merchantStock.getProductId());
                        oldMerchantStock.setMerchantId(merchantStock.getMerchantId());
                        oldMerchantStock.setStock(merchantStock.getStock());
                        merchantStockRepository.save(oldMerchantStock);
                        return "OK";
                    }
                }
                return "merchant id not found";
            }
        }
        return "product id not found";
    }


    public Boolean deleteMerchantStock(Integer id) {

        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if (merchantStock == null) {
            return false;
        }
        merchantStockRepository.delete(merchantStock);
        return true;
    }




    public String addStocks(Integer productId, Integer merchantId, Integer amount) {

        for (int i = 0; i < getAllMerchantStock().size(); i++) {
            if (getAllMerchantStock().get(i).getProductId().equals(productId)) {
                if (getAllMerchantStock().get(i).getMerchantId().equals(merchantId)) {
                    getAllMerchantStock().get(i).setStock(getAllMerchantStock().get(i).getStock() + amount);
                    merchantStockRepository.saveAll(getAllMerchantStock());
                    return "Stoke added";
                }
                return "merchant Id not found";
            }

        }
        return "Product Id not found";
    }
}





