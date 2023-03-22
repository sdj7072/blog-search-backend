package com.example.search.controller;

import com.example.search.service.KakaoBlogAPIService;
import com.example.search.service.NaverBlogAPIService;
import com.example.search.service.SearchWordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(value = 1)
    @DisplayName("Controller Test : BLOG 검색 성공")
    void BLOG_검색_성공() throws Exception {
        mockMvc.perform(get("/api/v1/search/blog")
                        .contentType((MediaType.APPLICATION_JSON))
                        .param("query", "test")
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(value = 2)
    @DisplayName("Controller Test : BLOG 검색 실패, 4xx Client Error")
    void BLOG_검색_실패_4xxClientError() throws Exception {
        mockMvc.perform(get("/api/v1/search/blog")
                        .contentType((MediaType.APPLICATION_JSON))
                        .param("query", "")
                ).andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @Order(value = 3)
    @DisplayName("Controller Test : 인기검색어 조회 성공 ")
    void 인기검색어_조회_성공() throws Exception {
        mockMvc.perform(get("/api/v1/search/popularSearchWord")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print());
    }

}
