package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.enumclass.ItemStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;


public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {

        Item item = new Item();
        item.setStatus(ItemStatus.UNREGISTERED);
        item.setName("삼성 노트북");
        item.setTitle("갤럭시 북");
//        item.setPrice(2000000);
        item.setContent("2021년 형");
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
//        item.setCreatedAt(LocalDateTime.now());
//        item.setCreatedBy("Partner01");
//        item.setPartner();

        Item newItem = itemRepository.save(item);
        System.out.println(newItem);
        Assertions.assertNotNull(newItem);

    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assertions.assertTrue(item.isPresent());

        item.ifPresent(i -> {
            System.out.println(i);
        });
    }

}
