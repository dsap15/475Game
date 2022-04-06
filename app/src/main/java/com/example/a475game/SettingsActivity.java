package com.example.a475game;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ToggleButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ImageButton back = (ImageButton) findViewById(R.id.imageButton5);
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



    }


}