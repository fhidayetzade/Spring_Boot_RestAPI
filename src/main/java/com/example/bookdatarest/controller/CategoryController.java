package com.example.bookdatarest.controller;

import com.example.bookdatarest.rest.model.request.CategoryRequest;
import com.example.bookdatarest.rest.model.response.CategoryResponse;
import com.example.bookdatarest.services.imple.CategoryMapperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryMapperService categoryMapperService;

    @GetMapping("/listAll")
    public List<CategoryResponse> categoryResponses(){
        return categoryMapperService.getAllCategoryResponseLists();
    }

    @PostMapping("/addCategory")
    public void insertCategory(@RequestBody CategoryRequest request){
        categoryMapperService.createCategory(request);
    }

    @GetMapping("/category/{id}")
    public CategoryResponse categoryByResponseId(@PathVariable("id") int id){
        return categoryMapperService.findByCategoryId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") int id){
        categoryMapperService.deleteById(id);
    }
}
