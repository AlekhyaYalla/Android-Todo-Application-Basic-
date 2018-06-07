package com.example.ayalla.myapplication.views;

import com.example.ayalla.myapplication.models.TodoItem;

import java.util.List;

public interface TodoListMainView {
    void renderList(List<TodoItem> todoList);
}
