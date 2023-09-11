package com.cajun.mds.service;

import com.cajun.mds.domain.Item;
import com.cajun.mds.domain.Region;
import com.cajun.mds.dto.ItemDto;
import com.cajun.mds.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        Region r1 = new Region(11680,10300,"강남구","개포동");
        System.out.println(r1);
        ItemDto.Request request = ItemDto.Request.builder()
//                .memberPk()
                .price(35000)
                .region(r1)
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

    @Test
    @DisplayName("매물 1차 조회")
    void getItems(){
        Region r1 = new Region(11680,10300,"강남구","개포동");
        ItemDto.Request request = ItemDto.Request.builder()
//                .memberPk()
                .price(35000)
                .region(r1)
                .addressDetail("116-1403")
                .totalSquare(33.3)
                .unitSquare(28)
                .build();
        Region r2 = new Region(11680,10300,"강남구","개포동");
        ItemDto.Request request2 = ItemDto.Request.builder()
//                .memberPk()
                .price(550000)
                .region(r2)
                .addressDetail("116-1404")
                .totalSquare(33.3)
                .unitSquare(28)
                .build();
        itemService.register(request);
        itemService.register(request2);

        List<ItemDto.Response> items = itemService.getItems(11680, 10300);
        assertEquals(2L, items.size());
        assertEquals(35000, items.get(0).getPrice());
        assertEquals("116-1403", items.get(0).getAddressDetail());
        assertEquals("116-1404", items.get(1).getAddressDetail());

    }
}
