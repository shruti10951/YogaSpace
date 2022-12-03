package com.vidyalankar.yogaspace.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.yogaspace.R;
import com.vidyalankar.yogaspace.model.UserModel;

import java.util.Locale;


public class ProfileActivity extends AppCompatActivity {

    ImageView profileIcon;
    TextView logoutTxt, nameTxt, emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileIcon = findViewById(R.id.profile_icon);
        logoutTxt = findViewById(R.id.log_out_txt);
        emailTxt = findViewById(R.id.email_txt);
        nameTxt = findViewById(R.id.username_txt);

        FirebaseDatabase.getInstance().getReference()
                .child("Yoga Space")
                .child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        String initial = userModel.getInitial().toUpperCase(Locale.ROOT);
                        TextDrawable textDrawable = TextDrawable.builder()
                                //to start the config
                                .beginConfig()
                                .width(120)
                                .height(120)
                                .useFont(Typeface.SERIF)
                                .endConfig()
                                .buildRound(initial, getResources().getColor(R.color.grey));

                        profileIcon.setImageDrawable(textDrawable);

                        String name = userModel.getUsername();
                        String email = userModel.getEmail();

                        nameTxt.setText(name);
                        emailTxt.setText(email);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        logoutTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Log Out?");
                builder.setMessage("Are you sure you want to log out?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
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
        });


    }
}