package com.chan.springbootpractice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Author {
    private int author_id;
    private String username;
    private String gender;
}
