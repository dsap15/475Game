package com.example.a475game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ImageButton startButton = (ImageButton) findViewById(R.id.start);
        ImageButton quickPlay = (ImageButton) findViewById(R.id.quickplay);
        ImageButton tutorial = (ImageButton) findViewById(R.id.tutorial);
        ImageButton setting = (ImageButton) findViewById(R.id.testgearicon);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LevelEditor.class));
            }
        });

        quickPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameActivity.grid = 3;
                startActivity(new Intent(MainActivity.this,gameActivity.class));
            }
        });

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, tutorialActivity.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v  ) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

    }

}