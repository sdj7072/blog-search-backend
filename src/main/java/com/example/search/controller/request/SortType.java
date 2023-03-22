package com.example.search.controller.request;

import lombok.Getter;

@Getter
public enum SortType {

    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date");

    private String kakaoSortType;
    private String naverSortType;

    SortType(String kakaoSortType, String naverSortType) {
        this.kakaoSortType = kakaoSortType;
        this.naverSortType = naverSortType;
    }
}
