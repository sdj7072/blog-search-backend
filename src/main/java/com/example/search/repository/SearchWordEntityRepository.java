package com.example.search.repository;

import com.example.search.model.entity.SearchWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchWordEntityRepository extends JpaRepository<SearchWordEntity, Integer> {
    Optional<SearchWordEntity> findBySearchWord(String searchWord);
    List<SearchWordEntity> findTop10ByOrderBySearchCountDesc();
}
