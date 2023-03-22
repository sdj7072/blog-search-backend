package com.example.search.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Items {

    private String title;
    private String description;
    private String link;
    private String bloggername;
    private Timestamp postdate; // ISO8601, [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
}
