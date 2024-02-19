package com.example.bookdatarest.controller;

import com.example.bookdatarest.rest.model.request.BookDto;
import com.example.bookdatarest.rest.model.response.BookResponse;
import com.example.bookdatarest.services.imple.BookServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
@Tag(name = "Book Controller", description = "Book Services")
public class BookController {

    private final BookServiceImpl bookService;



    @GetMapping("/all")
    public BookResponse bookResponse() {

        return bookService.getBookResponseAll();
    }

    @GetMapping("/book/{id}")
    public BookDto bookDtoById(@PathVariable ("id") int id){
        return bookService.getBookDtoById(id);
    }

    @GetMapping("/search")
    public BookResponse bookResponseByNameAndAuthor(@RequestParam String name, @RequestParam String author){
        return bookService.getBookResponseByNameByAuthor(name,author);
    }

    @PostMapping("/addBooks")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBook (@RequestBody BookDto bookDto){
        bookService.insertBookDto(bookDto);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody BookDto bookDto, @PathVariable("id") int id){
        bookService.update(bookDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable ("id") int id){
            bookService.delete(id);
    }

    @PatchMapping("/{id}")
    public void updateSome(@RequestBody BookDto bookDto, @PathVariable int id){
        bookService.update(bookDto,id);
    }
}
