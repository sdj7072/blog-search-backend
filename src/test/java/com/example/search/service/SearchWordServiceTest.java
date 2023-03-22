package com.example.search.service;

import com.example.search.exception.ErrorCode;
import com.example.search.exception.SearchApplicationException;
import com.example.search.model.SearchWord;
import com.example.search.model.entity.SearchWordEntity;
import com.example.search.repository.SearchWordEntityRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class SearchWordServiceTest {

    @Autowired
    private SearchWordService searchWordService;

    private SearchWordEntityRepository searchWordEntityRepository;

    @Test
    @Order(value = 1)
    @DisplayName("Service Test : 인기검색어 등록 성공")
    void 인기검색어_등록_성공() {
        String searchWord = "test";

        searchWordService.setPopularSearchWord(searchWord);
        Optional<SearchWordEntity> searchWordEntity = searchWordService.searchWord(searchWord);

        Assertions.assertEquals(searchWord, searchWordEntity.get().getSearchWord());
        Assertions.assertEquals(1, searchWordEntity.get().getSearchCount());

    }

    @Test
    @Order(value = 2)
    @DisplayName("Service Test : 인기검색어 등록 실패_인기검색어 중복 등록 시도")
    void 인기검색어_등록_실패() {
        String searchWord = "test1";
        Long searchCount = 1L;

        SearchApplicationException e = Assertions.assertThrows(SearchApplicationException.class, () -> searchWordService.modify(searchWord, searchCount));

        Assertions.assertEquals(ErrorCode.DUPLICATED_SEARCH_WORD, e.getErrorCode());

    }

    @Test
    @Order(value = 3)
    @DisplayName("Service Test : 인기검색어 증분 성공")
    void 인기검색어_증분_성공() {
        String searchWord = "test";

        searchWordService.setPopularSearchWord(searchWord);
        Optional<SearchWordEntity> searchWordEntity = searchWordService.searchWord(searchWord);

        Assertions.assertEquals(searchWord, searchWordEntity.get().getSearchWord());
        Assertions.assertEquals(2, searchWordEntity.get().getSearchCount());

    }

    @Test
    @Order(value = 4)
    @DisplayName("Service Test : 인기검색어 목록 조회 성공")
    void 인기검색어_목록_조회_성공() {
        List<SearchWord> searchWordList = searchWordService.getPopularSearchWord();

        Assertions.assertEquals(10, searchWordList.size());

    }

}
