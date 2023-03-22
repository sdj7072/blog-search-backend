package com.example.search.service;

import com.example.search.model.Channel;
import com.example.search.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class NaverBlogAPIService {

    @Value("${naver.rest-api.uri}")
    private String REST_API_URI;

    @Value("${naver.rest-api.path}")
    private String REST_API_PATH;

    @Value("${naver.rest-api.client-id}")
    private String REST_API_CLIENT_ID;

    @Value("${naver.rest-api.client-secret}")
    private String REST_API_CLIENT_SECRET;

    public Channel list(String query, String sort, int start, int display) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();

        URI uri = UriComponentsBuilder.fromUriString(REST_API_URI)
                .path(REST_API_PATH)
                .queryParam("query", "{query}")
                .queryParam("sort", "{sort}")
                .queryParam("start", "{start}")
                .queryParam("display", "{display}")
                .encode()
                .build()
                .expand(query, sort, start, display)
                .toUri();

        header.set("X-Naver-Client-Id", REST_API_CLIENT_ID);
        header.set("X-Naver-Client-SECRET", REST_API_CLIENT_SECRET);

        ResponseEntity<Channel> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity(header), Channel.class);

        return setChannelWithPagination(responseEntity, start, display);
    }

    private Channel setChannelWithPagination(ResponseEntity<Channel> responseEntity, int page, int size) {

        Channel channel = responseEntity.getBody();
        channel.setPagination(new Pagination(channel.getTotal(), page, size));

        return channel;
    }

}