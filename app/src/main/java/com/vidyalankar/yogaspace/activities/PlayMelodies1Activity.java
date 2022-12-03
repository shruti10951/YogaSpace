package com.vidyalankar.yogaspace.activities;

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
import com.vidyalankar.yogaspace.R;

import java.util.concurrent.TimeUnit;

public class PlayMelodies1Activity extends AppCompatActivity {


    ImageView play1, pause1;
    TextView playerPosition1, playerDuration1;
    SeekBar seekBar1;
    Intent intent;
    String song;
    MediaPlayer mediaPlayer;
    ImageView music_image;

    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_melodies1);

        play1 = findViewById(R.id.play1);
        pause1 = findViewById(R.id.pause1);
        music_image = findViewById(R.id.image_music);
        seekBar1 = findViewById(R.id.seekbar1);
        playerDuration1 = findViewById(R.id.player_duration1);
        playerPosition1 = findViewById(R.id.player_position1);
        intent = getIntent();
        song = intent.getStringExtra("songName");

        Glide.with(PlayMelodies1Activity.this)
                .load("https://firebasestorage.googleapis.com/v0/b/yoga-space-15f1d.appspot.com/o/gif%2Fmusic1.gif?alt=media&token=3d3e3103-2db1-4466-8963-9316a9d7c02f")
                .centerCrop()
                .into(music_image);

        switch (song) {
            case "Calm Mind":
                mediaPlayer = MediaPlayer.create(this, R.raw.calm_mind);
                break;
            case "Morning Motivator":
                mediaPlayer = MediaPlayer.create(this, R.raw.morning_motivator);
                break;
            case "Alpha Waves":
                mediaPlayer = MediaPlayer.create(this, R.raw.alpha_waves);
                break;
            case "Beta Swirl":
                mediaPlayer = MediaPlayer.create(this, R.raw.beta_swirl);
                break;
            case "Healing Aura":
                mediaPlayer = MediaPlayer.create(this, R.raw.healing_aura);
                break;
            case "Ground Air":
                mediaPlayer = MediaPlayer.create(this, R.raw.ground_air);
                break;
            case "Deep Alpha":
                mediaPlayer = MediaPlayer.create(this, R.raw.deep_alpha);
                break;
            case "Relaxing Waves":
                mediaPlayer = MediaPlayer.create(this, R.raw.relaxing_waves);
                break;
        }

        runnable = new Runnable() {
            @Override
            public void run() {

                seekBar1.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 400);

            }
        };

        int duration = mediaPlayer.getDuration();

        String sDuration = convertFormat(duration);
        playerDuration1.setText(sDuration);


        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play1.setVisibility(View.GONE);
                pause1.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                seekBar1.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable, 0);

            }
        });

        pause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause1.setVisibility(View.GONE);
                play1.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);

            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
                playerPosition1.setText(convertFormat(mediaPlayer.getCurrentPosition()));
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
                pause1.setVisibility(View.GONE);
                play1.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);
            }
        });


    }

    @Override
    public void onBackPressed() {
        pause1.setVisibility(View.GONE);
        play1.setVisibility(View.VISIBLE);
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