package com.chan.springbootpractice.Repository;

import com.chan.springbootpractice.model.Author;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Repository
@Mapper
public interface AuthorRepo {
    // see all author from author_table
    @Select("SELECT * FROM Author_table")
    List<Author>getAllAuthors();
    //insert new author to table
    @Insert("INSERT INTO Author_table (author_id,username,gender)" +
            " VALUES(#{au.author_id},#{au.username},#{au.gender}) ")
    void saveAuthor(@Param("au") Author author);
    // get author by id
    @Select("SELECT * FROM author_table WHERE author_id=#{id} ")
    Author getAuthorById(int id);
    //update author info by her or her id
    @Update("UPDATE author_table SET author_id=#{au.author_id},username=#{au.username},gender=#{au.gender} ")
    void updateAuthorById(@Param("au") Author author);




}
