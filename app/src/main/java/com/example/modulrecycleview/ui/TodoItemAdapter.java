package com.example.modulrecycleview.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modulrecycleview.R;
import com.example.modulrecycleview.db.TodoItem;
import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemAdapter.ViewHolder> {
    private List<TodoItem> todoItems;
    private OnItemClickListener listener;

    public TodoItemAdapter(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView dateTextView;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView);
            dateTextView = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            ImageButton deleteButton = itemView.findViewById(R.id.ibDelete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem item = todoItems.get(position);
        holder.nameTextView.setText(item.getName());
        holder.dateTextView.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
        notifyDataSetChanged();
    }
}
