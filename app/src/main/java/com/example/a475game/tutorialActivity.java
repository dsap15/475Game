package com.example.a475game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class tutorialActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //if we want to use internal video
//        VideoView videoView = findViewById(R.id.internal_tutorial_video);
//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.coming_soon;
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//
//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);


        ImageButton tutorialBtn = (ImageButton) findViewById(R.id.tutorialBack);
        tutorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tutorialActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();

        } else{
            super.onBackPressed();
        }

    }


}