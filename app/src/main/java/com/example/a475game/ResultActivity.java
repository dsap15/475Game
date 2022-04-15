package com.example.a475game;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int Result_Sound;
    private SoundPool sound_effects;
    boolean sound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sound = SettingsActivity.resultsound;
        setContentView(R.layout.result_activity);
        TextView tv = findViewById(R.id.winner);
        String result = getIntent().getExtras().getString("Result");
        tv.setText(result);

        if(!sound) {
            MediaPlayer resultSound = MediaPlayer.create(this, R.raw.resultsound);
            resultSound.start();
        }

        ImageButton back = (ImageButton) findViewById(R.id.restart_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });

    }
}

