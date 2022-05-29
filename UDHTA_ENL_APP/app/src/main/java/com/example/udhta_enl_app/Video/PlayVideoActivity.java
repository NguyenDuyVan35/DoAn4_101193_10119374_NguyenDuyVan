package com.example.udhta_enl_app.Video;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.udhta_enl_app.R;


public class PlayVideoActivity extends AppCompatActivity {

    WebView webViewFullScreen;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        webViewFullScreen=findViewById(R.id.webViewFullScreen);

        String link=getIntent().getStringExtra("Link");
        webViewFullScreen.loadUrl(link);

        webViewFullScreen.setWebViewClient(new WebViewClient());
        webViewFullScreen.setWebChromeClient(new WebChromeClient());
        webViewFullScreen.getSettings().setJavaScriptEnabled(true);

    }
}