package com.vidyalankar.yogaspace.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.vidyalankar.yogaspace.R;

public class BreathNowActivity extends AppCompatActivity {

    TextView relax, relax1, relax2;
    ImageView imageView;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_now);

        relax = findViewById(R.id.relax);
        relax1 = findViewById(R.id.relax1);
        relax2 = findViewById(R.id.relax2);
        imageView = findViewById(R.id.imageView);
        toggleButton = findViewById(R.id.start);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton.isChecked()) {
                    relax.setVisibility(View.GONE);
                    relax1.setVisibility(View.GONE);
                    relax2.setVisibility(View.GONE);

                    Glide.with(BreathNowActivity.this)
                            .asGif()
                            .load("https://firebasestorage.googleapis.com/v0/b/yoga-space-15f1d.appspot.com/o/gif%2Fbreathing.gif?alt=media&token=27f79bf3-e807-4dbc-8977-41b0f083e725")
                            .centerCrop()
                            .into(imageView);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    relax.setVisibility(View.VISIBLE);
                    relax1.setVisibility(View.VISIBLE);
                    relax2.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }
}