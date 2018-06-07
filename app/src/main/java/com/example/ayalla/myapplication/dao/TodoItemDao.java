package com.example.ayalla.myapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ayalla.myapplication.models.TodoItem;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TodoItemDao {

    @Query("SELECT * FROM TODOITEM")
    List<TodoItem> getAll();

    @Query("SELECT * FROM TODOITEM WHERE ISDONE = 1")
    List<TodoItem> getAllDone();

    @Query("SELECT * FROM TODOITEM WHERE ISDONE = 0")
    List<TodoItem> getAllNotDone();

    @Insert
    void insert(TodoItem item);

    @Delete
    void delete(TodoItem item);

    @Query("SELECT * FROM TODOITEM WHERE TITLE = :title LIMIT 1")
    TodoItem getItem(String title);

    @Update(onConflict = REPLACE)
    void updateIsDone(TodoItem item);


}
