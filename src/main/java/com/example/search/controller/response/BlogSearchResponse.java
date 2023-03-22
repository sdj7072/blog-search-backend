package com.example.search.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BlogSearchResponse {
    private PageInfoResponse pageInfoResponse;
    private List<DocumentsResponse> documentsResponseList;

    public BlogSearchResponse() {}
}
