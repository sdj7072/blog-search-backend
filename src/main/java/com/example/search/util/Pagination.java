package com.example.search.util;

import lombok.Getter;

@Getter
public class Pagination {

    private int totalRecordCount;   // 전체 데이터 수
    private int totalPageCount;     // 전체 페이지 수
    private int currentPage;        // 현재 페이지
    private int startPage;          // 첫 페이지 번호
    private int endPage;            // 끝 페이지 번호
    private boolean existPrevPage;  // 이전 페이지 존재 여부
    private boolean existNextPage;  // 다음 페이지 존재 여부

    public Pagination(int totalRecordCount, int page, int size) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.currentPage = page;
            this.calculation(totalRecordCount, page, size);
        }
    }

    private void calculation(int totalRecordCount, int page, int size) {

        // 전체 페이지 수
        //totalPageCount = (int) Math.ceil(totalRecordCount/size);
        totalPageCount = ((totalRecordCount - 1) / size) + 1;

        // 현재 페이지
        currentPage = page > totalPageCount ? totalPageCount : page;

        // 시작 페이지
        startPage = ((page - 1) / size) * size + 1;

        // 마지막 페이지
        endPage = startPage + size - 1;

        // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // 이전 페이지 존재 여부
        existPrevPage = startPage == 1 ? false : true;

        // 다음 페이지 존재 여부
        existNextPage = endPage == totalPageCount || endPage == 50 ? false : true;
    }

}
