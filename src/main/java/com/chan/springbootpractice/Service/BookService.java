package com.chan.springbootpractice.Service;

import com.chan.springbootpractice.Repository.BookRepo;
import com.chan.springbootpractice.model.Book;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    BookRepo bookRepo;
     // contructor injection
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    public List<Book>getAllBooks(){
        return  bookRepo.getAllBooks();
    }

   public Optional<Book> saveBook(Book book){
        // if book already exist return null to controller
        try {
            bookRepo.saveBook(book);
        }catch (DuplicateKeyException ex){
            return  Optional.empty();
        }
       System.out.println("after catch call mee!!");
        return  Optional.ofNullable(bookRepo.getBookById(book.getBook_id()));
    }
    public Optional<Book> getBookById(int id){
      return Optional.ofNullable( bookRepo.getBookById(id)) ;
    }

   public Optional<Book> deleteBookById(int id){
      Book book= bookRepo.getBookById(id);
        bookRepo.deleteBookById(id);
        return Optional.ofNullable(book);
    }
    public Optional<Book> updateBookById(Book book,int id){
     bookRepo.updateBookById(id,book);
     return Optional.ofNullable(bookRepo.getBookById(id));
    }
}
