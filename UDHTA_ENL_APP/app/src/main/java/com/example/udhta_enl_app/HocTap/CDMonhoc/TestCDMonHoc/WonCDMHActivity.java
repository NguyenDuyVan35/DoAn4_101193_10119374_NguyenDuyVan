package com.example.udhta_enl_app.HocTap.CDMonhoc.TestCDMonHoc;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.example.udhta_enl_app.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonCDMHActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBarMH;
    TextView resultTextMH;
    MediaPlayer mediaPlayer;
    int correctMH,wrongMH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_cdmhactivity);

        amthanhmh(R.raw.amthanh_win);
        correctMH=getIntent().getIntExtra("correct",0);
        wrongMH=getIntent().getIntExtra("wrong",0);

        circularProgressBarMH=findViewById(R.id.circularProgressBar);
        resultTextMH=findViewById(R.id.resultText);

        circularProgressBarMH.setProgress(correctMH);
        resultTextMH.setText(correctMH+"/20");
    }
    public void amthanhmh(int n){
        mediaPlayer=MediaPlayer.create(this,n);
        mediaPlayer.start();
    }
}