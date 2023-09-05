package com.cajun.mds.controller;

import com.cajun.mds.dto.ItemDto;
import com.cajun.mds.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ItemRepository itemRepository;
    @BeforeEach
    void clean(){
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("")
    void registerTest() throws Exception{
        ItemDto.Request request = ItemDto.Request.builder()
//                .memberPk()
                .price(35000)
                .regionPk(1200)
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
}