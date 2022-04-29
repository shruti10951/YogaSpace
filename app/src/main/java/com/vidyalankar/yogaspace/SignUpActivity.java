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

public class SignUpActivity extends AppCompatActivity {

    TextView login_txt;
    Button sign_up_btn,ok;
    EditText email_et, password_et, confirm_pass_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login_txt= findViewById(R.id.login_txt);
        ok= findViewById(R.id.ok);
        sign_up_btn= findViewById(R.id.sign_up_btn);
        email_et = findViewById(R.id.sign_up_email);
        password_et = findViewById(R.id.sign_up_password);
        confirm_pass_et = findViewById(R.id.sign_up_confirm_password);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,YogaTypesActivity.class);
                startActivity(intent);
            }
        });

        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= email_et.getText().toString();
                String password= password_et.getText().toString();
                String password_confirm= confirm_pass_et.getText().toString();

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
                if(!password.equals(password_confirm)){
                    password_et.setError("Password did not match!");
                    password_et.requestFocus();
                    return;
                }
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "User Registered Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignUpActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}