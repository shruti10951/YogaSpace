package com.vidyalankar.yogaspace.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    View profileView;
    ImageView profile_img;
    TextView emailTxt, nameTxt, logOutTxt;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;

    String initial, name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        yoga = findViewById(R.id.yoga_card);
        breathe = findViewById(R.id.breathe_card);
        wellness = findViewById(R.id.wellness_card);
        binaural = findViewById(R.id.binaural_card);
        account = findViewById(R.id.profile_pic);

        profileView = findViewById(R.id.profile_view);
        profile_img= findViewById(R.id.profile_view_pic);
        emailTxt= findViewById(R.id.profile_view_email);
        nameTxt= findViewById(R.id.profile_view_name);
        logOutTxt= findViewById(R.id.log_out_txt);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        firebaseDatabase.getReference().child("Yoga Space").child("Users")
                .child(mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        initial = userModel.getInitial().toUpperCase(Locale.ROOT);
                        name= userModel.getUsername();
                        email= userModel.getEmail();

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

                profileView.setVisibility(View.VISIBLE);
                profile_img.setVisibility(View.VISIBLE);
                emailTxt.setVisibility(View.VISIBLE);
                nameTxt.setVisibility(View.VISIBLE);
                logOutTxt.setVisibility(View.VISIBLE);

                TextDrawable textDrawable= TextDrawable.builder()
                        .beginConfig()
                        .width(60)
                        .height(60)
                        .useFont(Typeface.SERIF)
                        .endConfig()
                        .buildRound(initial, getResources().getColor(R.color.grey));

                profile_img.setImageDrawable(textDrawable);
                nameTxt.setText(name);
                emailTxt.setText(email);

                logOutTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        logout();
                    }
                });

                profileView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        profileView.setVisibility(View.GONE);
                        profile_img.setVisibility(View.GONE);
                        emailTxt.setVisibility(View.GONE);
                        nameTxt.setVisibility(View.GONE);
                        logOutTxt.setVisibility(View.GONE);

                    }
                });

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

    private void logout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
        builder.setTitle("Log Out?");
        builder.setMessage("Are you sure you want to log out?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}