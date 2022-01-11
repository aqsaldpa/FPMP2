package com.example.fp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RekeningDAO {

    @Query("SELECT * FROM Rekening")
    List<Rekening> getAll();


    @Query("SELECT * FROM Rekening WHERE id LIKE :rekeningId LIMIT 1")
    Rekening findById(int rekeningId);

    @Insert
    void insertData(Rekening mahasiswa);

    @Update
    void update(Rekening mahasiswa);

    @Delete
    void delete(Rekening mahasiswa);

}
