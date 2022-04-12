package com.example.a475game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< Updated upstream
        ImageButton startButton = (ImageButton) findViewById(R.id.sButton);
        ImageButton quickPlay = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton tutorial = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton setting = (ImageButton) findViewById(R.id.imageButton12);
=======


        ImageButton startButton = (ImageButton) findViewById(R.id.start);
        ImageButton quickPlay = (ImageButton) findViewById(R.id.quickplay);
        ImageButton tutorial = (ImageButton) findViewById(R.id.tutorial);
        ImageButton setting = (ImageButton) findViewById(R.id.testgearicon);
>>>>>>> Stashed changes

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LevelEditor.class));
            }
        });

        quickPlay.setOnClickListener(new View.OnClickListener() {

            // Modify this grid to include randomized grid layout. 3, 5, 7
            @Override
            public void onClick(View view) {
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