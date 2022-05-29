package com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh;

import androidx.annotation.NonNull;
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
import com.example.udhta_enl_app.HoctapActivity;
import com.example.udhta_enl_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CauhoiCDGDActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextToSpeech textToSpeech;
    MediaPlayer mediaPlayer;
    private static final long START_TIME_IN_MILLIS=600000;
    int timeQuestion=30;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis=START_TIME_IN_MILLIS;
    RoundedHorizontalProgressBar progressBar;

    ArrayList<CauhoiCDGiadinh> cauhoiCDGiadinhs;
    DatabaseReference databaseReference;

    List<CauhoiCDGiadinh> cauhoiCDGiadinhList;
    CauhoiCDGiadinh cauhoiCDGiadinh;
    TextView tvquestionb,tvdapan1,tvdapan2,tvdapan3,tvdapan4,tvquestion,tvNDquestion,tvdichcauhoi,tvghichuquestion,tvnext,textViewTime;
    ImageView imageAnhMinhhoa;
    CardView cadviewDA1,cadviewDA2,cadviewDA3,cadviewDA4;
    int index=0;
    int correctCount=0;
    int wrongCount=0;
    int question=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoi_cdgdactivity);

        Anhxa();



        cauhoiCDGiadinhs=new ArrayList<>();

