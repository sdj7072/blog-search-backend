package com.example.search.service;

import com.example.search.model.SearchWord;
import com.example.search.model.entity.SearchWordEntity;
import com.example.search.repository.SearchWordEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchWordService {
    private final SearchWordEntityRepository searchWordEntityRepository;

    public Optional<SearchWordEntity> searchWord(String searchWord) {
        return searchWordEntityRepository.findBySearchWord(searchWord);
    }

    @Transactional
    public void create(String searchWord) {
        searchWordEntityRepository.save(SearchWordEntity.of(searchWord, 1L));
    }

    @Transactional
    public void modify(String searchWord, Long searchCount) {
        searchWordEntityRepository.save(SearchWordEntity.of(searchWord, searchCount));
    }

    public void setPopularSearchWord(String searchWord) {
        Optional<SearchWordEntity> searchWordEntity = this.searchWord(searchWord);

        if(searchWordEntity.isPresent()) {
            this.modify(searchWord, searchWordEntity.get().getSearchCount() + 1L);
        } else {
            this.create(searchWord);
        }
    }

    public List<SearchWord> getPopularSearchWord() {
        return searchWordEntityRepository.findTop10ByOrderBySearchCountDesc().stream()
                .map(SearchWord::fromEntity)
                .collect(Collectors.toList());
    }

}
