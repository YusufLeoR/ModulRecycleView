package com.example.modulrecycleview.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.modulrecycleview.R;
import com.example.modulrecycleview.db.TodoItem;

public class AddDataActivity extends AppCompatActivity {
    private EditText etName, etDate, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_field);

        etName = findViewById(R.id.etNameAdd);
        etDate = findViewById(R.id.etDateAdd);
        etDescription = findViewById(R.id.etDescriptionAdd);

        Button btnSave = findViewById(R.id.btSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String date = etDate.getText().toString();
                String description = etDescription.getText().toString();

                TodoItem todoItem = new TodoItem(name, date, description);

                TodoItemViewModel todoItemViewModel = new TodoItemViewModel(getApplication());
                todoItemViewModel.insert(todoItem);

                finish();
            }
        });
    }
}
