package com.chan.springbootpractice.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorRespone<T> {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T  authorList;
    private Timestamp timestamp;
    private HttpStatus status;
}
