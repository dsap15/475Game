package com.example.a475game;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ToggleButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class SettingsActivity extends AppCompatActivity {

    String SoundVal = "SOUNDS: ON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ImageButton back = (ImageButton) findViewById(R.id.testbackbuttonsettings);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });

        ToggleButton darkModeBtn = findViewById(R.id.toggleButton3);
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        darkModeBtn.setChecked(sharedPreferences.getBoolean("value", true));
        if (darkModeBtn.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        darkModeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        boolean clicked = darkModeBtn.isChecked();
                        if(clicked) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                            editor.putBoolean("value", true);
                            editor.apply();
                            darkModeBtn.setChecked(true);
                        }
                        else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                            editor.putBoolean("value",false);
                            editor.apply();
                            darkModeBtn.setChecked(false);
                        }
                    }
                });

        // sound DO NOT FUCKING REMOVE IT            :- TP

        ToggleButton soundButton = findViewById(R.id.toggleButton);
        soundButton.setText(SoundVal);
        SharedPreferences sharedPreferences2 = getSharedPreferences("save2", MODE_PRIVATE);
        soundButton.setChecked(sharedPreferences2.getBoolean("value2", true));
        if (soundButton.isChecked()) {
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        }
        else {
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
        }

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                boolean clicked = soundButton.isChecked();
                if(clicked) {

                    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);

                    SharedPreferences.Editor editor = getSharedPreferences("save2", MODE_PRIVATE).edit();
                    editor.putBoolean("value2", true);
                    editor.apply();
                    soundButton.setChecked(true);
                }
                else {
                    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                    SharedPreferences.Editor editor = getSharedPreferences("save2", MODE_PRIVATE).edit();
                    editor.putBoolean("value2",false);
                    editor.apply();
                    soundButton.setChecked(false);
                }
            }
        });


    }


}