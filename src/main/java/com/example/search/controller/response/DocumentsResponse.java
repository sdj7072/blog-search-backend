package com.example.search.controller.response;

import com.example.search.model.Documents;
import com.example.search.model.Items;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class DocumentsResponse {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private Timestamp datetime; // ISO8601, [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]

    public static DocumentsResponse fromDocuments(Documents documents) {
        return new DocumentsResponse(
                documents.getTitle(),
                documents.getContents(),
                documents.getUrl(),
                documents.getBlogname(),
                documents.getThumbnail(),
                documents.getDatetime()
        );
    }

    public static DocumentsResponse fromItems(Items items) {
        return new DocumentsResponse(
                items.getTitle(),
                items.getDescription(),
                items.getLink(),
                items.getBloggername(),
                null,
                items.getPostdate()
        );
    }
}
