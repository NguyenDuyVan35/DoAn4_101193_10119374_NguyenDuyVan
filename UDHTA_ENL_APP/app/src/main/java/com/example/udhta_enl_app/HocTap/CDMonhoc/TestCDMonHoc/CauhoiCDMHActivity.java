package com.example.udhta_enl_app.HocTap.CDMonhoc.TestCDMonHoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh.CauhoiCDGDActivity;
import com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh.CauhoiCDGiadinh;
import com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh.WonActivity;
import com.example.udhta_enl_app.HoctapActivity;
import com.example.udhta_enl_app.R;
import com.google.firebase.database.DatabaseReference;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CauhoiCDMHActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextToSpeech textToSpeech;
    MediaPlayer mediaPlayer;
    private static final long START_TIME_IN_MILLIS=300000;
    int timeQuestion=30;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis=START_TIME_IN_MILLIS;
    RoundedHorizontalProgressBar progressBar;

    ArrayList<CauhoiCDMH> cauhoiCDMHs;
    DatabaseReference databaseReference;

    List<CauhoiCDMH> cauhoiCDMHList;
    CauhoiCDMH cauhoiCDMH;
    TextView tvquestionmh,tvNDquestionmh,tvdichcauhoimh,tvghichuCDMH,tvdapan1CDMH,tvdapan2CDMH,tvdapan3CDMH,tvdapan4CDMH,textViewTimeMH;
    ImageView ivChudeMH;
    CardView cadviewDA1CDMH,cadviewDA2CDMH,cadviewDA3CDMH,cadviewDA4CDMH;
    int indexmh=0;
    int correctCountmh=0;
    int wrongCountmh=0;
    int questionmh=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoi_cdmhactivity);

        Anhxa();

        cauhoiCDMHs=new ArrayList<>();

        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));
        cauhoiCDMHs.add(new CauhoiCDMH("","","","","","","","",""));

        countDownTimer=new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis=millisUntilFinished;
                uppdateCountDownText();

            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(CauhoiCDMHActivity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.buttonTryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(CauhoiCDMHActivity.this, HoctapActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        }.start();

        cauhoiCDMHList=cauhoiCDMHs;
        Collections.shuffle(cauhoiCDMHList);
        cauhoiCDMH= cauhoiCDMHs.get(indexmh);
        setAlldata();
    }

    private void uppdateCountDownText() {
        int minutes=(int) (mTimeLeftInMillis/1000)/60;
        int seconds=(int) (mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        textViewTimeMH.setText(timeLeftFormatted);
    }

    private void setAlldata() {
        tvNDquestionmh.setText(cauhoiCDMH.getCauhoiCDMH());
        tvdichcauhoimh.setText(cauhoiCDMH.getDichCHCDMH());
        tvghichuCDMH.setText(cauhoiCDMH.getGhiChuCDMH());
        tvdapan1CDMH.setText(cauhoiCDMH.getDapan1CDMH());
        tvdapan2CDMH.setText(cauhoiCDMH.getDapan2CDMH());
        tvdapan3CDMH.setText(cauhoiCDMH.getDapan3CDMH());
        tvdapan4CDMH.setText(cauhoiCDMH.getDapan4CDMH());
        Glide.with(this).load(cauhoiCDMH.getLinkAnhCDMH()).into(ivChudeMH);
    }

    private void Anhxa() {
        tvquestionmh=findViewById(R.id.tvquestionmh);
        tvNDquestionmh=findViewById(R.id.tvNDquestionmh);
        tvdichcauhoimh=findViewById(R.id.tvdichcauhoimh);
        tvghichuCDMH=findViewById(R.id.tvghichuCDMH);
        tvdapan1CDMH=findViewById(R.id.tvdapan1CDMH);
        tvdapan2CDMH=findViewById(R.id.tvdapan2CDMH);
        tvdapan3CDMH=findViewById(R.id.tvdapan3CDMH);
        tvdapan4CDMH=findViewById(R.id.tvdapan4CDMH);
        textViewTimeMH=findViewById(R.id.textViewTimeMH);

        ivChudeMH=findViewById(R.id.ivChudeMH);

        cadviewDA1CDMH=findViewById(R.id.cadviewDA1CDMH);
        cadviewDA2CDMH=findViewById(R.id.cadviewDA2CDMH);
        cadviewDA3CDMH=findViewById(R.id.cadviewDA3CDMH);
        cadviewDA4CDMH=findViewById(R.id.cadviewDA4CDMH);
    }
    public void Correct(CardView cardView){
//        StartTimeQuestion();
        amthanh(R.raw.amthanh_dung);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                correctCountmh++;
                indexmh++;
                questionmh=questionmh+1;
                tvquestionmh.setText(questionmh+"");
                cauhoiCDMH= cauhoiCDMHList.get(indexmh);
                setAlldata();
                resetColor();

            }
        },1500);

    }
    public void Wrong(CardView cadviewDA1){
//        StartTimeQuestion();
        amthanh(R.raw.amthanh_sai);
        cadviewDA1.setCardBackgroundColor(getResources().getColor(R.color.red));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wrongCountmh++;
                if (indexmh<cauhoiCDMHList.size()-1){
                    indexmh++;
                    questionmh=questionmh+1;
                    tvquestionmh.setText(questionmh+"");
                    cauhoiCDMH= cauhoiCDMHList.get(indexmh);
                    setAlldata();
                    resetColor();

                }
                else {
                    GameWonMH();
                }
            }
        }, 1500);
    }
    private void GameWonMH() {

        Intent intent=new Intent(CauhoiCDMHActivity.this, WonActivity.class);
        intent.putExtra("correct",correctCountmh);
        intent.putExtra("wrong",wrongCountmh);
        startActivity(intent);
    }
    public void amthanh(int at){
        mediaPlayer=MediaPlayer.create(this,at);
        mediaPlayer.start();

    }
    public void  resetColor(){
        cadviewDA1CDMH.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA2CDMH.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA3CDMH.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA4CDMH.setCardBackgroundColor(getResources().getColor(R.color.white));

    }

    public void ClickDA1CDMH(View view) {
        if (cauhoiCDMH.getDapan1CDMH().equals(cauhoiCDMH.getDapanCDMH())){
            cadviewDA1CDMH.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (indexmh<cauhoiCDMHList.size()-1){
                Correct(cadviewDA1CDMH);
            }
            else {
                GameWonMH();
            }
        }
        else {
            Wrong(cadviewDA1CDMH);
        }
    }

    public void ClickDA2CDMH(View view) {
        if (cauhoiCDMH.getDapan2CDMH().equals(cauhoiCDMH.getDapanCDMH())){
            cadviewDA2CDMH.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (indexmh<cauhoiCDMHList.size()-1){
                Correct(cadviewDA2CDMH);
            }
            else {
                GameWonMH();
            }
        }
        else {
            Wrong(cadviewDA2CDMH);
        }
    }

    public void ClickDA3CDMH(View view) {
        if (cauhoiCDMH.getDapan3CDMH().equals(cauhoiCDMH.getDapanCDMH())){
            cadviewDA3CDMH.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (indexmh<cauhoiCDMHList.size()-1){
                Correct(cadviewDA3CDMH);
            }
            else {
                GameWonMH();
            }
        }
        else {
            Wrong(cadviewDA3CDMH);
        }
    }

    public void ClickDA4CDMH(View view) {
        if (cauhoiCDMH.getDapan4CDMH().equals(cauhoiCDMH.getDapanCDMH())){
            cadviewDA4CDMH.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (indexmh<cauhoiCDMHList.size()-1){
                Correct(cadviewDA4CDMH);
            }
            else {
                GameWonMH();
            }
        }
        else {
            Wrong(cadviewDA4CDMH);
        }
    }
}