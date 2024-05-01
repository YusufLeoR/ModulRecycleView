package com.example.modulrecycleview.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao

public interface TodoItemDAO {
    @Insert
    public void addTodo(TodoItem todo);

    @Update
    public void updateTodo(TodoItem todo);

    @Delete
    public void deleteTodo(TodoItem todo);

    @Query("select * from Todo")
    public LiveData<List<TodoItem>> getAllTodo();

    @Query("SELECT * FROM Todo WHERE todo_id = :id")
    LiveData<TodoItem> getTodo(int id);
}
