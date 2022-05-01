package com.vidyalankar.yogaspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IsochronicActivity extends AppCompatActivity {

    String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isochronic);

        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.melody1:
                        songName= "Calm Mind";
                        forward();
                        break;
                    case R.id.melody2:
                        songName= "Morning Motivator";
                        forward();
                        break;
                    case R.id.melody3:
                        songName= "Alpha Waves";
                        forward();
                        break;
                    case R.id.melody4:
                        songName= "Beta Swirl";
                        forward();
                        break;
                }
            }
        };
        findViewById(R.id.melody1).setOnClickListener(listener);
        findViewById(R.id.melody2).setOnClickListener(listener);
        findViewById(R.id.melody3).setOnClickListener(listener);
        findViewById(R.id.melody4).setOnClickListener(listener);

    }


    private void forward() {

        Intent intent= new Intent(IsochronicActivity.this, PlayMelodies1Activity.class);
        intent.putExtra("songName", songName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
}