package com.example.todoList.exception;

public class NoTodoItemFoundException extends RuntimeException{
    public NoTodoItemFoundException() {
        super("No Todo Item Found");
    }
}
