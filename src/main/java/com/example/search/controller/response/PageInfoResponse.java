package com.example.search.controller.response;

import com.example.search.util.Pagination;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class PageInfoResponse {
    private int totalRecordCount;
    private int totalPageCount;
    private int currentPage;
    private int startPage;
    private int endPage;
    private boolean existPrevPage;
    private boolean existNextPage;
    public static PageInfoResponse fromPageInfo(Pagination pagination) {
        return new PageInfoResponse(
                pagination.getTotalRecordCount(),
                pagination.getTotalPageCount(),
                pagination.getCurrentPage(),
                pagination.getStartPage(),
                pagination.getEndPage(),
                pagination.isExistPrevPage(),
                pagination.isExistNextPage()
        );
    }
}
