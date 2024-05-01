package com.example.modulrecycleview.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.modulrecycleview.db.TodoItem;
import com.example.modulrecycleview.repository.TodoItemRepository;
import java.util.List;

public class TodoItemViewModel extends AndroidViewModel {
    private TodoItemRepository repository;
    private LiveData<List<TodoItem>> allTodoItems;

    public TodoItemViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoItemRepository(application);
        allTodoItems = repository.getAllTodoItems();
    }

    public LiveData<List<TodoItem>> getAllTodoItems() {
        return allTodoItems;
    }

    public void insert(TodoItem todoItem) {
        repository.insert(todoItem);
    }

    public void update(TodoItem todoItem) {
        repository.update(todoItem);
    }

    public void delete(TodoItem todoItem) {
        repository.delete(todoItem);
    }

    public LiveData<TodoItem> getTodoItem(int id) {
        return repository.getTodoItem(id);
    }
}
