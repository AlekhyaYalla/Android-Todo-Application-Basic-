package com.example.ayalla.myapplication.views;

import com.example.ayalla.myapplication.models.TodoItem;

public interface TodoItemView {
    void displayItem(TodoItem todoItem);

    void markAsDone();

    void close();

    void delete();
}
