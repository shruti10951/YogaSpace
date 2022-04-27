package com.vidyalankar.yogaspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView sign_up_txt;
    EditText email_et, password_et;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sign_up_txt= findViewById(R.id.sign_up_txt);
        email_et= findViewById(R.id.login_email_et);
        password_et= findViewById(R.id.login_password_et);
        login_btn= findViewById(R.id.login_btn);

        sign_up_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= email_et.getText().toString();
                String password= password_et.getText().toString();

                if(email.isEmpty()){
                    email_et.setError("Email is required!");
                    email_et.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    password_et.setError("Password is required!");
                    password_et.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    email_et.setError("Please provide valid Email!");
                    email_et.requestFocus();
                    return;
                }
                if(password.length()<6){
                    password_et.setError("Min password length should be 6 characters!");
                    password_et.requestFocus();
                    return;
                }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(LoginActivity.this, SignUpActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Failed to Login! Please check your credentials!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}