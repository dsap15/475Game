package com.example.a475game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
//Just adding a stupid comment because my commit wasn't shown in the actual contributor section
//Gary Preston main contributor

public class LevelEditor extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_editor);

        ImageButton backbutton  = (ImageButton) findViewById(R.id.SECONDPAGEBACKBUTTON);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LevelEditor.this, MainActivity.class));
            }
        });

        ImageButton threeGridButton = (ImageButton) findViewById(R.id.option3x3);
        threeGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameActivity.grid = 3;
            }
        });

        ImageButton fiveGridButton = (ImageButton) findViewById(R.id.option5x5);
        fiveGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameActivity.grid = 5;
            }
        });

        ImageButton sevenGridButton = (ImageButton) findViewById(R.id.option7x7);
        sevenGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameActivity.grid = 7;
            }
        });

        ImageButton nineGridButton = (ImageButton) findViewById(R.id.option9x9);
        nineGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameActivity.grid = 9;
            }
        });

        ImageButton begin = findViewById(R.id.begin_button);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent beginGame = new Intent(view.getContext(), gameActivity.class);
                startActivity(beginGame);
            }
        });

        }
    }


