package com.example.fp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class CrudActivity extends AppCompatActivity {
    private RecyclerView rvListRekening;
    private RecyclerViewAdapter adapter;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        rvListRekening = findViewById(R.id.rv_list_rekening);
        adapter = new RecyclerViewAdapter();
        rvListRekening.setAdapter(adapter);

        adapter.setRemoveListener(new DataListListener() {
            @Override
            public void onRemoveClick(Rekening rekening) {
                adapter.removeData(rekening);
            }
        });
    }
        @Override
        protected void onResume() {
            super.onResume();
            List<Rekening> rek = MyApp.getInstance().getDb().rekDAO().getAll();
            adapter.setData(rek);


    }
}