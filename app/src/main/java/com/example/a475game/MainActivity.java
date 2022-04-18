package com.example.a475game;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.nio.file.WatchEvent;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable logo_animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.lg0);
        imageView.setBackgroundResource(R.drawable.animation);
        logo_animation = (AnimationDrawable) imageView.getBackground();

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
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, gameActivity.class));
                int min = 3;
                int max = 7;
                int random = ThreadLocalRandom.current().nextInt(min, max);
                if (random % 2 != 0) {
                    gameActivity.grid = random;
                }
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
    @Override
     public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
    logo_animation.start();
}
}