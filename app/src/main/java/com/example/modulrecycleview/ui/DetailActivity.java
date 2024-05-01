package com.example.modulrecycleview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.modulrecycleview.R;
import com.example.modulrecycleview.db.TodoItem;

public class DetailActivity extends AppCompatActivity {
    private TodoItemViewModel todoItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        todoItemViewModel = new ViewModelProvider(this).get(TodoItemViewModel.class);

        Intent intent = getIntent();
        int todoItemId = intent.getIntExtra("todo_item_id", -1);

        todoItemViewModel.getTodoItem(todoItemId).observe(this, todoItem -> {
            if (todoItem != null) {
                TextView nameTextView = findViewById(R.id.tvName);
                TextView dateTextView = findViewById(R.id.tvDate);
                TextView descriptionTextView = findViewById(R.id.tvDescription);

                nameTextView.setText(todoItem.getName());
                dateTextView.setText(todoItem.getDate());
                descriptionTextView.setText(todoItem.getDescription());
            }
        });
    }
}
