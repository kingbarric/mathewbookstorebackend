package com.mathew.bookstore.mathewbookstore.services;

import com.mathew.bookstore.mathewbookstore.model.Books;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {
    public ResponseEntity saveBook(Books book);
    public ResponseEntity updateBook(Books book);
    public List<Books> findAll();
    public List<Books> searchBooks(@RequestBody Books search);
    public ResponseEntity delete(@PathVariable String id);
    public Books findByISBN(String isbn);
}
