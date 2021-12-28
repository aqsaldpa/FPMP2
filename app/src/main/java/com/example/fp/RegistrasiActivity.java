package com.example.fp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrasiActivity extends AppCompatActivity {
    TextView log;
    Button Register;
    EditText inputemail, pass1, pass2;
    String email, password1, password2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        mAuth = FirebaseAuth.getInstance();
        log = (TextView) findViewById(R.id.akun);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrasiActivity.this, MainActivity.class));
            }
        });

        inputemail = findViewById(R.id.etEmail);
        pass1 = findViewById(R.id.etPassword);
        pass2 = findViewById(R.id.etConfirm);

        Register = findViewById(R.id.btnRegister);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrasi();
            }
        });
    }
    private void registrasi() {
        email = inputemail.getText().toString();
        password1 = pass1.getText().toString();
        password2 = pass2.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent( RegistrasiActivity.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegistrasiActivity.this, "Registrasi Sukses", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistrasiActivity.this, "Registrasi Gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}