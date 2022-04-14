package com.example.a475game;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.concurrent.ThreadLocalRandom;
import android.widget.Toast;
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

        ImageButton random_button = (ImageButton) findViewById(R.id.optionrandom);
        random_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                int min = 3;
                int max = 11;
                int random = ThreadLocalRandom.current().nextInt(min, max);
                gameActivity.grid = random;
            }
        });


        ImageButton custom_button = (ImageButton) findViewById(R.id.optioncustom);
        custom_button.setOnClickListener(new View.OnClickListener() {
            final EditText user_input = findViewById(R.id.test_user_input);

            @Override
            public void onClick(View view) {
                    //Refine code
                int new_input = Integer.parseInt(user_input.getText().toString());
                gameActivity.grid = new_input;

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


