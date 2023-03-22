package com.example.search.controller;

import com.example.search.controller.request.Platform;
import com.example.search.controller.request.SortType;
import com.example.search.controller.response.BlogSearchResponse;
import com.example.search.controller.response.DocumentsResponse;
import com.example.search.controller.response.PageInfoResponse;
import com.example.search.controller.response.Response;
import com.example.search.model.*;
import com.example.search.service.KakaoBlogAPIService;
import com.example.search.service.NaverBlogAPIService;
import com.example.search.service.SearchWordService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final KakaoBlogAPIService kakaoBlogAPIService;
    private final NaverBlogAPIService naverBlogAPIService;
    private final SearchWordService searchWordService;

    @GetMapping("/blog")
    public Response<BlogSearchResponse> list(@RequestParam(name = "platform", required = false, defaultValue = "KAKAO") Platform platform,
                                             @RequestParam(name = "query", required = true) String query,
                                             @RequestParam(name = "sort", required = false, defaultValue = "ACCURACY") SortType sortType,
                                             @RequestParam(name = "page", required = false, defaultValue = "1") @Min(1) @Max(50) int page,
                                             @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) @Max(50) int size) {

        searchWordService.setPopularSearchWord(query);

        BlogSearchResponse blogSearchResponse = new BlogSearchResponse();

        try {
            blogSearchResponse = this.callBlogAPIService(platform, query, sortType, page, size);
        } catch (HttpServerErrorException e) {
            if(platform.name() == "KAKAO") {
                blogSearchResponse = this.callBlogAPIService(Platform.NAVER, query, sortType, page, size);
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }

        return Response.success(blogSearchResponse);

    }

    @GetMapping("/popularSearchWord")
    public ResponseEntity<List<SearchWord>> getPopularSearchWord() {
        return ResponseEntity.ok()
                .body(searchWordService.getPopularSearchWord());

    }

    private BlogSearchResponse callBlogAPIService(Platform platform, String query, SortType sortType, int page, int size) {
        BlogSearchResponse blogSearchResponse = new BlogSearchResponse();
        List<DocumentsResponse> documentsResponseList = new ArrayList<DocumentsResponse>();

        if(platform.name() == "KAKAO") {
            Blog blog = kakaoBlogAPIService.list(query, sortType.getKakaoSortType(), page, size);

            for (Documents documents : blog.getDocuments()) {
                documentsResponseList.add(DocumentsResponse.fromDocuments(documents));
            }

            blogSearchResponse.setPageInfoResponse(PageInfoResponse.fromPageInfo(blog.getPagination()));
            blogSearchResponse.setDocumentsResponseList(documentsResponseList);

        } else if(platform.name() == "NAVER") {
            Channel channel = naverBlogAPIService.list(query, sortType.getNaverSortType(), page, size);

            for (Items items : channel.getItems()) {
                documentsResponseList.add(DocumentsResponse.fromItems(items));
            }

            blogSearchResponse.setPageInfoResponse(PageInfoResponse.fromPageInfo(channel.getPagination()));
            blogSearchResponse.setDocumentsResponseList(documentsResponseList);
        }

        return blogSearchResponse;
    }
}
