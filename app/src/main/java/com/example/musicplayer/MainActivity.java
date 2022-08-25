package com.example.musicplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonMinus;
    private ImageButton buttonPlus;
    private ImageButton buttonPlay;
    private SeekBar seekBar;
    private TextView textViewNameSong;
    private MediaPlayer player;
    private AudioManager audioManager;
    private Uri music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMinus = findViewById(R.id.imageButtonMinus);
        buttonPlus = findViewById(R.id.imageButtonPlus);
        buttonPlay = findViewById(R.id.imageButtonPlay);
        textViewNameSong = findViewById(R.id.textViewNameSong);
        seekBar = findViewById(R.id.seekBarVolume);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        player = MediaPlayer.create(this, R.raw.stuff);
        player.start();
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    buttonPlay.setImageResource(android.R.drawable.ic_media_play);
                    player.pause();
                } else {
                    buttonPlay.setImageResource(android.R.drawable.ic_media_pause);
                    player.start();
                }
            }
        });
        seekBar.setMax(player.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(player.getCurrentPosition());
            }
        }, 0, 1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    player.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onClickMinus(View view) {
        player.seekTo(player.getCurrentPosition() - 3000);
    }

    public void onClickPlus(View view) {
        player.seekTo(player.getCurrentPosition() + 3000);
    }
}