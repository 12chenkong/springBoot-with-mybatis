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

    public ResponseEntity<AuthorRespone<Author>>saveAuthor(@RequestBody,@PathVariable int id){
        return null;
    }

}
