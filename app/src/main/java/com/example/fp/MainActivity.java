package com.example.fp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnRegister;
    Button login;
    EditText username, pass;
    CheckBox checkBox;
    boolean remember = false;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etUsername);
        pass = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        checkBox = findViewById(R.id.checkBox);
        preferences = getSharedPreferences("Userinfo", 0); //0 (mode privat)

        remember = preferences.getBoolean("checkbox", false);
        if (remember) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }


        login.setOnClickListener(v -> {
            String uservalue = username.getText().toString();
            String pwvalue = pass.getText().toString();
            String registereduser = preferences.getString("Username", "");
            String registeredpass = preferences.getString("Password", "");

            boolean checked = checkBox.isChecked();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("checkbox", checked);
            editor.commit();
            editor.apply();

            if (uservalue.length()>1 && pwvalue.length()>1) {
                if (uservalue.equals(registereduser) && pwvalue.equals(registeredpass)) {
                    Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                }
            }else {
                Toast.makeText(MainActivity.this, "Username dan Password Salah!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v ->{
            Intent i = new Intent (MainActivity.this,RegistrasiActivity.class);
            startActivity(i);
        });
    }
}