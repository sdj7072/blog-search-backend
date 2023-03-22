package com.example.search.model;

import com.example.search.model.entity.SearchWordEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchWord {

    private String searchWord;
    private Long searchCount;

    public static SearchWord fromEntity(SearchWordEntity searchWordEntity) {
        return new SearchWord(
                searchWordEntity.getSearchWord(),
                searchWordEntity.getSearchCount()
        );
    }
}
