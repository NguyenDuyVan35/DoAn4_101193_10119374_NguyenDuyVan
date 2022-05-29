package com.example.udhta_enl_app.HocTap.CDDodunghoctap.TestCDddht;

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
import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TvCDDodunghoctapActivity;
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

public class ChCdddhtActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextToSpeech textToSpeech;
    MediaPlayer mediaPlayer;
    private static final long START_TIME_IN_MILLIS=300000;
    int timeQuestion=30;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis=START_TIME_IN_MILLIS;
    RoundedHorizontalProgressBar progressBar;

    ArrayList<CauhoiDDHT> cauhoiDDHTArrayList;
    DatabaseReference databaseReference;

    List<CauhoiDDHT> cauhoiDDHTList;
    CauhoiDDHT cauhoiDDHT;
    TextView tvquestionbddht,textViewTimeddht,tvNDquestiondd,tvdichcauhoidd,tvghichuQddht,tvdapan1ddht,tvdapan2ddht,tvdapan3ddht
            ,tvdapan4ddht;
    ImageView imageAnhddht;
    CardView cadviewDA1ddht,cadviewDA2ddht,cadviewDA3ddht,cadviewDA4ddht;
    int index1=0;
    int correctCount1=0;
    int wrongCount1=0;
    int question1=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_cdddht);
        Anhxa();



        cauhoiDDHTArrayList=new ArrayList<>();

        cauhoiDDHTArrayList.add(new CauhoiDDHT("Choose the word that matches the picture","Chọn từ phù hợp với hình ảnh","glue","pen","pencil","map","pencil","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2FPencil.png?alt=media&token=dc36d80a-0045-4fcb-850f-47f45d76dd76", "Một chiếc bút dùng trong giờ học vẽ"));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("(bag) và (pack) có nghĩa lần lượt là:","","Cái túi; túi đeo, ba lô","Cái túi, bảng","Bảng, phấn","Cái túi, phấn","Cái túi; túi đeo, ba lô","", "Dùng để đựng đồ đạc hoặc sách vở"));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("Choose the word that matches the picture","", "schoolbag","scissors","pen","ruler","pen","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2Fpen.png?alt=media&token=6ba01f78-8c8d-4a92-be32-7a41c874692b", ""));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("Choose the word that matches the picture","", "sharpener","ruler","paints","Notebook","paints","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2Fpaints.jpg?alt=media&token=c85e2efc-25c5-42cb-af8c-66ea882dc7e7", "Dùng để tô màu"));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("Marker có nghĩa là gì?","", "Bút chì","Cái cặp","Cục tẩy","Bút tẩy","Bút chì","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2FMarker.png?alt=media&token=1ba6a0d4-8d2e-447c-8352-cf51d98c0f88", "Một chiếc bút dùng trong giờ học vẽ"));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("Bút sáp màu tiếng Anh là gì?","", "Notebook","sharpener","Crayon","pen","Crayon","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2FCrayon.jpg?alt=media&token=f03e3fb7-2bea-457b-a86a-8a5bcef8624a", "Cây bút dùng để tô màu"));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("Choose the word that matches the picture","", "Chalk","pen","pencil","Crayon","Chalk","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2FPicture1.png?alt=media&token=20b3d7d3-cc64-4412-b6d6-89b3fbfe37cd", "Sử dụng khi viết bảng"));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("Text book có nghĩa là gì","", "sách giáo khoa","túi đeo  lưng","máy tính","phấn","sách giáo khoa","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2FText-books.jpg?alt=media&token=3b157e79-cd94-433d-aa77-9f3a40831114", "Một quyển sách không thể thiếu trong mỗi giờ học"));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("Ruler có nghĩa là gì?","", "Cục tẩy","hộp bút","cái gọt bút chì","Thước kẻ","Thước kẻ","", "Dùng để vẽ các hình vuông, tròn, tam giác..."));
        cauhoiDDHTArrayList.add(new CauhoiDDHT("Choose the word that matches the picture","", "pencil","notebook","scissors","Sharpener","scissors","https://firebasestorage.googleapis.com/v0/b/udhocen.appspot.com/o/TvDDHT%2Fscissors.jpg?alt=media&token=b620668d-cc2f-4384-b8cf-3b794124ae11", "Dùng để cắt"));



        countDownTimer=new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis=millisUntilFinished;
                uppdateCountDownText();

            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(ChCdddhtActivity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                dialog.findViewById(R.id.buttonTryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(ChCdddhtActivity.this, WonDDHTActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        }.start();

        cauhoiDDHTList=cauhoiDDHTArrayList;
        Collections.shuffle(cauhoiDDHTList);
        cauhoiDDHT= cauhoiDDHTArrayList.get(index1);
        setAlldata();
    }

    private void uppdateCountDownText() {
        int minutes=(int) (mTimeLeftInMillis/1000)/60;
        int seconds=(int) (mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        textViewTimeddht.setText(timeLeftFormatted);
    }

    private void setAlldata() {
        tvNDquestiondd.setText(cauhoiDDHT.getCauhoiDDHT());
        tvdichcauhoidd.setText(cauhoiDDHT.getDichCHCDDDHT());
        tvghichuQddht.setText(cauhoiDDHT.getGhiChuCHDDHT());
        tvdapan1ddht.setText(cauhoiDDHT.getDapan1DDHT());
        tvdapan2ddht.setText(cauhoiDDHT.getDapan2DDHT());
        tvdapan3ddht.setText(cauhoiDDHT.getDapan3DDHT());
        tvdapan4ddht.setText(cauhoiDDHT.getDapan4DDHT());
        Glide.with(this).load(cauhoiDDHT.getLinkAnhMHDDHT()).into(imageAnhddht);

    }

    private void Anhxa() {
        tvdapan1ddht=findViewById(R.id.tvdapan1ddht);
        tvdapan2ddht=findViewById(R.id.tvdapan2ddht);
        tvdapan3ddht=findViewById(R.id.tvdapan3ddht);
        tvdapan4ddht=findViewById(R.id.tvdapan4ddht);
        tvNDquestiondd=findViewById(R.id.tvNDquestiondd);
        tvdichcauhoidd=findViewById(R.id.tvdichcauhoidd);
        tvghichuQddht=findViewById(R.id.tvghichuQddht);
        textViewTimeddht=findViewById(R.id.textViewTimeddht);
        tvquestionbddht=findViewById(R.id.tvquestionbddht);

        imageAnhddht=findViewById(R.id.imageAnhddht);


        cadviewDA1ddht=findViewById(R.id.cadviewDA1ddht);
        cadviewDA2ddht=findViewById(R.id.cadviewDA2ddht);
        cadviewDA3ddht=findViewById(R.id.cadviewDA3ddht);
        cadviewDA4ddht=findViewById(R.id.cadviewDA4ddht);

    }
    public void Correct1(CardView cardView){
//        StartTimeQuestion();
        amthanh1(R.raw.amthanh_dung);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                correctCount1++;
                index1++;
                question1=question1+1;
                tvquestionbddht.setText(question1+"");
                cauhoiDDHT= cauhoiDDHTList.get(index1);
                setAlldata();
                resetColor1();

            }
        },1500);

    }
    public void Wrong1(CardView cadviewDA1){
//        StartTimeQuestion();
        amthanh1(R.raw.amthanh_sai);
        cadviewDA1.setCardBackgroundColor(getResources().getColor(R.color.red));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wrongCount1++;
                if (index1<cauhoiDDHTList.size()-1){
                    index1++;
                    question1=question1+1;
                    tvquestionbddht.setText(question1+"");
                    cauhoiDDHT= cauhoiDDHTList.get(index1);
                    setAlldata();
                    resetColor1();

                }
                else {
                    GameWonddht();
                }
            }
        }, 1500);
    }

    private void GameWonddht() {

        Intent intent=new Intent(ChCdddhtActivity.this, WonActivity.class);
        intent.putExtra("correctddht",correctCount1);
        intent.putExtra("wrongddht",wrongCount1);
        startActivity(intent);
    }
    public void amthanh1(int at){
        mediaPlayer=MediaPlayer.create(this,at);
        mediaPlayer.start();

    }
    public void  resetColor1(){
        cadviewDA1ddht.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA2ddht.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA3ddht.setCardBackgroundColor(getResources().getColor(R.color.white));
        cadviewDA4ddht.setCardBackgroundColor(getResources().getColor(R.color.white));

    }

    public void ClickDA1ddht(View view) {
        if (cauhoiDDHT.getDapan1DDHT().equals(cauhoiDDHT.getDapanDDHT())){
            cadviewDA1ddht.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index1<cauhoiDDHTList.size()-1){
                Correct1(cadviewDA1ddht);
            }
            else {
                GameWonddht();
            }
        }
        else {
            Wrong1(cadviewDA1ddht);
        }
    }

    public void ClickDA2ddht(View view) {
        if (cauhoiDDHT.getDapan2DDHT().equals(cauhoiDDHT.getDapanDDHT())){
            cadviewDA2ddht.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index1<cauhoiDDHTList.size()-1){
                Correct1(cadviewDA2ddht);
            }
            else {
                GameWonddht();
            }
        }
        else {
            Wrong1(cadviewDA2ddht);
        }
    }

    public void ClickDA3ddht(View view) {
        if (cauhoiDDHT.getDapan3DDHT().equals(cauhoiDDHT.getDapanDDHT())){
            cadviewDA3ddht.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index1<cauhoiDDHTList.size()-1){
                Correct1(cadviewDA3ddht);
            }
            else {
                GameWonddht();
            }
        }
        else {
            Wrong1(cadviewDA3ddht);
        }
    }

    public void ClickDA4ddht(View view) {
        if (cauhoiDDHT.getDapan4DDHT().equals(cauhoiDDHT.getDapanDDHT())){
            cadviewDA4ddht.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            if (index1<cauhoiDDHTList.size()-1){
                Correct1(cadviewDA4ddht);
            }
            else {
                GameWonddht();
            }
        }
        else {
            Wrong1(cadviewDA4ddht);
        }
    }
}