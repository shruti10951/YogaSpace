package com.vidyalankar.yogaspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    CardView yoga, breathe, binaural, isochronic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        yoga= findViewById(R.id.yoga_card);
        breathe= findViewById(R.id.breathe_card);
        binaural= findViewById(R.id.binaural_card);
        isochronic= findViewById(R.id.isochronic_card);

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DashboardActivity.this, YogaTypesActivity.class);
                startActivity(intent);
            }
        });

    }
}