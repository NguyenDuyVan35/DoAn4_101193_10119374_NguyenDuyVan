package com.example.udhta_enl_app.HocTap.CDDodunghoctap.TestCDddht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TvCDDodunghoctapActivity;
import com.example.udhta_enl_app.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonDDHTActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBarddht;
    TextView resultTextddht,tvluuTestddht;
    MediaPlayer mediaPlayer;
    int correctCount1,wrongCount1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_ddhtactivity);

        amthanhmh(R.raw.amthanh_win);
        correctCount1=getIntent().getIntExtra("correctddht",0);
        wrongCount1=getIntent().getIntExtra("wrongddht",0);

        circularProgressBarddht=findViewById(R.id.circularProgressBar);
        resultTextddht=findViewById(R.id.resultText);
        tvluuTestddht=findViewById(R.id.tvluuTestddht);

        circularProgressBarddht.setProgress(correctCount1);
        resultTextddht.setText(wrongCount1+"/10");

        tvluuTestddht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WonDDHTActivity.this, TvCDDodunghoctapActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void amthanhmh(int n){
        mediaPlayer=MediaPlayer.create(this,n);
        mediaPlayer.start();
    }
}