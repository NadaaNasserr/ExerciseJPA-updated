package com.example.exercisejpa.Service;


import com.example.exercisejpa.Model.Merchant;
import com.example.exercisejpa.Repository.MerchantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class MerchantService {

    private final MerchantRepository merchantRepository;



    public List<Merchant> getAllMerchant(){
        return merchantRepository.findAll();
    }


    public void addMerchant(Merchant merchant){
        merchantRepository.save(merchant);
    }

    public Boolean updateMerchant(Integer id , Merchant merchant){

        Merchant oldMerchant = merchantRepository.getById(id);
        if(oldMerchant==null){
            return false;
        }


        oldMerchant.setName(merchant.getName());



        merchantRepository.save(oldMerchant);
        return true;
    }
    public Boolean deleteMerchant(Integer id){

        Merchant merchant = merchantRepository.getById(id);
        if(merchant == null){
            return false;
        }
        merchantRepository.delete(merchant);
        return true;
    }



}


