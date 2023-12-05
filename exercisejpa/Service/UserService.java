package com.example.exercisejpa.Service;


import com.example.exercisejpa.Model.User;
import com.example.exercisejpa.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
private  final MerchantStockService merchantStockService;
private final ProductService productService;


    public List<User> getAllUser(){

        return userRepository.findAll();
    }


    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id , User user){

        User oldUser = userRepository.getById(id);
        if(oldUser==null){
            return false;
        }


        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setBalance(user.getBalance());
        oldUser.setCountOfPurchases(user.getCountOfPurchases());



        userRepository.save(oldUser);
        return true;
    }
    public Boolean deleteUser(Integer id){

        User user = userRepository.getById(id);
        if(user == null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }



    public String buyProduct(Integer userId, Integer productId, Integer merchantId, Integer quantity) {


        for (int j = 0; j < getAllUser().size(); j++) {
            if (getAllUser().get(j).getId().equals(userId)) {
                for (int i = 0; i < merchantStockService.getAllMerchantStock().size(); i++) {
                    if (merchantStockService.getAllMerchantStock().get(i).getProductId().equals(productId) && merchantStockService.getAllMerchantStock().get(i).getMerchantId().equals(merchantId)) {
                        if (merchantStockService.getAllMerchantStock().get(i).getStock() > 0 && merchantStockService.getAllMerchantStock().get(i).getStock() >= quantity) {
                            merchantStockService.getAllMerchantStock().get(i).setStock((merchantStockService.getAllMerchantStock().get(i).getStock() - quantity));
                            if ((productService.getAllProduct().get(i).getPrice() * quantity) < getAllUser().get(j).getBalance()) {
                                getAllUser().get(j).setBalance(getAllUser().get(j).getBalance() - (productService.getAllProduct().get(i).getPrice() * quantity));
                               userRepository.saveAll(getAllUser());

                                return "OK";
                            }
                                 return "balance is not enough";

                            }
                        return "unavailable";

                        }
                    return "productId and merchantId not found";
                    }
                }
            }
        return "user id not found";

    }



}



