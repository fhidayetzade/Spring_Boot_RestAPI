package com.example.bookdatarest.services.imple;

import com.example.bookdatarest.mapper.BookMapper;
import com.example.bookdatarest.model.Book;
import com.example.bookdatarest.repository.BookRepository;
import com.example.bookdatarest.rest.model.request.BookDto;
import com.example.bookdatarest.rest.model.request.UpdateBookRequest;
import com.example.bookdatarest.rest.model.response.BookResponse;
import com.example.bookdatarest.rest.model.response.BookResponseList;
import com.example.bookdatarest.services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;



    @Override
    public BookResponse getBookResponseAll() {
        List<BookDto> employeeDtoList = bookRepository.findAll()
                .stream()
                .map(book -> convertToDto(book))
                .collect(Collectors.toList());

        return BookResponse.builder()
                .bookDtoResponseList(employeeDtoList)
                .build();
    }

    @Override
    public BookDto getBookDtoById(int id) {

        return bookRepository
                .findById(id)
                .map(book -> convertToDto(book))
                .orElseThrow();
    }

    @Override
    public BookResponse getBookResponseByNameByAuthor(String name, String author) {
        List<BookDto> bookDtoList = bookRepository.findBookByNameAndAuthor(name,author)
                .stream()
                .map(book -> convertToDto(book))
                .collect(Collectors.toList());
        return BookResponse.builder()
                .bookDtoResponseList(bookDtoList)
                .build();
    }

    @Override
    public void insertBookDto(BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto,book);
        bookRepository.save(book);
    }

    @Override
    public void update(BookDto bookDto, int id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Exception Id"));
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        bookRepository.save(book);

    }

    @Override
    public void delete(int id) {
        Book book =  bookRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Exception Delete"));
        bookRepository.delete(book);
    }

    @Override
    public void updateSomeBook(BookDto bookDto, int id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Exception"));
        if(bookDto.getName()!=null)
            book.setName(bookDto.getName());
        if (bookDto.getAuthor()!=null)
            book.setAuthor(bookDto.getAuthor());
        if (bookDto.getPrice()>0);
        book.setPrice(bookDto.getPrice());
        bookRepository.save(book);
    }

    private BookDto convertToDto(Book book){
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
        return bookDto;
    }
}
