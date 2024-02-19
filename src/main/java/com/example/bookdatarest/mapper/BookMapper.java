package com.example.bookdatarest.mapper;

import com.example.bookdatarest.model.Book;
import com.example.bookdatarest.repository.BookRepository;
import com.example.bookdatarest.rest.model.request.BookDto;
import com.example.bookdatarest.rest.model.request.UpdateBookRequest;
import com.example.bookdatarest.rest.model.response.BookResponse;
import com.example.bookdatarest.rest.model.response.BookResponseList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {


    @Mapping(target = "id",ignore = true)
    Book mapRequestToBook (BookDto bookDto);

    BookResponseList bookResponseList (Book book);

}
