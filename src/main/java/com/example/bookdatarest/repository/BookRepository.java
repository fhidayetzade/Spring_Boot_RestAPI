package com.example.bookdatarest.repository;

import com.example.bookdatarest.model.Book;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
   List<Book> findBookByNameAndAuthor (String name, String author);
}
