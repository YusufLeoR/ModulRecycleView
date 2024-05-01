package com.example.modulrecycleview.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modulrecycleview.R;
import com.example.modulrecycleview.db.TodoItem;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoItemAdapter todoAdapter;
    private TodoItemViewModel todoItemViewModel;
    private List<TodoItem> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvToDo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoAdapter = new TodoItemAdapter(new ArrayList<>());
        recyclerView.setAdapter(todoAdapter);

        Button addButton = findViewById(R.id.btAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
                startActivity(intent);
            }
        });

        todoItemViewModel = new ViewModelProvider(this).get(TodoItemViewModel.class);
        todoItemViewModel.getAllTodoItems().observe(this, todoItems -> {
            todoList = todoItems;
            todoAdapter.setTodoItems(todoList);
        });

        todoAdapter.setOnItemClickListener(new TodoItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TodoItem selectedItem = todoList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("todo_item", selectedItem);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                TodoItem selectedItem = todoList.get(position);
                todoItemViewModel.delete(selectedItem);
            }
        });
    }
}
