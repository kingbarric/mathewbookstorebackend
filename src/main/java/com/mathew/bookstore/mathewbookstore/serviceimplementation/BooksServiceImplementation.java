package com.mathew.bookstore.mathewbookstore.serviceimplementation;


import com.mathew.bookstore.mathewbookstore.model.Books;
import com.mathew.bookstore.mathewbookstore.repos.BooksRepo;
import com.mathew.bookstore.mathewbookstore.services.BookService;
import com.mathew.bookstore.mathewbookstore.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BooksServiceImplementation implements BookService {
    @Autowired
    private BooksRepo booksRepo;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // create a method that saves the book

    @Transactional
    @Override
    public ResponseEntity saveBook(Books book){
        ResponseMessage message = new ResponseMessage();
        if(book.getIsbn().isEmpty()){
            message.setCode(1);
            message.setMessage("ISBN can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if(book.getTitle().isEmpty()){
            message.setCode(1);
            message.setMessage("Book Title can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if( book.getCategory().isEmpty()){
            message.setCode(1);
            message.setMessage("Book Category can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if(book.getAuthor().isEmpty()){
            message.setCode(1);
            message.setMessage("Publisher can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if(StringUtils.isEmpty(book.getQuantity()+"")){
            message.setCode(1);
            message.setMessage("Book Quantity can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        booksRepo.save(book);
        message.setCode(0);
        message.setMessage("Book created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @Override
    @Transactional
    public ResponseEntity updateBook(Books book){
        ResponseMessage message = new ResponseMessage();
        if(book.getIsbn().isEmpty()){
            message.setCode(1);
            message.setMessage("ISBN can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if(book.getTitle().isEmpty()){
            message.setCode(1);
            message.setMessage("Book Title can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if( book.getCategory().isEmpty()){
            message.setCode(1);
            message.setMessage("Book Category can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if(book.getAuthor().isEmpty()){
            message.setCode(1);
            message.setMessage("Publisher can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if(StringUtils.isEmpty(book.getQuantity()+"")){
            message.setCode(1);
            message.setMessage("Book Quantity can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Books updatedBook = booksRepo.findByIsbn(book.getIsbn());
        if(updatedBook ==null){
            message.setCode(-3);
            message.setMessage("No such book to edit");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        booksRepo.save(updatedBook);
        message.setCode(0);
        message.setMessage("Book Updated successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


    @Override
    public List<Books> findAll(){
        return this.booksRepo.findAll();
    }

    @Override
    @Transactional
    public Books findByISBN(String isbn){
        try{
            return   this.booksRepo.findByIsbn(isbn);
        }catch (Exception e){
            System.out.println();
            return null;
        }
    }


    @Override
    public List<Books> searchBooks(@RequestBody Books search) {
        System.out.println(search);
        String sql= "SELECT * FROM books where ";

        sql +=" category ='"+search.getCategory()+"' ";

        sql +=" OR publisher ='"+search.getAuthor()+"' ";
        if(!StringUtils.isEmpty(search.getLowPrice()) && !StringUtils.isEmpty(search.getHighPrice()) ){
            sql +=" OR   price BETWEEN  "+search.getLowPrice()+"  AND "+search.getHighPrice()+" ";
        }
        System.out.println(sql);
        RowMapper<Books> rowMapper = new BeanPropertyRowMapper<>(Books.class);
        return jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    @Transactional
    public ResponseEntity delete(@PathVariable String id) {
        Books b = booksRepo.findByIsbn(id);
        booksRepo.delete(b);
        Map m = new HashMap();
        m.put("code",0);
        m.put("message","The Book was successfully deleted");
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }
}
