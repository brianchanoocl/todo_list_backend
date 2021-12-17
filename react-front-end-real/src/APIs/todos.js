import api from './api';

export const getTodos = () => {
    return api.get("/todos");
}

export const addTodoItem = (item) => {
    return api.post("/todos", item);
}

export const deleteTodoItem = (id) => {
    return api.delete("/todos/" + id);
}

export const toggleTodoItemDoneStatus = (item) => {
    return api.put("/todos/" + item.id, item);
}

export const updateTodoItenDetail = (item) => {
    return api.put("/todos/" + item.id, item);
}