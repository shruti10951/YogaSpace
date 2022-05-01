package com.vidyalankar.yogaspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BinauralActivity extends AppCompatActivity {
    String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binaural);



        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.melodyB1:
                        songName= "Healing Aura";
                        forward();
                        break;
                    case R.id.melodyB2:
                        songName= "Ground Air";
                        forward();
                        break;
                    case R.id.melodyB3:
                        songName= "Deep Alpha";
                        forward();
                        break;
                    case R.id.melodyB4:
                        songName= "Relaxing Waves";
                        forward();
                        break;
                }
            }
        };
        findViewById(R.id.melodyB1).setOnClickListener(listener);
        findViewById(R.id.melodyB2).setOnClickListener(listener);
        findViewById(R.id.melodyB3).setOnClickListener(listener);
        findViewById(R.id.melodyB4).setOnClickListener(listener);


    }


    private void forward() {

        Intent intent= new Intent(BinauralActivity.this, PlayMelodies2Activity.class);
        intent.putExtra("songName", songName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}