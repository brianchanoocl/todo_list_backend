package com.example.todoList.service;

import com.example.todoList.entity.TodoItem;
import com.example.todoList.exception.NoTodoItemFoundException;
import com.example.todoList.repository.TodoRepositoryNew;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepositoryNew todoRepositoryNew;

    public TodoService(TodoRepositoryNew todoRepositoryNew) {
        this.todoRepositoryNew = todoRepositoryNew;
    }

    public List<TodoItem> findAll() {
        return todoRepositoryNew.findAll();
    }

    public TodoItem edit(String id, TodoItem updatedTodoItem) {
        TodoItem todoItem = findById(id);

//        if(updatedTodoItem.getText() != null){
//            todoItem.setText(updatedTodoItem.getText());
//        }


        if(updatedTodoItem.getText().equals(todoItem.getText())){
            todoItem.setDone(!updatedTodoItem.isDone());
        } else {
            todoItem.setText(updatedTodoItem.getText());
        }

        return todoRepositoryNew.save(todoItem);
    }

    public TodoItem create(TodoItem todoItem) {
        return todoRepositoryNew.insert(todoItem);
    }

    public TodoItem findById(String id) {
        return todoRepositoryNew.findById(id).orElseThrow(NoTodoItemFoundException::new);
    }

    public void delete(String id) {
        todoRepositoryNew.deleteById(id);
    }
}
