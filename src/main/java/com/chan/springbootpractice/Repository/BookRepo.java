package com.chan.springbootpractice.Repository;

import com.chan.springbootpractice.model.Author;
import com.chan.springbootpractice.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
@Mapper
public interface BookRepo {

    @Select("SELECT * FROM Book ")
    List<Book>getAllBooks();
    @Insert("INSERT INTO Book(book_id,book_title,Author_name,date) " +
            " VALUE(#{book.book_id},#{book.book_title},#{book.Author_name},#{book.date}) ")
    void saveBook(@Param("book") Book book);

    @Select("SELECT *FROM Book WHERE Book_id= #{id}")
    Book getBookById(int id);

    @Delete("DELETE FROM Book WHERE book_id=#{id}")
    void deleteBookById(int id);
    @Update("UPDATE Book SET book_title=#{book.book_title}, Author_name=#{book.Author_name} ,date=#{book.date} " +
            " WHERE book_id=#{id}")
    Author updateBookById(int id, @Param("book") Book book);


}
