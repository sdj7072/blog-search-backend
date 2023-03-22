package com.example.search.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Documents {

    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private Timestamp datetime; // ISO8601, [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]

}
