package com.vidyalankar.yogaspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class YogaTypesActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView yoga1, yoga2, yoga3, yoga4, yoga5, yoga6, yoga7, yoga8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_types);
        yoga1= findViewById(R.id.yoga1);
        yoga2= findViewById(R.id.yoga2);
        yoga3= findViewById(R.id.yoga3);
        yoga4= findViewById(R.id.yoga4);
        yoga5= findViewById(R.id.yoga5);
        yoga6= findViewById(R.id.yoga6);
        yoga7= findViewById(R.id.yoga7);
        yoga8= findViewById(R.id.yoga8);

        yoga1.setOnClickListener(this);
        yoga2.setOnClickListener(this);
        yoga3.setOnClickListener(this);
        yoga4.setOnClickListener(this);
        yoga5.setOnClickListener(this);
        yoga6.setOnClickListener(this);
        yoga7.setOnClickListener(this);
        yoga8.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String type= "";
        switch (view.getId()){
            case R.id.yoga1:
                type= "Balasana";
                changeScreen(type);
                break;
            case R.id.yoga2:
                type= "Trikonasana";
                changeScreen(type);
                break;
            case R.id.yoga3:
                type= "Uttanasana";
                changeScreen(type);
                break;
            case R.id.yoga4:
                type= "yoga4";
                changeScreen(type);
                break;
            case R.id.yoga5:
                type= "yoga5";
                changeScreen(type);
                break;
            case R.id.yoga6:
                type= "yoga6";
                changeScreen(type);
                break;
            case R.id.yoga7:
                type= "yoga7";
                changeScreen(type);
                break;
            case R.id.yoga8:
                type= "yoga8";
                changeScreen(type);
                break;
        }
    }

    private void changeScreen(String type) {
        Intent intent= new Intent(YogaTypesActivity.this, YogaActivity.class);
        intent.putExtra("yoga", type);
        startActivity(intent);
    }
}