package com.example.bookdatarest.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {

    private int id;
    private String name;
    private String author;
    private double price;
}
