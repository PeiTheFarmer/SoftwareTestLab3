package com.example.lab3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {NoteBean.class}, version = 1, exportSchema = false)
@TypeConverters({ConversionFactory.class})
public abstract class NoteDatabase extends RoomDatabase {
    public static NoteDatabase getDefault(Context context) {
        return buildDatabase(context);
    }

    private static NoteDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "NOTE.db")
                .allowMainThreadQueries()
                .build();
    }

    public abstract NoteDao getNoteDao();
}
