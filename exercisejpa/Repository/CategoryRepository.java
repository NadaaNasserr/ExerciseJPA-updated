package com.example.exercisejpa.Repository;

import com.example.exercisejpa.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
