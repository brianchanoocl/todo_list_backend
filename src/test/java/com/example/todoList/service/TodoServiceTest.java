package com.example.todoList.service;

import com.example.todoList.entity.TodoItem;
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
import static org.mockito.BDDMockito.given;

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
}
