package com.example.lab3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("select * from todoList")
    List<NoteBean> getNoteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteBean note);

    @Update()
    void update(NoteBean note);

    @Delete()
    void delete(NoteBean note);
}
