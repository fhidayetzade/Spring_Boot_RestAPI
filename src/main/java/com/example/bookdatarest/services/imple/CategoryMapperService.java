package com.example.bookdatarest.services.imple;

import com.example.bookdatarest.mapper.BookMapper;
import com.example.bookdatarest.mapper.CategoryMapper;
import com.example.bookdatarest.repository.BookRepository;
import com.example.bookdatarest.repository.CategoryRepository;
import com.example.bookdatarest.rest.model.request.BookDto;
import com.example.bookdatarest.rest.model.request.CategoryRequest;
import com.example.bookdatarest.rest.model.response.BookResponseList;
import com.example.bookdatarest.rest.model.response.CategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryMapperService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public void createCategory (CategoryRequest request){
        categoryRepository.save(categoryMapper.mapRequestToCategory(request));
    }

    public List<CategoryResponse> getAllCategoryResponseLists(){
        return categoryRepository.findAll()
                .stream().map(categoryMapper::categoryResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse findByCategoryId(int id){
        var category =  categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Book not find by id "+id));
        var mapperCategory = categoryMapper.categoryResponse(category);
        return mapperCategory;
    }



    public void deleteById(int id){
        var deleteCategory = categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Book not find by id "+id));
        categoryRepository.deleteById(deleteCategory.getId());
    }
}
