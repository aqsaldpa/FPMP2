package com.example.fp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fp.MyApp;
import com.example.fp.R;
import com.example.fp.Rekening;
import com.example.fp.RekeningDAO;

public class RegisterBank extends AppCompatActivity implements View.OnClickListener {
    EditText nama,norek,kota,umur;
    public final static String TAG_DATA_INTENT = "data_norek";
    private Rekening rekening;
    private RekeningDAO dao;
    private Button btnTambah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_bank);
        dao = MyApp.getInstance().getDb().rekDAO();

        if (getIntent() != null){
            int id = getIntent().getIntExtra(TAG_DATA_INTENT,0);
            rekening = dao.findById(id);
        }
        if (rekening == null) {
            rekening = new Rekening();
        }

        btnTambah = findViewById(R.id.btInsert);
        nama = findViewById(R.id.etNama);
        norek = findViewById(R.id.etNorek);
        kota = findViewById(R.id.etKota);
        umur = findViewById(R.id.etUmur);

        btnTambah.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btInsert:
                addOrUpdate();
                if (rekening.getId() == 0) {
                    dao.insertData(rekening);
                } else {
                    dao.update(rekening);
                }
                Toast.makeText(this, btnTambah.getText().toString(), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    private void addOrUpdate() {
        rekening.setNama(nama.getText().toString());
        rekening.setNorek(Integer.parseInt(norek.getText().toString()));
        rekening.setKota(kota.getText().toString());
        rekening.setUmur(umur.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rekening.getId()>0){
            nama.setText(rekening.getNama());
            norek.setText(String.valueOf(rekening.getNorek()));
            kota.setText(rekening.getKota());
            umur.setText(rekening.getUmur());

            btnTambah.setText("Ubah Data");
        }
    }
}