package com.example.search.service;

import com.example.search.model.Blog;
import com.example.search.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class KakaoBlogAPIService {

    @Value("${kakao.rest-api.uri}")
    private String REST_API_URI;

    @Value("${kakao.rest-api.path}")
    private String REST_API_PATH;

    @Value("${kakao.rest-api.key}")
    private String REST_API_KEY;

    NaverBlogAPIService naverBlogAPIService;

    public Blog list(String query, String sort, int page, int size) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();

        URI uri = UriComponentsBuilder.fromUriString(REST_API_URI)
                .path(REST_API_PATH)
                .queryParam("query", "{query}")
                .queryParam("sort", "{sort}")
                .queryParam("page", "{page}")
                .queryParam("size", "{size}")
                .encode()
                .build()
                .expand(query, sort, page, size)
                .toUri();

        header.set("Authorization", REST_API_KEY);

        ResponseEntity<Blog> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity(header), Blog.class);

        return setBlogWithPagination(responseEntity, page, size);

    }

    private Blog setBlogWithPagination(ResponseEntity<Blog> responseEntity, int page, int size) {

        Blog blog = responseEntity.getBody();
        blog.setPagination(new Pagination(blog.getMeta().getPageable_count(), page, size));

        return blog;
    }

}