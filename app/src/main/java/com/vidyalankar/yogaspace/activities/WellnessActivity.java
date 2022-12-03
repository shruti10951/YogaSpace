package com.vidyalankar.yogaspace.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.florent37.diagonallayout.DiagonalLayout;
import com.vidyalankar.yogaspace.R;

public class WellnessActivity extends AppCompatActivity {

    DiagonalLayout sleep, mindfulness, stress, nutrition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness);
        sleep = findViewById(R.id.sleep_diagonal);
        mindfulness = findViewById(R.id.mindfulness_diagonal);
        stress = findViewById(R.id.stress_diagonal);
        nutrition = findViewById(R.id.nutrition_diagonal);

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WellnessActivity.this, ArticlesActivity.class);
                intent.putExtra("Name", "Sleep");
                startActivity(intent);
            }
        });

        mindfulness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WellnessActivity.this, ArticlesActivity.class);
                intent.putExtra("Name", "Mindfulness");
                startActivity(intent);
            }
        });

        stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WellnessActivity.this, ArticlesActivity.class);
                intent.putExtra("Name", "Stress");
                startActivity(intent);
            }
        });

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WellnessActivity.this, ArticlesActivity.class);
                intent.putExtra("Name", "Nutrition");
                startActivity(intent);
            }
        });

    }

}