//        databaseReference= FirebaseDatabase.getInstance().getReference("QuestionCDGD");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    CauhoiCDGiadinh cauhoiCDGiadinh=dataSnapshot.getValue(CauhoiCDGiadinh.class);
//                    cauhoiCDGiadinhList.add(cauhoiCDGiadinh);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("I have a beautidull 5-year-old ... named Linh","Tôi có một người em họ 5 tuổi xinh xắn tên là Linh","Mother","Father","Grandfather","Foster mother","Mother","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/istockphoto-1253920275-612x612.jpg?alt=media&token=77185a7c-42a0-4cd5-9a8c-f061f10bf4e0", "Anh chị em họ"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Barry has some ...s living in Vietnam","Barry có vài người học hàng sống ở Vietnam","Ralative","Sister","Father","Sibling","Ralative","", "Họ hàng"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("There is often competition between ...s","Thông thường sẽ có sự cạnh tranh giữa anh chị em trong một gia đình", "Sibling","Husband","Ralative","Wife","Sibling","", " anh (chị, em) ruột"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("My ... is kind and generous man","Bố tôi là một người tốt bụng và hào phóng", "Cousin","Father","Mother","Birth","Father","", "Cha/Bố"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("My ... will visit me in New year.","Dì tôi sẽ tới thăm thôi vào dịp năm mới", "aunt","relative","birth","mother-in-law","aunt","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/Aunt.jpg?alt=media&token=ee011f40-cbae-44a5-81d0-d96c2ce6a74b", "Người dì, người mà là em gái của bố hoặc mẹ bạn"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Every summer, Tim gets to visit his ... house and stays there for a month.","Mỗi mùa hè, Tim đều được đến thăm nhà ông bà và ở lại chơi trong một tháng.", "uncle","sister","grandparents","relative","grandparents","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/Grandparents.jpg?alt=media&token=804d1d38-3d24-4f86-965b-7455bef901f7", "Ông bà"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("I have two ...s, one is 10 and the other is 17.","Tôi có 2 anh em trai, một người 10 tuổi và người còn lại 17.", "brother","uncle","grandparents","sibling","brother","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/brother.jpg?alt=media&token=ef113f05-f72d-491c-b6a0-bea94f6b1511", "Anh/em trai"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Moderm women don't like to live with their ... .","Phụ nữ hiện đại không thích sống chung với mẹ chồng.", "mother-in-law","wife","pregnant","grandparents","mother-in-law","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/Mother-in-Law.jpg?alt=media&token=7c4dcefa-13f5-405c-8427-dc68355d4dec", "Mẹ chồng"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Every member in Clare's family is ... yo eachother.","Mọi thành viên trong gia đình Clara đều rất gần gũi với nhau.", "father","brother","close","uncle","close","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/close.jpg?alt=media&token=bd95620a-c77a-4d73-bec5-efcc97b3e0c7", "Gần gũi, gắn bó"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Her ... has just come home after a long trip from the US.","Chồng cô ấy vừa mới về nhà sau một chuyến đi dài từ Mĩ.", "husband","brother","parents","son","husband","", "Người chồng"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("People number 1 are called ... ","Những người số 1 được gọi là ...", "Grandparents","Grandson","Granddaughter","Sister","Grandparents","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/T%E1%BB%AB%20v%E1%BB%B1ng%2Ftrac-nghiem-tu-vung-tieng-anh-chu-de-gia-dinh-1.png?alt=media&token=e5afa607-3582-438e-8eeb-b0fbccd95dd4", "Người bà"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("The person number 2 is called ...","Người số 2 được gọi là ...", "Granddaughter","Grandson","Mother","Grandparents","Grandson","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/T%E1%BB%AB%20v%E1%BB%B1ng%2Ftrac-nghiem-tu-vung-tieng-anh-chu-de-gia-dinh-1.png?alt=media&token=e5afa607-3582-438e-8eeb-b0fbccd95dd4", "Cháu trai"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("The person number 3 is called ...","", "brother","brother","Granddaughter","Sibling","Granddaughter","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/T%E1%BB%AB%20v%E1%BB%B1ng%2Ftrac-nghiem-tu-vung-tieng-anh-chu-de-gia-dinh-1.png?alt=media&token=e5afa607-3582-438e-8eeb-b0fbccd95dd4", "Cháu gái"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("People number 4 are called ...","Những người số 4 được gọi là", "parents-in-law","son","Mother","daughter-in-law","parents-in-law","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/T%E1%BB%AB%20v%E1%BB%B1ng%2Ftrac-nghiem-tu-vung-tieng-anh-chu-de-gia-dinh-2.png?alt=media&token=03a5753f-c214-449a-aeb1-b41ac7c1f85a", "Cha mẹ chồng"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("The person number 5 is called...","", "son","wife","brother","daughter-in-law","son","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/T%E1%BB%AB%20v%E1%BB%B1ng%2Ftrac-nghiem-tu-vung-tieng-anh-chu-de-gia-dinh-2.png?alt=media&token=03a5753f-c214-449a-aeb1-b41ac7c1f85a", "Con trai \n \"son and heir : con trai cả, con thừa tự\""));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("The person number 6 is called...","", "Cousin","Daughter-in-law","Godmother","Sibling ","daughter-in-law","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/T%E1%BB%AB%20v%E1%BB%B1ng%2Ftrac-nghiem-tu-vung-tieng-anh-chu-de-gia-dinh-2.png?alt=media&token=03a5753f-c214-449a-aeb1-b41ac7c1f85a", "Con dâu"));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Parents nghĩa là gì?","", "Ba","Mẹ","Con cái","Ba mẹ","Father","", ""));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Wife nghĩa là gì?","", "Con gái","Vợ","Con trai","Anh rể, em rể","Vợ","", ""));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Brother-in-law nghĩa là gì","", "Anh rể, em rể","Ông ngoại/ ông nội","Cháu gái ( của cô/ dì/ chú …)","Anh chị em họ","Anh rể, em rể","", ""));
        cauhoiCDGiadinhs.add(new CauhoiCDGiadinh("Nephew nghĩa là gì?","", "Cháu trai (của ông bà)","cháu trai ( của cô/ dì/ chú …)","Bố chồng/ vợ","Ông bà","cháu trai ( của cô/ dì/ chú …)","", ""));




        countDownTimer=new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis=millisUntilFinished;
                uppdateCountDownText();

            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(CauhoiCDGDActivity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.buttonTryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(CauhoiCDGDActivity.this, HoctapActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        }.start();

        cauhoiCDGiadinhList=cauhoiCDGiadinhs;
        Collections.shuffle(cauhoiCDGiadinhList);
        cauhoiCDGiadinh= cauhoiCDGiadinhs.get(index);
        setAlldata();
    }

    private void uppdateCountDownText() {
        int minutes=(int) (mTimeLeftInMillis/1000)/60;
        int seconds=(int) (mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        textViewTime.setText(timeLeftFormatted);
    }

    private void setAlldata() {
        tvNDquestion.setText(cauhoiCDGiadinh.getCauhoiCDGD());
        tvdichcauhoi.setText(cauhoiCDGiadinh.getDichCHCDGD());
        tvghichuquestion.setText(cauhoiCDGiadinh.getGhiChuCH());
        tvdapan1.setText(cauhoiCDGiadinh.getDapan1());
        tvdapan2.setText(cauhoiCDGiadinh.getDapan2());
        tvdapan3.setText(cauhoiCDGiadinh.getDapan3());
        tvdapan4.setText(cauhoiCDGiadinh.getDapan4());
        Glide.with(this).load(cauhoiCDGiadinh.getLinkAnhMH()).into(imageAnhMinhhoa);

    }

    private void Anhxa() {
        tvdapan1=findViewById(R.id.tvdapan1);
        tvdapan2=findViewById(R.id.tvdapan2);
        tvdapan3=findViewById(R.id.tvdapan3);
        tvdapan4=findViewById(R.id.tvdapan4);
        tvquestion=findViewById(R.id.tvquestion);
        tvNDquestion=findViewById(R.id.tvNDquestion);
        tvdichcauhoi=findViewById(R.id.tvdichcauhoi);
        tvghichuquestion=findViewById(R.id.tvghichuquestion);
        textViewTime=findViewById(R.id.textViewTime);
        tvquestionb=findViewById(R.id.tvquestionb);

        imageAnhMinhhoa=findViewById(R.id.imageAnhMinhhoa);


        cadviewDA1=findViewById(R.id.cadviewDA1);
        cadviewDA2=findViewById(R.id.cadviewDA2);
        cadviewDA3=findViewById(R.id.cadviewDA3);
        cadviewDA4=findViewById(R.id.cadviewDA4);

    }
    public void Correct(CardView cardView){
//        StartTimeQuestion();
        amthanh(R.raw.amthanh_dung);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                correctCount++;
                index++;
                question=question+1;
                tvquestionb.setText(question+"");
                cauhoiCDGiadinh= cauhoiCDGiadinhList.get(index);
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
                wrongCount++;
                if (index<cauhoiCDGiadinhList.size()-1){
                    index++;
                    question=question+1;
                    tvquestionb.setText(question+"");
                    cauhoiCDGiadinh= cauhoiCDGiadinhList.get(index);
                    setAlldata();
                    resetColor();

                }
                else {
                    GameWon();
                }
            }
        }, 1500);
    }

    private void GameWon() {

        Intent intent=new Intent(CauhoiCDGDActivity.this,WonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);
    }

