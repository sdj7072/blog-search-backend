package com.example.search.service;

import com.example.search.controller.request.SortType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class KakaoBlogAPIServiceTest {

    @Autowired
    private KakaoBlogAPIService kakaoBlogAPIService;

    @Test
    @Order(value = 1)
    @DisplayName("Service Test : Kakao API(Blog 검색) 호출 성공")
    void KAKAO_API_호출_성공() {
        String query = "query";
        String sort = SortType.ACCURACY.getKakaoSortType();
        int page = 1;
        int size = 10;

        Assertions.assertDoesNotThrow(() -> kakaoBlogAPIService.list(query, sort, page, size));
    }

    @Test
    @Order(value = 2)
    @DisplayName("Service Test : Kakao API(Blog 검색) 호출 실패, 필수 Parameter 누락")
    void KAKAO_API_호출_실패_필수_Parameter_누락() {
        String query = null;
        String sort = SortType.ACCURACY.getKakaoSortType();
        int page = 1;
        int size = 10;

        Assertions.assertThrows(Exception.class, () -> kakaoBlogAPIService.list(query, sort, page, size));
    }

    @Test
    @Order(value = 3)
    @DisplayName("Service Test : Kakao API(Blog 검색) 호출 실패, Parameter 오입력")
    void KAKAO_API_호출_실패_Parameter_오입력() {
        String query = null;
        String sort = SortType.ACCURACY.getNaverSortType();
        int page = 1;
        int size = 10;

        Assertions.assertThrows(Exception.class, () -> kakaoBlogAPIService.list(query, sort, page, size));
    }

}
