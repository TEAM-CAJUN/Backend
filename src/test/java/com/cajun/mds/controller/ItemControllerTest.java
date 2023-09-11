package com.cajun.mds.controller;

import com.cajun.mds.domain.Item;
import com.cajun.mds.domain.Region;
import com.cajun.mds.dto.ItemDto;
import com.cajun.mds.repository.ItemRepository;
import com.cajun.mds.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @BeforeEach
    void clean(){
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("")
    void postTest() throws Exception{
        Region r1 = new Region(11680,10300,"강남구","개포동");

        ItemDto.Request request = ItemDto.Request.builder()
//                .memberPk()
                .price(35000)
                .region(r1)
                .addressDetail("116-1403")
                .totalSquare(33.3)
                .unitSquare(28)
                .build();
        String json = objectMapper.writeValueAsString(request);
        System.out.println(json);
        mockMvc.perform(post("/item/register")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    @DisplayName("매물 1차 조회")
    void getItems() throws Exception{
        Region r1 = new Region(11680,10300,"강남구","개포동");
        Item item1 = Item.builder()
//                .memberPk()
                .price(35000)
                .region(r1)
                .addressDetail("116-1403")
                .totalSquare(33.3)
                .unitSquare(28)
                .build();
        Region r2 = new Region(11680,10300,"강남구","개포동");
        Item item2 = Item.builder()
//                .memberPk()
                .price(550000)
                .region(r2)
                .addressDetail("116-1404")
                .totalSquare(33.3)
                .unitSquare(28)
                .build();
        itemRepository.saveAll(Arrays.asList(item1, item2));

        mockMvc.perform(get("/item/get?gu=11680&dong=10300")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].region.gu").value("강남구"))
                .andExpect(jsonPath("$[0].region.dong").value("개포동"))
                .andExpect(jsonPath("$[0].addressDetail").value("116-1403"))
                .andDo(print());

    }
}