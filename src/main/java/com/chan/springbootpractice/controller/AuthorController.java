package com.chan.springbootpractice.controller;

import com.chan.springbootpractice.Service.AuthorService;
import com.chan.springbootpractice.model.Author;
import com.chan.springbootpractice.respone.AuthorRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorController {
    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/addAuthor")
    public ResponseEntity<AuthorRespone<Author>>getAllAuthors(){
        List<Author>author=authorService.getAllAuthors();
        AuthorRespone authorRespone=AuthorRespone.builder()
                .message("get authors successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .authorList(author)
                .build();
        return new ResponseEntity<>(authorRespone, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorRespone<Author>>getAuthorById(@PathVariable int id){
        Optional<Author> author=authorService.getAuthorById(id);
        if(author.isPresent()){
            AuthorRespone authorRespone=AuthorRespone.builder()
                    .message("get author by id  successfully")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.OK)
                    .authorList(author)
                    .build();
            return  new ResponseEntity<>(authorRespone, HttpStatus.OK);
        }else {
            AuthorRespone authorRespone=AuthorRespone.builder()
                    .message("fail to search student by id")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return  new ResponseEntity<>(authorRespone, HttpStatus.NOT_FOUND);
        }
    }
    // Aad author recode to author_table
    @PostMapping("/saveAuthor")
    public ResponseEntity<AuthorRespone<Author>>saveAuthor(@RequestBody Author author){
        Optional<Author>authorOptional=authorService.saveAuthor(author);
        if(authorOptional.isPresent()){
            AuthorRespone authorRespone=AuthorRespone.builder()
                    .message("add author successfully")
                    .status(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .authorList(authorOptional)
                    .build();
            return new ResponseEntity<>(authorRespone, HttpStatus.OK);
        }else{
            AuthorRespone authorRespone=AuthorRespone.builder()
                    .message("author with this id already exist!!!!")
                    .status(HttpStatus.valueOf(409))
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return new ResponseEntity<>(authorRespone, HttpStatus.valueOf(409));
        }
    }
    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<AuthorRespone<Author>>updateAuthor(@RequestBody Author author,@PathVariable int id){
        Optional<Author>author1=authorService.updateAuthor(author,id);
        System.out.println("called in update author");
        if(author1.isPresent()){
            AuthorRespone authorRespone=AuthorRespone.builder()
                    .message("update author successfully")
                    .status(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .authorList(author1)
                    .build();
            return new ResponseEntity<>(authorRespone,HttpStatus.OK);
        }else {
            AuthorRespone authorRespone=AuthorRespone.builder()
                    .message("author to be update does not exist!!!!")
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return new ResponseEntity<>(authorRespone,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<AuthorRespone<Author>>deleteById(@PathVariable int id){
        Optional<Author> author=authorService.deleteAuthorById(id);
        if(author.isPresent()){
            AuthorRespone authorRespone=AuthorRespone.builder()
                    .message("Delete book successfully")
                    .status(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .authorList(author)
                    .build();
            return new ResponseEntity<>(authorRespone, HttpStatus.OK);

        }else {
            AuthorRespone authorRespone=AuthorRespone.builder()
                    .message("Fail to delete author")
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return new ResponseEntity<>(authorRespone, HttpStatus.valueOf(404));

        }

    }

}

