package com.chan.springbootpractice.controller;

import com.chan.springbootpractice.Service.BookService;
import com.chan.springbootpractice.model.Book;
import com.chan.springbootpractice.respone.BookRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
     // Show all of books in the table
    @GetMapping("/fetch")
    public ResponseEntity<BookRespone<List<Book>>>getAllBooks(){
        System.out.println("Called now!");
        List<Book>listOfBooks=bookService.getAllBooks();
        BookRespone bookRespone= BookRespone.builder().message("Get books successfully")
                .status(HttpStatus.valueOf(200))
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .bookList(listOfBooks)
                .build();
        return new ResponseEntity<>(bookRespone,HttpStatus.valueOf(200));
    }

     @PostMapping("/save")
     public ResponseEntity<BookRespone<Book>>saveBook(@RequestBody Book book){
        Optional<Book> book1=bookService.saveBook(book);
        BookRespone bookRespone;
        ResponseEntity responseEntity;
        if(book1.isPresent()){
            bookRespone=BookRespone.builder()
                    .message("Add book successfully")
                    .status(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .bookList(book1)
                    .build();
             responseEntity=new ResponseEntity<>(bookRespone,HttpStatus.OK);
        }else{
            bookRespone=BookRespone.builder()
                    .message("Book with is id already exist...")
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            responseEntity= new ResponseEntity<>(bookRespone,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
     }
    public void SaveBook(@RequestBody Book book){
        bookService.saveBook(book);


     }
     // get book with certain id
    @GetMapping("/search/{id}")
     public ResponseEntity<BookRespone<Book>>getBookById(@PathVariable int id){
        Optional<Book> book=bookService.getBookById(id);
        BookRespone bookRespone;
        System.out.println("value of returning: "+book);
        if(book.isPresent()){
            bookRespone= BookRespone.builder()
                    .message("get Book by id successfully")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.valueOf(200))
                    .bookList(book)
                    .build();
            return new ResponseEntity<>(bookRespone,HttpStatus.valueOf(200));
        }else {
            bookRespone= BookRespone.builder()
                    .message("This book with id does not exist")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.valueOf(200))
                    .build();
            return new ResponseEntity<>(bookRespone,HttpStatus.valueOf(200));
        }

     }
    @DeleteMapping("/delete/{id}")
     public ResponseEntity<BookRespone<Book>>DeleteById(@PathVariable int id){
        Optional<Book>book=bookService.deleteBookById(id);
        if(book.isPresent()){
            System.out.println("called in if");
            BookRespone  bookRespone=BookRespone.builder()
                    .message("Delete book Successfully")
                    .status(HttpStatus.OK)
                    .bookList(book)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return new ResponseEntity<>(bookRespone,HttpStatus.OK);
        }else {
            BookRespone  bookRespone=BookRespone.builder()
                    .message("book with this id does not exi")
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return new ResponseEntity<>(bookRespone,HttpStatus.NOT_FOUND);

        }

     }

     @PutMapping("/update/{id}")
     public ResponseEntity<BookRespone<Book>>updateBookById(@PathVariable int id,@RequestBody Book book){
        Optional<Book> book1=bookService.updateBookById(book,id);
         BookRespone bookRespone;
         if(book1.isPresent()){
             bookRespone=BookRespone.builder()
                     .message("Update book successfully")
                     .timestamp(new Timestamp(System.currentTimeMillis()))
                     .status(HttpStatus.OK)
                     .bookList(book)
                     .build();
             return new ResponseEntity<>(bookRespone,HttpStatus.OK);
         }else {
             bookRespone=BookRespone.builder()
                     .message("Update book not successfully")
                     .timestamp(new Timestamp(System.currentTimeMillis()))
                     .status(HttpStatus.NOT_FOUND)
                     .build();
             return new ResponseEntity<>(bookRespone,HttpStatus.NOT_FOUND);
         }

    }




}
