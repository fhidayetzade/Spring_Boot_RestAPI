package com.example.bookdatarest.rest.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseList {

    private int id;
    private String name;
    private String author;
    private double price;
}
