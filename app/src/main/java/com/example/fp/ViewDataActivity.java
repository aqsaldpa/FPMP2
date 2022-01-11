package com.example.fp;

import static com.example.fp.MyApp.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ViewDataActivity extends AppCompatActivity {
    RecyclerView myRecyclerview;
    RecyclerViewAdapter recycleAdapter;
    List<Rekening> listMahasiswas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        myRecyclerview = findViewById(R.id.myRecyclerview);

        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();
    }

    private void fetchDataFromRoom() {

        db = Room.databaseBuilder(getApplicationContext(),
                RekeningDb.class, "mahasiswa").allowMainThreadQueries().build();
        listMahasiswas = db.rekDAO().getAll();

        //just checking data from db
        for (int i = 0 ;i <listMahasiswas.size();i++){
            Log.e("Aplikasi",listMahasiswas.get(i).getNama()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getUmur()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getKota()+i);
            Log.e("Aplikasi", String.valueOf(Integer.valueOf(listMahasiswas.get(i).getNorek()+i)));
        }
    }
    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);
    }
    private void setAdapter() {
        myRecyclerview.setAdapter(recycleAdapter);
    }




}