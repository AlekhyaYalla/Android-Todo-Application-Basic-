package com.example.ayalla.myapplication.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ayalla.myapplication.databases.TodoItemDatabase;
import com.example.ayalla.myapplication.models.TodoItem;

import java.util.List;

public class TodoItemRepository {
    private final TodoItemDatabase db;

    public TodoItemRepository(Context applicationContext) {
        db = Room.databaseBuilder(applicationContext, TodoItemDatabase.class, "my application").
                allowMainThreadQueries().build();
    }

    public void saveTodoItem(TodoItem todoItem) {
        db.todoItemDao().insert(todoItem);
    }

    public List<TodoItem> getAll() {
        return db.todoItemDao().getAll();
    }

    public List<TodoItem> getAllDone() {
        return db.todoItemDao().getAllDone();
    }

    public List<TodoItem> getAllNotDone() {
        return db.todoItemDao().getAllNotDone();
    }


    public void updateIsDoneColumnForTheRecord(TodoItem item) {
        db.todoItemDao().updateIsDone(item);
    }

    public void deleteRecord(TodoItem item) {
        db.todoItemDao().delete(item);
    }


}
