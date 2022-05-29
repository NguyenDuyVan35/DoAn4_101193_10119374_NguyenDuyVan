package com.example.udhta_enl_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.Kiemtra.KiemtraActivity;
import com.example.udhta_enl_app.TaiKhoan.LoginActivity;
import com.example.udhta_enl_app.TaiKhoan.UpdateUserActivity;
import com.example.udhta_enl_app.TuDienTA.TuVungActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewAvatar;
    TextView textViewName,textViewEmail;

    private Animation topAnimation,bottomAnimation,middleAnimation;
    CardView cadviewHT,cadviewKT,cadviewVideo,cadviewTudien;
    LinearLayout layouthoctap,layoutkiemtra,layouttracuu,layoutUser,layoutdoimk,layoutsignout;

    public static Toolbar mtoolbar;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        ShowUserInformatiom();


        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animantion);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animantion);
        middleAnimation= AnimationUtils.loadAnimation(this,R.anim.middle_animantion);

        cadviewHT.setAnimation(topAnimation);
        cadviewKT.setAnimation(topAnimation);
        cadviewVideo.setAnimation(bottomAnimation);
        cadviewTudien.setAnimation(bottomAnimation);

        //chuyển activity
        cadviewHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HoctapActivity.class));
            }
        });
        cadviewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });
        cadviewTudien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TuVungActivity.class));
            }
        });
        cadviewKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KiemtraActivity.class));
            }
        });
        layouthoctap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //set màu cho background layout khi người dùng ấn vào layout
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    layouthoctap.setBackgroundColor(Color.parseColor("#B0777575"));
                }
                //khi người dùng thả tay khỏi layout sẽ set background thanh màu trắng và chuyển tới giao diện học tập
                else {
                    if(event.getAction()==MotionEvent.ACTION_UP){
                        layouthoctap.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        Intent intent=new Intent(MainActivity.this,HoctapActivity.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
        layoutkiemtra.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //set màu cho background layout khi người dùng ấn vào layout
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    layoutkiemtra.setBackgroundColor(Color.parseColor("#B0777575"));
                }
                //khi người dùng thả tay khỏi layout sẽ set background thanh màu trắng và chuyển tới giao diện học tập
                else {
                    if(event.getAction()==MotionEvent.ACTION_UP){
                        layoutkiemtra.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        Intent intent=new Intent(MainActivity.this,KiemtraActivity.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
        layouttracuu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //set màu cho background layout khi người dùng ấn vào layout
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    layouttracuu.setBackgroundColor(Color.parseColor("#B0777575"));
                }
                //khi người dùng thả tay khỏi layout sẽ set background thanh màu trắng và chuyển tới giao diện học tập
                else {
                    if(event.getAction()==MotionEvent.ACTION_UP){
                        layouttracuu.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        Intent intent=new Intent(MainActivity.this,TuVungActivity.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
        layoutsignout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    layoutsignout.setBackgroundColor(Color.parseColor("#B0777575"));
                }else {
                    if(event.getAction()==MotionEvent.ACTION_UP){
                        layoutsignout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
        layoutUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //set màu cho background layout khi người dùng ấn vào layout
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    layoutUser.setBackgroundColor(Color.parseColor("#B0777575"));
                }
                //khi người dùng thả tay khỏi layout sẽ set background thanh màu trắng và chuyển tới giao diện học tập
                else {
                    if(event.getAction()==MotionEvent.ACTION_UP){
                        layoutUser.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        Intent intent=new Intent(MainActivity.this,UpdateUserActivity.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });

    }
    //hàm ShowUserInformatiom dùng để hiện thị thông tin user từ firebase
    private void ShowUserInformatiom(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //nếu user bằng null thì return và không làm gì cả
        if (user== null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        //nếu name bằng null tức bằng rỗng thì ẩn thông tin name và ngược lại
        if (name==null){
            textViewName.setVisibility(View.GONE);
        }else {
            textViewName.setVisibility(View.VISIBLE);
        }

        //gán textViewName bằng name
        textViewName.setText(name);
        textViewEmail.setText(email);
        //load link ảnh từ trên firebase về
        Glide.with(this).load(photoUrl).error(R.drawable.image_avatar).into(imageViewAvatar);
    }


    private void Anhxa() {
        cadviewHT=(CardView) findViewById(R.id.cadviewHT);
        cadviewKT=(CardView) findViewById(R.id.cadviewKT);
        cadviewVideo=(CardView) findViewById(R.id.cadviewVideo);
        cadviewTudien=(CardView) findViewById(R.id.cadviewTudien);
        textViewName=(TextView) findViewById(R.id.textViewName);
        textViewEmail=(TextView) findViewById(R.id.textViewEmail);
        imageViewAvatar=(ImageView) findViewById(R.id.imageViewAvatar);
        layouthoctap=(LinearLayout) findViewById(R.id.layouthoctap);
        layoutsignout=(LinearLayout) findViewById(R.id.layoutsignout);
        layouttracuu=findViewById(R.id.layouttracuu);
        layoutkiemtra=findViewById(R.id.layoutkiemtra);
        layoutUser=findViewById(R.id.layoutUser);
    }
}