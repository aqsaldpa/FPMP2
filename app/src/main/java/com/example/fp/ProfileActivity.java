package com.example.fp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    Button logout;
    TextView tvUsername, tvEMail;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.btnLogout);
        tvUsername = findViewById(R.id.tvUsername);
        tvEMail = findViewById(R.id.tvEmail);

        logout.setOnClickListener(v -> {

            Toast.makeText(this, "LOG OUT Berhasil!!!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(i);
        });

    }
}