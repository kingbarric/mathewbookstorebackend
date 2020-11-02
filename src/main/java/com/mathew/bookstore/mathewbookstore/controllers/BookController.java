package com.mathew.bookstore.mathewbookstore.controllers;

import com.mathew.bookstore.mathewbookstore.model.Books;
import com.mathew.bookstore.mathewbookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("bookstore/")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(value = "/add")
    public ResponseEntity saveBook(@RequestBody final Books book) {
        return bookService.saveBook(book);
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateBook(@RequestBody final Books book) {
        return bookService.updateBook(book);
    }

    @GetMapping(value = "/findall")
    public ResponseEntity findAllBook() {
        return  ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping(value = "/findone/{isbn}")
    public Books findById(@PathVariable String isbn){
        return bookService.findByISBN(isbn);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        return   bookService.delete(id);

    }

    @PostMapping(value = "/search")
    public List<Books> searchBooks(@RequestBody Books search) {
        return bookService.searchBooks(search);
    }
}
