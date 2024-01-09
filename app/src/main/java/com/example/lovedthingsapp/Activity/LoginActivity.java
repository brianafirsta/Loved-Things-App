package com.example.lovedthingsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void signin(View view) {

        String emailuser = email.getText().toString();
        String passworduser = password.getText().toString();

        if (TextUtils.isEmpty(emailuser)) {
            Toast.makeText(this, "Masukkan Email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(passworduser)) {
            Toast.makeText(this, "Masukkan Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (passworduser.length() < 6){
            Toast.makeText(this, "Password terlalu singkat, masukkan minimal 6 karakter", Toast.LENGTH_SHORT).show();
        }

        auth.signInWithEmailAndPassword(emailuser,passworduser)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Login Sukses!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity2.class));

                        }else {
                            Toast.makeText(LoginActivity.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

}