package com.example.udhta_enl_app.Kiemtra.BaiKT3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.Kiemtra.BaiKT1.KetthucBai1Activity;
import com.example.udhta_enl_app.Kiemtra.BaiKT2.Bai2Activity;
import com.example.udhta_enl_app.Kiemtra.BaiKT2.Cauhoibai2;
import com.example.udhta_enl_app.Kiemtra.BaiKT2.KetthucBai2Activity;
import com.example.udhta_enl_app.Kiemtra.KiemtraActivity;
import com.example.udhta_enl_app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Bai3Activity extends AppCompatActivity {

    ArrayList<Cauhoibai3> cauhoibai3s;
    Cauhoibai3 cauhoibai3;
    int index=0;
    int correctCountb3=0;
    int wrongCountb3=0;
    int diem=0;
    int questionbai3=1;
    List<Cauhoibai3> cauhoibai3List;

    private static  final long START_TIME_IN_MILLIS_BAI3=600000;
    private long b1TimeLeftInMillis=START_TIME_IN_MILLIS_BAI3;

    TextView tvtimeBai3,tvdiembai3,tvquestionbai3,tvCauhoibai3,tvdapan1bai3,tvdapan2bai3,tvdapan3bai3,tvdapan4bai3,Exitbai3;
    ImageView imvanhCHbai3;
    CardView cadviewDA1bai3,cadviewDA2bai3,cadviewDA3bai3,cadviewDA4bai3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        Anhxa();

        cauhoibai3s=new ArrayList<>();
        cauhoibai3s.add(new Cauhoibai3("Can you name the flowers pictured below?","sunflower","rose","orchid","chrysanthemum","rose","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TVCDLoaihoa%2Frose.jpeg?alt=media&token=890984fc-bab4-4210-a995-04af19299e00"));
        cauhoibai3s.add(new Cauhoibai3("Can you name the flowers pictured below?","Sakura flower","Orchid","sunflower","lotus flower","sunflower","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TVCDLoaihoa%2Fimages.jfif?alt=media&token=56505b09-b5e7-47cb-808a-7ca5fd524ed0"));
        cauhoibai3s.add(new Cauhoibai3("'tomato' có nghĩa là gì?","cà chua","cà rốt","dưa leo","cà tím","cà chua",""));
        cauhoibai3s.add(new Cauhoibai3("'Rau mùi' trong Tiếng Anh là gì","eggplant","cabbage","coriander","cauliflower","cauliflower",""));
        cauhoibai3s.add(new Cauhoibai3("'coriander' có phiên âm như thế nào?"," /kɒriˈændər/","/ˈkæbɪdʒ/","/ˈeɡplænt/","/ˈkɔːliflaʊər/","/kɒriˈændər/",""));
        cauhoibai3s.add(new Cauhoibai3("'cabbage' và 'carrot' có nghĩa là gì","Cà rốt, cà chua","Bắp cải, cà rốt"," Rau mùi, hành tây","Hành tím, bắp cải"," Bắp cải, cà rốt",""));
        cauhoibai3s.add(new Cauhoibai3("Can you name the vegetables in the picture below?","kohlrabi bulbs","radish","potato","carrot","carrot","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvCDRaucu%2F1524688181811.jpeg?alt=media&token=1bfd76f6-f260-48a1-b25e-6f8b7ded6ea3"));
        cauhoibai3s.add(new Cauhoibai3("Look at the picture and answer the question: what is this?","ruler", "crayon","spoon","pen","ruler","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2Fta-cai-thuoc-ke-cua-em-yeu-thich-nhat.jpg?alt=media&token=0d84182b-04f2-4b1d-a137-b4ae79ab5d24"));
        cauhoibai3s.add(new Cauhoibai3("How many fish are there in the picture?", "one","two","three","five","two","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2FO1CN01EuG8gM1cgaD5NpjOR_!!3973943630.jpg_400x400.jpg?alt=media&token=a4b11448-5740-4561-bd2d-d1a7d6cf2791"));
        cauhoibai3s.add(new Cauhoibai3("What is this fruit?", "Sapodilla fruit","Apple","Coconut","Papaya","Apple","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2Fistockphoto-183422512-612x612-1.jpg?alt=media&token=49f4537a-512a-4147-9fb1-0f2b21ab90b8"));

        cauhoibai3List=cauhoibai3s;
        Collections.shuffle(cauhoibai3List);
        cauhoibai3=cauhoibai3s.get(index);
        setAllDataCHBai3();

        CountDownTimer countDownTimer=new CountDownTimer(b1TimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                b1TimeLeftInMillis=millisUntilFinished;
                updateCountDownTimeBai2();
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(Bai3Activity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog_bai1);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.btDialogBai1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Bai3Activity.this, KetthucBai1Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();

        Exitbai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(Bai3Activity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.exit_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.btok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Bai3Activity.this, KiemtraActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    private void updateCountDownTimeBai2() {
        int minutesb1=(int) (b1TimeLeftInMillis/1000)/60;
        int secondsb1=(int) (b1TimeLeftInMillis/1000)%60;

        String timeBai1Formatted=String.format(Locale.getDefault(),"%02d:%02d",minutesb1,secondsb1);

        tvtimeBai3.setText(timeBai1Formatted);
    }

    private void setAllDataCHBai3() {
        tvquestionbai3.setText("Question "+questionbai3);
        tvCauhoibai3.setText(cauhoibai3.getCauhoibai3());
        tvdapan1bai3.setText(cauhoibai3.getDapan1bai3());
        tvdapan2bai3.setText(cauhoibai3.getDapan2bai3());
        tvdapan3bai3.setText(cauhoibai3.getDapan3bai3());
        tvdapan4bai3.setText(cauhoibai3.getDapan4bai3());
        Glide.with(this).load(cauhoibai3.getLinkanhCHbai3()).into(imvanhCHbai3);
    }
    private void GameWonB3(){
        Intent intent=new Intent(Bai3Activity.this, KetthucBai3Activity.class);
        intent.putExtra("correctB3",correctCountb3);
        intent.putExtra("wrongB3",wrongCountb3);
        intent.putExtra("diem",diem);
        startActivity(intent);
    }
    public void CorrectBai1(CardView cardView){
        cardView.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                correctCountb3++;
                questionbai3++;
                index++;
                diem=diem+10;
                tvdiembai3.setText(diem+"");
                cauhoibai3=cauhoibai3List.get(index);
                setAllDataCHBai3();
                resetColorcad();
            }
        },1000);
    }

    private void resetColorcad() {
        cadviewDA1bai3.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA2bai3.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA3bai3.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA4bai3.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    public void WrongBai2(CardView cardView){
        cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wrongCountb3++;
                if (index<cauhoibai3List.size()-1){
                    index++;
                    diem=diem+0;
                    tvdiembai3.setText( diem+"");
                    questionbai3++;
                    cauhoibai3=cauhoibai3List.get(index);
                    setAllDataCHBai3();
                    resetColorcad();
                }
                else {
                    GameWonB3();
                }
            }
        },1000);
    }


    private void Anhxa() {
        tvtimeBai3=findViewById(R.id.tvtimeBai3);
        tvdiembai3=findViewById(R.id.tvdiembai3);

        Exitbai3=findViewById(R.id.Exitbai3);
        tvquestionbai3=findViewById(R.id.tvquestionbai3);
        tvCauhoibai3=findViewById(R.id.tvCauhoibai3);
        tvdapan1bai3=findViewById(R.id.tvdapan1bai3);
        tvdapan2bai3=findViewById(R.id.tvdapan2bai3);
        tvdapan3bai3=findViewById(R.id.tvdapan3bai3);
        tvdapan4bai3=findViewById(R.id.tvdapan4bai3);

        imvanhCHbai3=findViewById(R.id.imvanhCHbai3);
        cadviewDA1bai3=findViewById(R.id.cadviewDA1bai3);
        cadviewDA2bai3=findViewById(R.id.cadviewDA2bai3);
        cadviewDA3bai3=findViewById(R.id.cadviewDA3bai3);
        cadviewDA4bai3=findViewById(R.id.cadviewDA4bai3);
    }

    public void ClickDA1b3(View view) {
        if (cauhoibai3.getDapan1bai3().equals(cauhoibai3.getDapanDbai3())){
            cadviewDA1bai3.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoibai3List.size()-1){
                CorrectBai1(cadviewDA1bai3);
            }
            else {
                GameWonB3();
            }
        }else {
            WrongBai2(cadviewDA1bai3);
        }
    }

    public void ClickDA2b3(View view) {
        if (cauhoibai3.getDapan2bai3().equals(cauhoibai3.getDapanDbai3())){
            cadviewDA2bai3.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoibai3List.size()-1){
                CorrectBai1(cadviewDA2bai3);
            }
            else {
                GameWonB3();
            }
        }else {
            WrongBai2(cadviewDA2bai3);
        }
    }

    public void ClickDA3bai3(View view) {
        if (cauhoibai3.getDapan3bai3().equals(cauhoibai3.getDapanDbai3())){
            cadviewDA3bai3.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoibai3List.size()-1){
                CorrectBai1(cadviewDA3bai3);
            }
            else {
                GameWonB3();
            }
        }else {
            WrongBai2(cadviewDA3bai3);
        }
    }

    public void ClickDA4bai3(View view) {
        if (cauhoibai3.getDapan4bai3().equals(cauhoibai3.getDapanDbai3())){
            cadviewDA4bai3.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoibai3List.size()-1){
                CorrectBai1(cadviewDA4bai3);
            }
            else {
                GameWonB3();
            }
        }else {
            WrongBai2(cadviewDA4bai3);
        }
    }
}