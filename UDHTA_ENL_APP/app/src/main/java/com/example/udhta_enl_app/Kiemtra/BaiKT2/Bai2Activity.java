package com.example.udhta_enl_app.Kiemtra.BaiKT2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.Kiemtra.BaiKT1.Bai1Activity;
import com.example.udhta_enl_app.Kiemtra.BaiKT1.CauHoibai1;
import com.example.udhta_enl_app.Kiemtra.BaiKT1.KetthucBai1Activity;
import com.example.udhta_enl_app.Kiemtra.KiemtraActivity;
import com.example.udhta_enl_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Bai2Activity extends AppCompatActivity {

    ArrayList<Cauhoibai2> cauhoibai2s;
    Cauhoibai2 cauhoibai2;
    int index=0;
    int correctCountb2=0;
    int wrongCountb2=0;
    int diem=0;
    int question=1;
    List<Cauhoibai2>cauhoibai2List;

    private static  final long START_TIME_IN_MILLIS_BAI1=600000;
    private long b1TimeLeftInMillis=START_TIME_IN_MILLIS_BAI1;

    TextView tvtimeBai2,tvdiembai2,tvquestionbai2,tvCauhoibai2,tvdapan1bai2,tvdapan2bai2,tvdapan3bai2,tvdapan4bai2,Exitbai2;
    ImageView imvanhCHbai2;
    CardView cadviewDA1bai2,cadviewDA2bai2,cadviewDA3bai2,cadviewDA4bai2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        
        Anhxa();

        cauhoibai2s=new ArrayList<>();
        cauhoibai2s.add(new Cauhoibai2("Can you name the flowers pictured below?","sunflower","rose","orchid","chrysanthemum","rose","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TVCDLoaihoa%2Frose.jpeg?alt=media&token=890984fc-bab4-4210-a995-04af19299e00"));
        cauhoibai2s.add(new Cauhoibai2("Can you name the flowers pictured below?","Sakura flower","Orchid","sunflower","lotus flower","sunflower","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TVCDLoaihoa%2Fimages.jfif?alt=media&token=56505b09-b5e7-47cb-808a-7ca5fd524ed0"));
        cauhoibai2s.add(new Cauhoibai2("'tomato' có nghĩa là gì?","cà chua","cà rốt","dưa leo","cà tím","cà chua",""));
        cauhoibai2s.add(new Cauhoibai2("'Rau mùi' trong Tiếng Anh là gì","eggplant","cabbage","coriander","cauliflower","cauliflower",""));
        cauhoibai2s.add(new Cauhoibai2("'coriander' có phiên âm như thế nào?"," /kɒriˈændər/","/ˈkæbɪdʒ/","/ˈeɡplænt/","/ˈkɔːliflaʊər/","/kɒriˈændər/",""));
        cauhoibai2s.add(new Cauhoibai2("'cabbage' và 'carrot' có nghĩa là gì","Cà rốt, cà chua","Bắp cải, cà rốt"," Rau mùi, hành tây","Hành tím, bắp cải"," Bắp cải, cà rốt",""));
        cauhoibai2s.add(new Cauhoibai2("Can you name the vegetables in the picture below?","kohlrabi bulbs","radish","potato","carrot","carrot","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvCDRaucu%2F1524688181811.jpeg?alt=media&token=1bfd76f6-f260-48a1-b25e-6f8b7ded6ea3"));
        cauhoibai2s.add(new Cauhoibai2("Look at the picture and answer the question: what is this?","ruler", "crayon","spoon","pen","ruler","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2Fta-cai-thuoc-ke-cua-em-yeu-thich-nhat.jpg?alt=media&token=0d84182b-04f2-4b1d-a137-b4ae79ab5d24"));
        cauhoibai2s.add(new Cauhoibai2("How many fish are there in the picture?", "one","two","three","five","two","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2FO1CN01EuG8gM1cgaD5NpjOR_!!3973943630.jpg_400x400.jpg?alt=media&token=a4b11448-5740-4561-bd2d-d1a7d6cf2791"));
        cauhoibai2s.add(new Cauhoibai2("What is this fruit?", "Sapodilla fruit","Apple","Coconut","Papaya","Apple","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2Fistockphoto-183422512-612x612-1.jpg?alt=media&token=49f4537a-512a-4147-9fb1-0f2b21ab90b8"));

        cauhoibai2List=cauhoibai2s;
        Collections.shuffle(cauhoibai2List);
        cauhoibai2=cauhoibai2s.get(index);
        setAllDataCHBai2();

        CountDownTimer countDownTimer=new CountDownTimer(b1TimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                b1TimeLeftInMillis=millisUntilFinished;
                updateCountDownTimeBai2();
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(Bai2Activity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog_bai1);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.btDialogBai1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Bai2Activity.this,KetthucBai1Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();

        Exitbai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(Bai2Activity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.exit_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.btok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Bai2Activity.this, KiemtraActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

    }

    private void setAllDataCHBai2() {
        tvquestionbai2.setText("Question "+question);
        tvCauhoibai2.setText(cauhoibai2.getCauhoibai2());
        tvdapan1bai2.setText(cauhoibai2.getDapan1bai2());
        tvdapan2bai2.setText(cauhoibai2.getDapan2bai2());
        tvdapan3bai2.setText(cauhoibai2.getDapan3bai2());
        tvdapan4bai2.setText(cauhoibai2.getDapan4bai2());
        Glide.with(this).load(cauhoibai2.getLinkanhCHbai2()).into(imvanhCHbai2);
    }

    private void updateCountDownTimeBai2() {
        int minutesb1=(int) (b1TimeLeftInMillis/1000)/60;
        int secondsb1=(int) (b1TimeLeftInMillis/1000)%60;

        String timeBai1Formatted=String.format(Locale.getDefault(),"%02d:%02d",minutesb1,secondsb1);

        tvtimeBai2.setText(timeBai1Formatted);
    }
    private void GameWonB2(){
        Intent intent=new Intent(Bai2Activity.this,KetthucBai2Activity.class);
        intent.putExtra("correctB2",correctCountb2);
        intent.putExtra("wrongB2",wrongCountb2);
        intent.putExtra("diem",diem);
        startActivity(intent);
    }
    public void CorrectBai1(CardView cardView){
        cardView.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                correctCountb2++;
                question++;
                index++;
                diem=diem+10;
                tvdiembai2.setText(diem+"");
                cauhoibai2=cauhoibai2List.get(index);
                setAllDataCHBai2();
                resetColorcad();
            }
        },1000);
    }

    private void resetColorcad() {
        cadviewDA1bai2.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA2bai2.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA3bai2.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA4bai2.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    public void WrongBai2(CardView cardView){
        cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wrongCountb2++;
                if (index<cauhoibai2List.size()-1){
                    index++;
                    diem=diem+0;
                    tvdiembai2.setText( diem+"");
                    question++;
                    cauhoibai2=cauhoibai2List.get(index);
                    setAllDataCHBai2();
                    resetColorcad();
                }
                else {
                    GameWonB2();
                }
            }
        },1000);
    }

    private void Anhxa() {
        tvtimeBai2=findViewById(R.id.tvtimeBai2);
        tvdiembai2=findViewById(R.id.tvdiembai2);

        Exitbai2=findViewById(R.id.Exitbai2);
        tvquestionbai2=findViewById(R.id.tvquestionbai2);
        tvCauhoibai2=findViewById(R.id.tvCauhoibai2);
        tvdapan1bai2=findViewById(R.id.tvdapan1bai2);
        tvdapan2bai2=findViewById(R.id.tvdapan2bai2);
        tvdapan3bai2=findViewById(R.id.tvdapan3bai2);
        tvdapan4bai2=findViewById(R.id.tvdapan4bai2);

        imvanhCHbai2=findViewById(R.id.imvanhCHbai2);
        cadviewDA1bai2=findViewById(R.id.cadviewDA1bai2);
        cadviewDA2bai2=findViewById(R.id.cadviewDA2bai2);
        cadviewDA3bai2=findViewById(R.id.cadviewDA3bai2);
        cadviewDA4bai2=findViewById(R.id.cadviewDA4bai2);
    }
    public void ClickDA1b2(View view) {
        if (cauhoibai2.getDapan1bai2().equals(cauhoibai2.getDapanDbai2())){
            cadviewDA1bai2.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoibai2List.size()-1){
                CorrectBai1(cadviewDA1bai2);
            }
            else {
                GameWonB2();
            }
        }else {
            WrongBai2(cadviewDA1bai2);
        }
    }

    public void ClickDA2b2(View view) {
        if (cauhoibai2.getDapan2bai2().equals(cauhoibai2.getDapanDbai2())){
            cadviewDA2bai2.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoibai2List.size()-1){
                CorrectBai1(cadviewDA2bai2);
            }
            else {
                GameWonB2();
            }
        }else {
            WrongBai2(cadviewDA2bai2);
        }
    }

    public void ClickDA3bai2(View view) {
        if (cauhoibai2.getDapan3bai2().equals(cauhoibai2.getDapanDbai2())){
            cadviewDA3bai2.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoibai2List.size()-1){
                CorrectBai1(cadviewDA3bai2);
            }
            else {
                GameWonB2();
            }
        }else {
            WrongBai2(cadviewDA3bai2);
        }
    }

    public void ClickDA4bai2(View view) {
        if (cauhoibai2.getDapan4bai2().equals(cauhoibai2.getDapanDbai2())){
            cadviewDA4bai2.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoibai2List.size()-1){
                CorrectBai1(cadviewDA4bai2);
            }
            else {
                GameWonB2();
            }
        }else {
            WrongBai2(cadviewDA4bai2);
        }
    }
}