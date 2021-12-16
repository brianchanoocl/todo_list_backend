package com.example.todoList.advice;

import com.example.todoList.exception.NoTodoItemFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalControllerAdvice {
    @ExceptionHandler({NoTodoItemFoundException.class})
    public ErrorResponse handleNotFound(Exception exception){
        return new ErrorResponse(404,"Entity not found.");
    }
}
