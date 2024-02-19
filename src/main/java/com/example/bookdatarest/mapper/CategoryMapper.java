package com.example.bookdatarest.mapper;

import com.example.bookdatarest.model.Book;
import com.example.bookdatarest.model.Category;
import com.example.bookdatarest.rest.model.request.BookDto;
import com.example.bookdatarest.rest.model.request.CategoryRequest;
import com.example.bookdatarest.rest.model.response.BookResponseList;
import com.example.bookdatarest.rest.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id",ignore = true)
    Category mapRequestToCategory (CategoryRequest request);

    CategoryResponse categoryResponse (Category category);
}
