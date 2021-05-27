package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void 생성() {

        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getType(), type);
        Assertions.assertEquals(newCategory.getTitle(), title);

    }

    @Test
    void 읽기() {
//        Optional<Category> optionalCategory = categoryRepository.findById(1L);
//
//        optionalCategory.ifPresent(category ->  {
//            System.out.println("## : " + category.getId());
//            System.out.println("## : " + category.getType());
//            System.out.println("## : " + category.getTitle());
//        });

        String type = "COMPUTER";

        Optional<Category> optionalCategory = categoryRepository.findByType("COMPUTER");
        optionalCategory.ifPresent(category ->  {
            Assertions.assertEquals(category.getType(), type);
            System.out.println("## : " + category.getId());
            System.out.println("## : " + category.getType());
            System.out.println("## : " + category.getTitle());
        });

    }

}