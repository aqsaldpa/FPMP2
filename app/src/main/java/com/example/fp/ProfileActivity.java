package com.example.fp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    Button logout;
    TextView tvUsername, tvEMail;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = findViewById(R.id.btnLogout);
        tvUsername = findViewById(R.id.tvUsername);
        tvEMail = findViewById(R.id.tvEmail);
        preferences = getSharedPreferences("UserInfo", 0);

        tvUsername.setText(preferences.getString("Username", ""));
        tvEMail.setText(preferences.getString("Email", ""));

        logout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(this, "LOG OUT Berhasil!!!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(i);
        });

    }
}