package com.example.fp.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fp.Activity.LoginActivity;
import com.example.fp.Activity.ProfileActivity;
import com.example.fp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            startActivity(new Intent(AuthSession.this, ProfileActivity.class));
        }else{
            startActivity(new Intent(AuthSession.this, LoginActivity.class));
        }
    }
}