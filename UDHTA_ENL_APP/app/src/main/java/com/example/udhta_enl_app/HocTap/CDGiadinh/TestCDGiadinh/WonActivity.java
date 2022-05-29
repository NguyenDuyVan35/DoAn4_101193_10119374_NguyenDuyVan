package com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.example.udhta_enl_app.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resultText;
    MediaPlayer mediaPlayer;
    int correct,wrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        amthanh(R.raw.amthanh_win);
        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong",0);

        circularProgressBar=findViewById(R.id.circularProgressBar);
        resultText=findViewById(R.id.resultText);

        circularProgressBar.setProgress(correct);
        resultText.setText(correct+"/20");
    }
    public void amthanh(int nhac){
        mediaPlayer=MediaPlayer.create(this,nhac);
        mediaPlayer.start();
    }
}