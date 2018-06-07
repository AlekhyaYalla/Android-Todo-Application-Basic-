package com.example.ayalla.myapplication.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ayalla.myapplication.dao.TodoItemDao;
import com.example.ayalla.myapplication.models.TodoItem;

@Database(entities = {TodoItem.class}, version = 2)
public abstract class TodoItemDatabase extends RoomDatabase {
    public abstract TodoItemDao todoItemDao();
}

