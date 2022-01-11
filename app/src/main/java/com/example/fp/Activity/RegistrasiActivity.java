package com.example.fp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fp.R;
import com.example.fp.Auth.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrasiActivity extends AppCompatActivity {
    TextView log;
    Button Register;
    ProgressBar progressBar;
    EditText inputemail, pass1, pass2, inputusername;
    String email, password1, password2, username;
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
                startActivity(new Intent(RegistrasiActivity.this, LoginActivity.class));
            }
        });

        inputusername = findViewById(R.id.etUsername);
        inputemail = findViewById(R.id.etEmail);
        pass1 = findViewById(R.id.etPassword);
        pass2 = findViewById(R.id.etConfirm);
        progressBar = findViewById(R.id.pbRegister);

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
        username = inputusername.getText().toString();

        if (email.isEmpty()) {
            inputemail.setError("Mohon Diisi dengan benar!");
            inputemail.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            inputusername.setError("Mohon Diisi dengan benar!");
            inputusername.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputemail.setError("Mohon Isi dengan format email!");
            inputemail.requestFocus();
            return;
        }
        if (password1.isEmpty()) {
            pass1.setError("Mohoon Diisi");
            pass1.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(username);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistrasiActivity.this, "User berhasil terdaftar !", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else{
                                        Toast.makeText(RegistrasiActivity.this, "Gagal , Coba Lagi", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                            if (password1.equals(password2)) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(RegistrasiActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegistrasiActivity.this, "Registrasi Sukses", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistrasiActivity.this, "Registrasi Gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}