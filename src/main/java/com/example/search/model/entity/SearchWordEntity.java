package com.example.search.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "T_SEARCH_WORD")
@Getter
@Setter
public class SearchWordEntity {

    @Id
    @Column(name = "searchWord")
    private String searchWord;

    @Column(name = "searchCount")
    private Long searchCount;

    public static SearchWordEntity of(String searchWord, Long searchCount) {
        SearchWordEntity searchWordEntity = new SearchWordEntity();
        searchWordEntity.setSearchWord(searchWord);
        searchWordEntity.setSearchCount(searchCount);
        return searchWordEntity;
    }

}
