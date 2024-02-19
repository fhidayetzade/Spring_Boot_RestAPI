package com.example.bookdatarest.services;

import com.example.bookdatarest.rest.model.request.BookDto;
import com.example.bookdatarest.rest.model.response.BookResponse;

public interface BookService {
     BookResponse getBookResponseAll ();
     BookDto getBookDtoById(int id);
     BookResponse getBookResponseByNameByAuthor(String name, String author);
     void insertBookDto(BookDto bookDto);
     void update(BookDto bookDto, int id);

     void delete(int id);

     void updateSomeBook(BookDto bookDto, int id);


}