//    private void StartTimeQuestion(){
//        new CountDownTimer(30000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeQuestion=timeQuestion-1;
//                progressBar.setProgress(timeQuestion);
//
//            }
//
//            @Override
//            public void onFinish() {
//                Dialog dialog=new Dialog(CauhoiCDGDActivity.this,R.style.Dialoge);
//                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//                dialog.setContentView(R.layout.time_out_dialog);
//                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//                dialog.show();
//
//                dialog.findViewById(R.id.buttonTryAgain).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent= new Intent(CauhoiCDGDActivity.this, CauhoiCDGDActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }
//        }.start();
//    }

    public void amthanh(int at){
        mediaPlayer=MediaPlayer.create(this,at);
        mediaPlayer.start();

    }
    public void  resetColor(){
        cadviewDA1.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA2.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA3.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA4.setCardBackgroundColor(getResources().getColor(R.color.white));

    }
    public void ClickDA1(View view) {
        if (cauhoiCDGiadinh.getDapan1().equals(cauhoiCDGiadinh.getDapan())){
            cadviewDA1.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoiCDGiadinhList.size()-1){
                Correct(cadviewDA1);
            }
            else {
                GameWon();
            }
        }
        else {
            Wrong(cadviewDA1);
        }
    }

    public void ClickDA2(View view) {
        if (cauhoiCDGiadinh.getDapan2().equals(cauhoiCDGiadinh.getDapan())){
            cadviewDA2.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoiCDGiadinhList.size()-1){
                Correct(cadviewDA2);
            }
            else {
                GameWon();
            }
        }
        else {
            Wrong(cadviewDA2);
        }
    }

    public void ClickDA3(View view) {
        if (cauhoiCDGiadinh.getDapan3().equals(cauhoiCDGiadinh.getDapan())){
            cadviewDA3.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoiCDGiadinhList.size()-1){
                Correct(cadviewDA3);
            }
            else {
                GameWon();
            }
        }
        else {
            Wrong(cadviewDA3);
        }
    }
    public void ClickDA4(View view) {
        if (cauhoiCDGiadinh.getDapan4().equals(cauhoiCDGiadinh.getDapan())){
            cadviewDA4.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index<cauhoiCDGiadinhList.size()-1){
                Correct(cadviewDA4);
            }
            else {
                GameWon();
            }
        }
        else {
            Wrong(cadviewDA4);
        }
    }



}