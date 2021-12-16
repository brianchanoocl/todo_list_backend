package com.example.todoList.controller;

import com.example.todoList.entity.TodoItem;
import com.example.todoList.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todos")
@CrossOrigin(origins = "localhost:3000")
public class TodoController {
    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public TodoItem getTodoItemById(@PathVariable String id) {
        return todoService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem){
        return todoService.create(todoItem);
    }

    @PutMapping("/{id}")
    public  TodoItem editTodoItem(@PathVariable String id, @RequestBody TodoItem todoItem){
        return todoService.edit(id, todoItem);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable String id){
        todoService.delete(id);
    }

}
