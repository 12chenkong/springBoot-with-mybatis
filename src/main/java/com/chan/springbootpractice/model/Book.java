package com.chan.springbootpractice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Builder
public class Book {
    private int  book_id;
    private String book_title;
    private String Author_name;
    private String date;


}
