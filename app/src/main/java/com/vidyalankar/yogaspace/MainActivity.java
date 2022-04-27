package com.vidyalankar.yogaspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        if(mAuth != null){
            firebaseUser= mAuth.getCurrentUser();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firebaseUser == null){
                    Intent intent1= new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent1);
                }else {
                    Intent intent2 = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(intent2);
                }
                finish();
            }
        },5000);

    }
}