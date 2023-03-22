package com.example.search.model;

import com.example.search.util.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Blog {

    private String errorType;
    private String message;
    private Meta meta;
    private List<Documents> documents;
    private Pagination pagination;

}
