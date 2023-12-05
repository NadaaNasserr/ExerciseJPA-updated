package com.example.exercisejpa.Service;


import com.example.exercisejpa.Model.Product;
import com.example.exercisejpa.Repository.CategoryRepository;
import com.example.exercisejpa.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {



    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public List<Product> getAllProduct(){

        return productRepository.findAll();
    }


    public String addProduct(Product product){
        for (int i = 0; i <categoryService.getAllCategory().size() ; i++) {
            if(categoryService.getAllCategory().get(i).getId().equals(product.getCategoryID())){
                    productRepository.save(product);
                    return "Ok";

            }


        }
        return "category id not fount";
    }



    public String updateProduct(Integer id , Product product) {

        Product oldProduct = productRepository.getById(id);

        for (int i = 0; i < getAllProduct().size(); i++) {
            if (getAllProduct().get(i).getId().equals(product.getId())) {
                for (int j = 0; j < categoryService.getAllCategory().size(); i++) {
                    if (categoryService.getAllCategory().get(i).getId().equals(product.getCategoryID())) {
                        oldProduct.setName(product.getName());
                        oldProduct.setPrice(product.getPrice());
                        oldProduct.setCategoryID(product.getCategoryID());
                        productRepository.save(oldProduct);
                        return "OK";
                    }


                }
                return "category id not fount";


            }
        }
        return "product id not found";
    }

    public Boolean deleteProduct(Integer id){

        Product product = productRepository.getById(id);
        if(product == null){
            return false;
        }
        productRepository.delete(product);
        return true;
    }




}



