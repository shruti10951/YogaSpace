package com.vidyalankar.yogaspace.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.yogaspace.R;
import com.vidyalankar.yogaspace.model.UserModel;

import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    DiagonalLayout yoga, breathe, wellness, binaural;
    ImageView account;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        yoga = findViewById(R.id.yoga_card);
        breathe = findViewById(R.id.breathe_card);
        wellness = findViewById(R.id.wellness_card);
        binaural = findViewById(R.id.binaural_card);
        account = findViewById(R.id.profile_pic);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        firebaseDatabase.getReference().child("Yoga Space").child("Users")
                .child(mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        String initial = userModel.getInitial().toUpperCase(Locale.ROOT);

                        TextDrawable textDrawable = TextDrawable.builder()
                                .beginConfig()
                                .width(60)
                                .height(60)
                                .useFont(Typeface.SERIF)
                                .endConfig()
                                .buildRound(initial, getResources().getColor(R.color.grey));

                        account.setImageDrawable(textDrawable);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, YogaTypesActivity.class);
                startActivity(intent);
            }
        });

        breathe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, BreathNowActivity.class);
                startActivity(intent);
            }
        });

        wellness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, WellnessActivity.class);
                startActivity(intent);
            }
        });

        binaural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, BinauralActivity.class);
                startActivity(intent);
            }
        });

    }
}