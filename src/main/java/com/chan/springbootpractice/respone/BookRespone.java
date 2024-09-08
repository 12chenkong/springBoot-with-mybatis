package com.chan.springbootpractice.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRespone<T> {
  private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T  bookList;
    private Timestamp timestamp;
    private HttpStatus status;
}
