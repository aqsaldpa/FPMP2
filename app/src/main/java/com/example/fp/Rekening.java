package com.example.fp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Rekening {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "nama")
    private String nama = "";

    @ColumnInfo(name = "norek")
    private int norek;

    @ColumnInfo(name = "kota")
    private String kota;

    @ColumnInfo(name = "umur")
    private String umur = "";

    public Rekening() {
    }

    public Rekening(int id, String nama, int norek, String kota, String umur) {
        this.id = id;
        this.nama = nama;
        this.norek = norek;
        this.kota = kota;
        this.umur = umur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNorek() {
        return norek;
    }

    public void setNorek(int norek) {
        this.norek = norek;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }
}
