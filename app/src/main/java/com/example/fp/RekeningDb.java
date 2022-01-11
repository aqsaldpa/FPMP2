package com.example.fp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Rekening.class},version = 1)

public abstract class RekeningDb extends RoomDatabase {

    public abstract RekeningDAO rekDAO();
}
