package com.example.bookdatarest.rest.model.request;

import lombok.*;

@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private int id;
    private String name;
    private String author;
    private double price;

}
