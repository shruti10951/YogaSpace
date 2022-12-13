package com.vidyalankar.yogaspace.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.vidyalankar.yogaspace.R;

public class BreathNowActivity extends AppCompatActivity {

    TextView relax, relax1, relax2;
    ImageView imageView;
    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_now);

        relax = findViewById(R.id.relax);
        relax1 = findViewById(R.id.relax1);
        relax2 = findViewById(R.id.relax2);
        imageView = findViewById(R.id.imageView);
        btn = findViewById(R.id.start);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn.getText().equals("Start")) {
                    relax.setVisibility(View.GONE);
                    relax1.setVisibility(View.GONE);
                    relax2.setVisibility(View.GONE);

                    Glide.with(BreathNowActivity.this)
                            .asGif()
                            .load(R.drawable.breathing_gif)
                            .centerCrop()
                            .into(imageView);
                    imageView.setVisibility(View.VISIBLE);

                    btn.setText("Stop");

                } else {
                    relax.setVisibility(View.VISIBLE);
                    relax1.setVisibility(View.VISIBLE);
                    relax2.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    btn.setText("Start");
                }
            }
        });
    }
}