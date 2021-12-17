package com.example.todoList.controller;

import com.example.todoList.entity.TodoItem;
import com.example.todoList.repository.TodoRepositoryNew;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void should_return_todo_item_when_perform_post_given_todo_item() throws Exception {
        //given
        String todoItem = "{\n" +
                "    \"text\":\"task 1\",\n" +
                "    \"done\":false\n" +
                "}";
        //When
        //then
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON).content(todoItem))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.text").value("task 1"))
                .andExpect(jsonPath("$.done").value(false));

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

    @Test
    void should_return_todo_item_when_perform_put_on_text_given_id_and_todo_item_with_different_text() throws Exception {
        //given
        TodoItem todoItem = new TodoItem("1","task 1",false);
        todoRepositoryNew.insert(todoItem);
        String updatedTodoItem = "{\n" +
                "    \"text\":\"new task\",\n" +
                "    \"done\":false\n" +
                "}";
        //When
        //then
        mockMvc.perform(put("/todos/{id}",todoItem.getId())
                .contentType(MediaType.APPLICATION_JSON).content(updatedTodoItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("new task"))
                .andExpect(jsonPath("$.done").value(false));

    }

    @Test
    void should_return_todo_item_when_perform_put_on_done_status_given_id_and_todo_item_with_different_done_status() throws Exception {
        //given
        TodoItem todoItem = new TodoItem("1","task 1",false);
        todoRepositoryNew.insert(todoItem);
        String updatedTodoItem = "{\n" +
                "    \"text\":\"task 1\",\n" +
                "    \"done\":true\n" +
                "}";
        //When
        //then
        mockMvc.perform(put("/todos/{id}",todoItem.getId())
                .contentType(MediaType.APPLICATION_JSON).content(updatedTodoItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("task 1"))
                .andExpect(jsonPath("$.done").value(true));

    }
}
