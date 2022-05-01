package com.vidyalankar.yogaspace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

public class PlayMelodies2Activity extends AppCompatActivity {

    ImageView play2, pause2;
    TextView playerPosition2, playerDuration2;
    SeekBar seekBar2;
    Intent intent;
    String song;
    MediaPlayer mediaPlayer;
    ImageView music_image;

    Handler handler = new Handler();
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_melodies2);

        play2 = findViewById(R.id.play2);
        pause2 = findViewById(R.id.pause2);
        music_image = findViewById(R.id.image_music);
        seekBar2=findViewById(R.id.seekbar2);
        playerDuration2=findViewById(R.id.player_duration2);
        playerPosition2=findViewById(R.id.player_position2);
        intent= getIntent();
        song= intent.getStringExtra("songName");

        Glide.with(PlayMelodies2Activity.this)
                .load("https://firebasestorage.googleapis.com/v0/b/yoga-space-15f1d.appspot.com/o/gif%2Fmusic1.gif?alt=media&token=3d3e3103-2db1-4466-8963-9316a9d7c02f")
                .centerCrop()
                .into(music_image);

        switch (song){
            case "Healing Aura":
                mediaPlayer= MediaPlayer.create(this, R.raw.healing_aura);
                break;
            case "Ground Air":
                mediaPlayer=MediaPlayer.create(this,R.raw.ground_air);
                break;
            case "Deep Alpha":
                mediaPlayer=MediaPlayer.create(this, R.raw.deep_alpha);
                break;
            case "Relaxing Waves":
                mediaPlayer=MediaPlayer.create(this, R.raw.relaxing_waves);
                break;
        }

        runnable = new Runnable() {
            @Override
            public void run() {

                seekBar2.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 400);

            }
        };

        int duration = mediaPlayer.getDuration();

        String sDuration = convertFormat(duration);
        playerDuration2.setText(sDuration);


        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play2.setVisibility(View.GONE);
                pause2.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                seekBar2.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable, 0);

            }
        });

        pause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause2.setVisibility(View.GONE);
                play2.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
                playerPosition2.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pause2.setVisibility(View.GONE);
                play2.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);
            }
        });


    }

    @Override
    public void onBackPressed() {
        pause2.setVisibility(View.GONE);
        play2.setVisibility(View.VISIBLE);
        mediaPlayer.pause();
        handler.removeCallbacks(runnable);
        super.onBackPressed();
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d"
                , TimeUnit.MILLISECONDS.toMinutes(duration)
                , TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));

    }
}