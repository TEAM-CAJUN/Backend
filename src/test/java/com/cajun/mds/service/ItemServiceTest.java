package com.cajun.mds.service;

import com.cajun.mds.domain.Item;
import com.cajun.mds.dto.ItemDto;
import com.cajun.mds.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void clean(){
        itemRepository.deleteAll();
    }
    @Test
    @DisplayName("매물 등록")
    void registerTest(){
        ItemDto.Request request = ItemDto.Request.builder()
//                .memberPk()
                .price(35000)
                .regionPk(1200)
                .addressDetail("116-1403")
                .totalSquare(33.3)
                .unitSquare(28)
                .build();

        itemService.register(request);

        Assertions.assertEquals(1L, itemRepository.count());
        Item item = itemRepository.findAll().get(0);
        assertEquals(0, item.getIsLoans());
        assertEquals(0, item.getIsPaper());
        assertEquals(0, item.getIsBpaper());
        assertEquals(35000, item.getPrice());
        System.out.println(item);
    }
}
