/**
 * @Author: Your name
 * @Date:   2023-03-24 17:39:57
 * @Last Modified by:   Your name
 * @Last Modified time: 2023-03-31 22:25:43
 */
package com.marketplace.Services;

import com.marketplace.Entity.Clothing;
import com.marketplace.Helpers.ClothingRequest;
import com.marketplace.Repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothingService {

    @Autowired
    private ClothingRepository repository;

    public List<Clothing> getAllClothes() {
        return repository.findAll();
    }

    public Clothing getClothByID(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Clothing> getAllClothesBySub(String subcat) {
        List<Clothing> all = getAllClothes();
        // List<Clothing> filteredList = new ArrayList<>();

        return all.stream().filter(cloth -> cloth.getSubcategory().equals(subcat))
                .collect(Collectors.toList());

        /*
         * for(Clothing cloth: all) { if(cloth.getSubcategory().equals(subcat)) {
         * filteredList.add(cloth); } }
         * 
         * return filteredList;
         */
    }

    public List<Clothing> saveProduct(Clothing cloth) {
        System.out.println("Inside the serice method " + cloth);

        repository.save(cloth);
        return getAllClothes();
    }

    public Clothing saveAndUpdateProduct(Clothing updated) {
        return repository.save(updated);
    }

    public List<Clothing> deleteProduct(int id) {
        repository.deleteById(id);
        return repository.findAll();
    }

    public List<Clothing> getUserUploads(String studentId) {
        return getAllClothes().stream()
                .filter(cloth -> cloth.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }
}