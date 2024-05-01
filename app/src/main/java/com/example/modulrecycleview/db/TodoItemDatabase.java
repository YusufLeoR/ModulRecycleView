package com.example.modulrecycleview.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TodoItem.class}, version = 1)
public abstract class TodoItemDatabase extends RoomDatabase {
    private static TodoItemDatabase INSTANCE;

    public abstract TodoItemDAO todoItemDao();

    public static TodoItemDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodoItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TodoItemDatabase.class, "todo_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
