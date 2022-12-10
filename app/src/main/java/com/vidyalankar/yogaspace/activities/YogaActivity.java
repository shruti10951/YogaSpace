package com.vidyalankar.yogaspace.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.yogaspace.R;
import com.vidyalankar.yogaspace.model.YogaInstruction;

public class YogaActivity extends AppCompatActivity {

    TextView yoga_name, instruction1, instruction2, instruction3, instruction4, instruction5, instruction6, start;
    ImageView yogaImage;

    NestedScrollView nestedScrollView;

    VideoView videoView;

    String url= "https://firebasestorage.googleapis.com/v0/b/yoga-space-15f1d.appspot.com/o/Video%2Fone_minute_timer.mp4?alt=media&token=5d24e4bb-fc74-4b35-a3ef-0ec631595692";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);
        yoga_name = findViewById(R.id.yoga_name);


        Uri uri= Uri.parse(url);
        videoView= findViewById(R.id.video_view);
        videoView.setVideoURI(uri);
        videoView.setMediaController(null);

        instruction1 = findViewById(R.id.instruction1);
        instruction2 = findViewById(R.id.instruction2);
        instruction3 = findViewById(R.id.instruction3);
        instruction4 = findViewById(R.id.instruction4);
        instruction5 = findViewById(R.id.instruction5);
        instruction6 = findViewById(R.id.instruction6);
        yogaImage = findViewById(R.id.yoga_img);

        start= findViewById(R.id.start_txt);

        nestedScrollView= findViewById(R.id.nested_scrollview);


        Intent intent = getIntent();
        String y = intent.getStringExtra("name");

        FirebaseDatabase.getInstance().getReference()
                .child("Yoga Space")
                .child("Yoga")
                .child(y)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        YogaInstruction yogaInstruction = snapshot.getValue(YogaInstruction.class);

                        String yogaName = yogaInstruction.getName();
                        String instruct1 = yogaInstruction.getInstruction1();
                        String instruct2 = yogaInstruction.getInstruction2();
                        String instruct3 = yogaInstruction.getInstruction3();
                        String instruct4 = yogaInstruction.getInstruction4();
                        String instruct5 = yogaInstruction.getInstruction5();
                        String instruct6 = yogaInstruction.getInstruction6();
                        String yogaImg = yogaInstruction.getImage();

                        yoga_name.setText(yogaName);
                        instruction1.setText(instruct1);
                        instruction2.setText(instruct2);
                        instruction3.setText(instruct3);
                        instruction4.setText(instruct4);
                        instruction5.setText(instruct5);
                        instruction6.setText(instruct6);

                        Glide.with(YogaActivity.this)
                                .load(yogaImg)
                                .placeholder(R.color.shimmer)
                                .centerCrop()
                                .into(yogaImage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(videoView.isPlaying()){
                    videoView.pause();
                    start.setText("Start");
                }else {
                    videoView.start();
                    start.setText("Stop");
                }

            }
        });

    }
}