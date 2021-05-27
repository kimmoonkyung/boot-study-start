package com.example.study.repository;

import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ItemRepositoryTest {

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void create() {

        Item item = new Item();
        item.setName("노트북");
        item.setPrice(1000000);
        item.setContent("엘지 그램");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(item);

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
