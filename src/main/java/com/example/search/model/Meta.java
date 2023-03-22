package com.example.search.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {

    private int total_count;
    private int pageable_count;
    private Boolean is_end;

}
