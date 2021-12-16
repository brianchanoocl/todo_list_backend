package com.example.todoList.controller;

import com.example.todoList.entity.TodoItem;
import com.example.todoList.repository.TodoRepositoryNew;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    TodoRepositoryNew todoRepositoryNew;
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    void clearRepositoryBefore(){
        todoRepositoryNew.deleteAll();
    }
    @AfterEach
    void clearRepository(){
        todoRepositoryNew.deleteAll();
    }

    @Test
    void should_return_todo_items_when_perform_get_given_todo_items() throws Exception {
        //given
        TodoItem todoItem = new TodoItem("1","task 1",false);
        todoRepositoryNew.insert(todoItem);

        //When
        //then
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].text").value("task 1"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    void should_remove_todo_item_when_perform_delete_given_todo_items_and_id() throws Exception {
        //given
        TodoItem todoItem = new TodoItem("1","task 1",false);
        todoRepositoryNew.insert(todoItem);

        //When
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/"+todoItem.getId()))
                .andExpect(status().isNoContent());

        assertEquals(0,todoRepositoryNew.findAll().size());
    }
}
