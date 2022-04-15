package com.example.a475game;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import java.util.Locale;


public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String SoundVal = "SOUNDS: ON";
    Spinner languageSpinner;
    public static String choice;
    SharedPreferences sp;
    public static boolean clicked, sailesh;
    public static boolean resultsound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        languageSpinner = findViewById(R.id.spinner5);
        Spinner coloredSpinner =  findViewById((R.id.spinner5));
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.languages,
                R.layout.spinner
        );
        adapter.setDropDownViewResource(R.layout.spinner);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);


        ImageButton back = (ImageButton) findViewById(R.id.backButtonSettingsP);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });

        ToggleButton darkModeBtn = findViewById(R.id.toggleButton3);
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        sp = getSharedPreferences("choicePref", Context.MODE_PRIVATE);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("choicePref", Context.MODE_PRIVATE);
        String lastChoice = sp.getString("choice","");
        System.out.println("last choice: "+ lastChoice);


        darkModeBtn.setChecked(sharedPreferences.getBoolean("value", true));

        sailesh = darkModeBtn.isChecked();
        if (sailesh) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        darkModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clicked = darkModeBtn.isChecked();
                if (clicked) {
                    languageSpinner.setSelection(getIndex(languageSpinner,lastChoice));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    darkModeBtn.setChecked(true);
                } else {
                    languageSpinner.setSelection(getIndex(languageSpinner,lastChoice));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    darkModeBtn.setChecked(false);
                }
            }
        });


        ToggleButton soundButton = findViewById(R.id.toggleButton);
        soundButton.setText(SoundVal);
        SharedPreferences sharedPreferences2 = getSharedPreferences("save2", MODE_PRIVATE);
        soundButton.setChecked(sharedPreferences2.getBoolean("value2", true));
        if (soundButton.isChecked()) {
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            resultsound = true;
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        } else {
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            resultsound = false;
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
        }

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean soundClicked = soundButton.isChecked();
                if (soundClicked) {

                    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
                    resultsound = true;
                    SharedPreferences.Editor editor = getSharedPreferences("save2", MODE_PRIVATE).edit();
                    editor.putBoolean("value2", true);
                    editor.apply();
                    soundButton.setChecked(true);
                } else {
                    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                    resultsound = false;
                    SharedPreferences.Editor editor = getSharedPreferences("save2", MODE_PRIVATE).edit();
                    editor.putBoolean("value2", false);
                    editor.apply();
                    soundButton.setChecked(false);
                }
            }
        });

        setLangage();


    }

    public void localsetter(String language) {
        Locale newLocal = new Locale(language);
        Resources res = getResources();
        DisplayMetrics mat = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        config.locale = newLocal;
        res.updateConfiguration(config, mat);
        Intent reset = new Intent(this, SettingsActivity.class);
        finish();
        startActivity(reset);
    }



    public void setLangage() {
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = parent.getItemAtPosition(position).toString();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("choice", choice);
                editor.commit();
                setLanguageHelper(choice);

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setLanguageHelper(String lastSelectedLang){
        if (lastSelectedLang.equals("English")) {
            localsetter("en");
        }
        if (lastSelectedLang.equals("Français")) {
            localsetter("fr");
        }
    }
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

