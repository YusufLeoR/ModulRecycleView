package com.example.modulrecycleview.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.modulrecycleview.db.TodoItem;
import com.example.modulrecycleview.db.TodoItemDAO;
import com.example.modulrecycleview.db.TodoItemDatabase;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoItemRepository {
    private TodoItemDAO todoItemDAO;
    private LiveData<List<TodoItem>> allTodoItems;
    private ExecutorService executorService;

    public TodoItemRepository(Context context) {
        TodoItemDatabase database = TodoItemDatabase.getInstance(context);
        todoItemDAO = database.todoItemDao();
        allTodoItems = todoItemDAO.getAllTodo();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<TodoItem>> getAllTodoItems() {
        return allTodoItems;
    }

    public void insert(TodoItem todoItem) {
        executorService.execute(() -> todoItemDAO.addTodo(todoItem));
    }

    public void update(TodoItem todoItem) {
        executorService.execute(() -> todoItemDAO.updateTodo(todoItem));
    }

    public void delete(TodoItem todoItem) {
        executorService.execute(() -> todoItemDAO.deleteTodo(todoItem));
    }

    public LiveData<TodoItem> getTodoItem(int id) {
        return todoItemDAO.getTodo(id);
    }
}
