package com.chan.springbootpractice.Service;

import com.chan.springbootpractice.Repository.AuthorRepo;
import com.chan.springbootpractice.model.Author;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<Author>getAllAuthors(){
        return  authorRepo.getAllAuthors();
    }

    public Optional<Author>saveAuthor(Author author){
        try {
            authorRepo.saveAuthor(author);
        }catch (DuplicateKeyException ex){
            return Optional.empty();
        }
        return Optional.ofNullable(authorRepo.getAuthorById(author.getAuthor_id()));
    }

    public Optional<Author>getAuthorById(int id){
       return Optional.ofNullable(authorRepo.getAuthorById(id));
    }

}
