package com.vidyalankar.yogaspace.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vidyalankar.yogaspace.R;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= findViewById(R.id.logo);

        Glide.with(this)
                .load(R.drawable.yoga_space)
                .into(imageView);


        mAuth = FirebaseAuth.getInstance();
        if (mAuth != null) {
            firebaseUser = mAuth.getCurrentUser();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseUser == null) {
                    Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent1);
                } else {
                    Intent intent2 = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent2);
                }
                finish();
            }
        }, 5000);

    }
}