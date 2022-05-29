package com.example.udhta_enl_app.Kiemtra.BaiKT1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh.CauhoiCDGiadinh;
import com.example.udhta_enl_app.Kiemtra.KiemtraActivity;
import com.example.udhta_enl_app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Bai1Activity extends AppCompatActivity {

    ArrayList<CauHoibai1> cauHoibai1s;
    CauHoibai1 cauHoibai1;
    int index=0;
    int correctCountb1=0;
    int wrongCountb1=0;
    int diem=0;
    int questionb1=1;
    List<CauHoibai1>cauHoibai1List;

    private static  final long START_TIME_IN_MILLIS_BAI1=600000;
    private long b1TimeLeftInMillis=START_TIME_IN_MILLIS_BAI1;
    TextView tvtimeBai1,tvdiembai1,tvCauhoibai1,tvdapan1bai1,tvdapan2bai1,tvdapan3bai1,tvdapan4bai1,tvquestionbai1,Exitbai1;
    CardView cadviewDA1bai1,cadviewDA2bai1,cadviewDA3bai1,cadviewDA4bai1;
    ImageView imvanhCHbai1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        AnhXa();



        cauHoibai1s=new ArrayList<>();
        cauHoibai1s.add(new CauHoibai1(". My brother and I .............television every evening.","watches","listen","are","watch","watch",""));
        cauHoibai1s.add(new CauHoibai1("There is a well ................Minh’s house.","behind","next","near to","to the left","behind",""));
        cauHoibai1s.add(new CauHoibai1("Đố bạn biết đây là con gì?","Dinosaur","Elephant","Chicken","Duck","Chicken","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/bai1%2F1632770699_758_Hinh-anh-con-ga-trong-dep.jpg?alt=media&token=fe6917e5-6e12-455f-8a6a-5a6fe98698f3"));
        cauHoibai1s.add(new CauHoibai1("Từ 'mẹ' trong tiếng anh là gì?","Mother","Father","Grandmother","Cousin","Mother",""));
        cauHoibai1s.add(new CauHoibai1("Con gì đây nhỉ?","Elephant","Tiger","Monkey","Dog","Dog","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/bai1%2Fcho-alaska-va-husky.jpg?alt=media&token=4afd4279-0530-437c-bcc6-23a428a1d1ed"));
        cauHoibai1s.add(new CauHoibai1("Cousin có nghĩa là gì nhỉ?","Ông bà","Bố","Anh/chị/em họ","Anh/chị/em ruột","Anh/chị/em họ",""));
        cauHoibai1s.add(new CauHoibai1("Con khỉ trong tiếng anh là gì","Fox","Pig","Monkey","Dolphin","Monkey",""));
        cauHoibai1s.add(new CauHoibai1("Parents nghĩa là gì?","Người dì", "Anh/chị/em","Ba mẹ","Con cái","Ba mẹ",""));
        cauHoibai1s.add(new CauHoibai1("Wife nghĩa là gì?", "Con gái","Vợ","Con trai","Anh rể, em rể","Vợ",""));
        cauHoibai1s.add(new CauHoibai1("Brother-in-law nghĩa là gì", "Anh rể, em rể","Ông ngoại/ ông nội","Cháu gái ( của cô/ dì/ chú …)","Anh rể","Anh rể",""));
        cauHoibai1s.add(new CauHoibai1("Nephew nghĩa là gì?", "Cháu trai (của ông bà)","cháu trai ( của cô/ dì/ chú …)","Bố chồng/ vợ","Ông bà","cháu trai ( của cô/ dì/ chú …)",""));
        cauHoibai1s.add(new CauHoibai1("Dolphin có nghĩa là con gì","Con voi","Con cá heo","Con cá mập","Con khủng long","Con cá heo","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/bai1%2FDolphin.jpg?alt=media&token=76fcb6d4-c262-4c44-acb3-dfd64a2087d5"));
        cauHoibai1s.add(new CauHoibai1("……..I help you?","Do","Can","Am","Would","Would",""));
        cauHoibai1s.add(new CauHoibai1("My ... will visit me in New year.", "aunt","relative","birth","mother-in-law","aunt","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/Aunt.jpg?alt=media&token=ee011f40-cbae-44a5-81d0-d96c2ce6a74b"));
        cauHoibai1s.add(new CauHoibai1("Every summer, Tim gets to visit his ... house and stays there for a month.", "uncle","sister","grandparents","relative","grandparents","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/Grandparents.jpg?alt=media&token=804d1d38-3d24-4f86-965b-7455bef901f7"));
        cauHoibai1s.add(new CauHoibai1("I have two ...s, one is 10 and the other is 17.", "brother","uncle","grandparents","sibling","brother","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/brother.jpg?alt=media&token=ef113f05-f72d-491c-b6a0-bea94f6b1511"));
        cauHoibai1s.add(new CauHoibai1("Moderm women don't like to live with their ... .", "mother-in-law","wife","pregnant","grandparents","mother-in-law","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/Mother-in-Law.jpg?alt=media&token=7c4dcefa-13f5-405c-8427-dc68355d4dec"));
        cauHoibai1s.add(new CauHoibai1("Every member in Clare's family is ... yo eachother.","father","brother","close","uncle","close","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/close.jpg?alt=media&token=bd95620a-c77a-4d73-bec5-efcc97b3e0c7"));
        cauHoibai1s.add(new CauHoibai1("Mẹ của bố bạn là ai?.","Parents-in-law","Grandfather","Sibling","Grandfmother","close",""));
        cauHoibai1s.add(new CauHoibai1("Con gái của chú bạn là ai?.","Parents-in-law","Sister","Cousin","Grandson","Cousin",""));
        cauHoibai1s.add(new CauHoibai1("Who is your father's son?.","Ralative","Mother","Brother","Granddaughter","Brother",""));
        cauHoibai1s.add(new CauHoibai1("Con gái của ông nội bạn là ai?.","Mother","Father","Cousin","Brother","Mother",""));
        cauHoibai1s.add(new CauHoibai1("Đây là con gì?","Cow","Mouse","Bear","Cat","Mouse","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/bai1%2Fmouse.jpg?alt=media&token=49cad060-ceb3-4fb1-bf45-22b608c052c9"));
        cauHoibai1s.add(new CauHoibai1("Con mèo trong tiếng anh là gì?","Cat","Dog","Bear","Cow","Cat","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/bai1%2Fcat.jpg?alt=media&token=f04b357d-8923-4c7e-a124-ee42640d13cb"));
        cauHoibai1s.add(new CauHoibai1("Chọn từ phù hợp với hình ảnh?","Horse","Giraffe","Lion","Duck","Horse","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/bai1%2Fmau-ngo-6.jpg?alt=media&token=ca6c0c6a-bfd7-41ed-9b57-6c97f5d9acde"));
        cauHoibai1s.add(new CauHoibai1("Đây là con gì?.","Lion","Bear","Tiger","Fish","Lion","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/bai1%2FLion.jpg?alt=media&token=5fad854c-2a64-4caf-ba8f-1a8350eef568"));
        cauHoibai1s.add(new CauHoibai1("Who is your aunt's husband?","Sister","Brother","Cousin","Uncle","Uncle",""));
        cauHoibai1s.add(new CauHoibai1("Who is your wife's brother?.","Brother-in-law","Mother","Brother-in-law","Sister","Brother-in-law",""));
        cauHoibai1s.add(new CauHoibai1("Who is your sister's son?","Parents-in-law","Uncle","Cousin","Nephew","Nephew",""));
        cauHoibai1s.add(new CauHoibai1("Con gì đây nhỉ?","Rabbit","Elephant","Butterfly","Chicken","Rabbit","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/bai1%2FRabbit.jpg?alt=media&token=bbb96edb-3b43-495a-a1c7-b11da6b27655"));

        cauHoibai1List=cauHoibai1s;
        Collections.shuffle(cauHoibai1List);
        cauHoibai1=cauHoibai1s.get(index);
        setAllDataCHBai1();

        CountDownTimer countDownTimer=new CountDownTimer(b1TimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                b1TimeLeftInMillis=millisUntilFinished;
                updateCountDownTimeBai1();
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(Bai1Activity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog_bai1);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.btDialogBai1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Bai1Activity.this,KetthucBai1Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();


        Exitbai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(Bai1Activity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.exit_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.btok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Bai1Activity.this, KiemtraActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });



    }

    private void setAllDataCHBai1() {

        tvquestionbai1.setText("Question "+questionb1);

        tvCauhoibai1.setText(cauHoibai1.getCauhoibai1());
        tvdapan1bai1.setText(cauHoibai1.getDapan1bai1());
        tvdapan2bai1.setText(cauHoibai1.getDapan2bai1());
        tvdapan3bai1.setText(cauHoibai1.getDapan3bai1());
        tvdapan4bai1.setText(cauHoibai1.getDapan4bai1());
        Glide.with(this).load(cauHoibai1.getLinkanhCHbai1()).into(imvanhCHbai1);
    }

    private void updateCountDownTimeBai1() {
        int minutesb1=(int) (b1TimeLeftInMillis/1000)/60;
        int secondsb1=(int) (b1TimeLeftInMillis/1000)%60;

        String timeBai1Formatted=String.format(Locale.getDefault(),"%02d:%02d",minutesb1,secondsb1);

        tvtimeBai1.setText(timeBai1Formatted);
    }
    private void GameWonB1(){
        Intent intent=new Intent(Bai1Activity.this,KetthucBai1Activity.class);
        intent.putExtra("correctb1",correctCountb1);
        intent.putExtra("wrongb1",wrongCountb1);
        intent.putExtra("diem",diem);
        startActivity(intent);
    }

    public void CorrectBai1(CardView cardView){
        cardView.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                correctCountb1++;
                index++;
                questionb1++;
                diem=diem+3;

                tvdiembai1.setText(diem+"");
                cauHoibai1=cauHoibai1List.get(index);
                setAllDataCHBai1();
                resetColorcad();
            }
        },1000);
    }
    public void WrongBai1(CardView cardView){
        cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wrongCountb1++;
                if (index<cauHoibai1List.size()-1){
                    index++;
                    questionb1++;
                    diem=diem+0;
                    tvdiembai1.setText( diem+"");
                    cauHoibai1=cauHoibai1List.get(index);
                    setAllDataCHBai1();
                    resetColorcad();
                }
                else {
                    GameWonB1();
                }
            }
        },1000);
    }

    private void AnhXa() {
        tvtimeBai1=findViewById(R.id.tvtimeBai1);
        tvdiembai1=findViewById(R.id.tvdiembai1);
        tvCauhoibai1=findViewById(R.id.tvCauhoibai1);
        tvdapan1bai1=findViewById(R.id.tvdapan1bai1);
        tvdapan2bai1=findViewById(R.id.tvdapan2bai1);
        tvdapan3bai1=findViewById(R.id.tvdapan3bai1);
        tvdapan4bai1=findViewById(R.id.tvdapan4bai1);
        imvanhCHbai1=findViewById(R.id.imvanhCHbai1);
        tvquestionbai1=findViewById(R.id.tvquestionbai1);
        Exitbai1=findViewById(R.id.Exitbai1);


        cadviewDA1bai1=findViewById(R.id.cadviewDA1bai1);
        cadviewDA2bai1=findViewById(R.id.cadviewDA2bai1);
        cadviewDA3bai1=findViewById(R.id.cadviewDA3bai1);
        cadviewDA4bai1=findViewById(R.id.cadviewDA4bai1);
    }
    public void  resetColorcad(){
        cadviewDA1bai1.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA2bai1.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA3bai1.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA4bai1.setCardBackgroundColor(getResources().getColor(R.color.white));

    }

    public void ClickDA1b1(View view) {
        if (cauHoibai1.getDapan1bai1().equals(cauHoibai1.getDapanDbai1())){
            cadviewDA1bai1.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauHoibai1List.size()-1){
                CorrectBai1(cadviewDA1bai1);
            }
            else {
                GameWonB1();
            }
        }else {
            WrongBai1(cadviewDA1bai1);
        }
    }

    public void ClickDA2b1(View view) {
        if (cauHoibai1.getDapan2bai1().equals(cauHoibai1.getDapanDbai1())){
            cadviewDA2bai1.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauHoibai1List.size()-1){
                CorrectBai1(cadviewDA2bai1);
            }
            else {
                GameWonB1();
            }
        }else {
            WrongBai1(cadviewDA2bai1);
        }
    }

    public void ClickDA3bai1(View view) {
        if (cauHoibai1.getDapan3bai1().equals(cauHoibai1.getDapanDbai1())){
            cadviewDA3bai1.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauHoibai1List.size()-1){
                CorrectBai1(cadviewDA3bai1);
            }
            else {
                GameWonB1();
            }
        }else {
            WrongBai1(cadviewDA3bai1);
        }
    }

    public void ClickDA4bai1(View view) {
        if (cauHoibai1.getDapan4bai1().equals(cauHoibai1.getDapanDbai1())){
            cadviewDA4bai1.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauHoibai1List.size()-1){
                CorrectBai1(cadviewDA4bai1);
            }
            else {
                GameWonB1();
            }
        }else {
            WrongBai1(cadviewDA4bai1);
        }
    }


}