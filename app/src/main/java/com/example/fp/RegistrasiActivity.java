package com.example.fp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrasiActivity extends AppCompatActivity {
    Button Register;
    EditText user, mail, etpw, etcpw;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        user = findViewById(R.id.etUsername);
        mail = findViewById(R.id.etEmail);
        etpw = findViewById(R.id.etPassword);
        etcpw = findViewById(R.id.etConfirm);
        Register = findViewById(R.id.btnRegister);
        preferences = getSharedPreferences("UserInfo",0);

        Register.setOnClickListener(v ->{
            String uservalue = user.getText().toString();
            String emailvalue = mail.getText().toString();
            String passvalue = etpw.getText().toString();
            String confirmpass = etcpw.getText().toString();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Username", uservalue);
            editor.putString("Email", emailvalue);
            editor.putString("Password",passvalue);
            editor.putString("Confirm Password",confirmpass);
            editor.commit();
            editor.apply();


            if (uservalue.length()==0) {
                Toast.makeText(RegistrasiActivity.this, "Masukkan Username", Toast.LENGTH_SHORT).show();
            }
            else if (emailvalue.length() == 0) {
                Toast.makeText(RegistrasiActivity.this, "Masukkan Email", Toast.LENGTH_SHORT).show();
            }
            else if (etpw.getText().toString().length() == 0 && (etcpw.getText().toString().length() == 0)) {
                etpw.setError("Password Tidak Boleh Kosong");
            }

            else if (etpw.getText().toString().length() != 0 && confirmpass.equals(passvalue)) {
                Toast.makeText(RegistrasiActivity.this, "Akun Terdaftar", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrasiActivity.this, MainActivity.class);
                startActivity(intent);
            }

            else {
                etcpw.setError("Konfirmasi Password Anda Salah");
            }

        });
    }
}