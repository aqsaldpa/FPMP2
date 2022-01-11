package com.example.fp;

import android.app.Application;

import androidx.room.Room;

public class MyApp extends Application {
    public static MyApp INSTANCE;
    public static RekeningDb db;
    public static MyApp getInstance(){return INSTANCE;}

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(this,
                RekeningDb.class,"db_rekening")
                .allowMainThreadQueries()
                .build();
        INSTANCE = this;
    }
    public RekeningDb getDb(){return db;}
}
