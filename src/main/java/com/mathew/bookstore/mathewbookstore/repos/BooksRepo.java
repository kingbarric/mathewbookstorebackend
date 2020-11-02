package com.mathew.bookstore.mathewbookstore.repos;


import com.mathew.bookstore.mathewbookstore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<Books, String> {
    public Books findByIsbn(String isbn);
}

