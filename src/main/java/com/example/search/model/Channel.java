package com.example.search.model;

import com.example.search.util.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Channel {

    private int total;
    private List<Items> Items;
    private Pagination pagination;

}
