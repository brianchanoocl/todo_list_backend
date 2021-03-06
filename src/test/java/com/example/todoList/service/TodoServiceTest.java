package com.example.todoList.service;

import com.example.todoList.entity.TodoItem;
import com.example.todoList.exception.NoTodoItemFoundException;
import com.example.todoList.repository.TodoRepositoryNew;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class TodoServiceTest {

    @Mock
    TodoRepositoryNew todoRepositoryNew;

    @InjectMocks
    TodoService todoService;

    @Test
    void should_return_all_todo_items_when_find_all_given_todo_items() {
        //given
        List<TodoItem> todoItems = Stream.of(new TodoItem("1","task 1",false))
                .collect(Collectors.toList());
        given(todoRepositoryNew.findAll())
                .willReturn(todoItems);
        //when
        List<TodoItem> actual =  todoService.findAll();
        //then
        assertEquals(todoItems,actual);
    }

    @Test
    void should_return_updated_when_edit_todo_item_given_updated_todo_item() {
        //given
        TodoItem todoItem = new TodoItem("1","task 1",false);
        TodoItem updatedTodoItem = new TodoItem("1","new name", false);
        given(todoRepositoryNew.findById(any()))
                .willReturn(java.util.Optional.of(todoItem));
        todoItem.setText(updatedTodoItem.getText());
        given(todoRepositoryNew.save(any(TodoItem.class)))
                .willReturn(todoItem);
        //When
        TodoItem actual = todoService.edit(todoItem.getId(),updatedTodoItem);
        //then
        verify(todoRepositoryNew).save(todoItem);
        assertEquals(actual,todoItem);
    }

    @Test
    void should_return_todo_item_when_create_todo_item_given_new_todo_item() {
        //given
        TodoItem todoItem = new TodoItem("1","task 1",false);
        given(todoRepositoryNew.insert(any(TodoItem.class)))
                .willReturn(todoItem);
        //When
        TodoItem actual = todoService.create(todoItem);
        //then
        assertEquals(actual,todoItem);
    }

    @Test
    void should_return_todo_item_when_find_by_id_given_todo_items_and_id() {
        //given
        TodoItem todoItem = new TodoItem("1","task 1",false);
        given(todoRepositoryNew.findById(any()))
                .willReturn(java.util.Optional.of(todoItem));

        //When
        TodoItem actual = todoService.findById(todoItem.getId());

        //then
        assertEquals(actual, todoItem);
    }

    @Test
    void should_remove_todo_item_when_delete_given_id() {
        //given
        TodoItem todoItem = new TodoItem("1","task 1",false);

        //When
        todoService.delete(todoItem.getId());
        //then
        verify(todoRepositoryNew).deleteById(todoItem.getId());

    }

    @Test
    void should_throw_exception_when_findById_given_todo_items_and_invalid_id() {
        //given
        String id = "999";
        TodoItem todoItem = new TodoItem("1","task 1",false);
        //when
        given(todoRepositoryNew.findById("999"))
                .willThrow(NoTodoItemFoundException.class);

        //then
        assertThrows(NoTodoItemFoundException.class, () -> todoService.findById(id));
    }
}
