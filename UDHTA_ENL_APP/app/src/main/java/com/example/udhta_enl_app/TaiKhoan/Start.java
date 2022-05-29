package com.example.udhta_enl_app.TaiKhoan;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.udhta_enl_app.MainActivity;
import com.example.udhta_enl_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Start extends AppCompatActivity {

    private Animation topAnimation,bottomAnimation,middleAnimation;
    View line_1,line_2,line_3,line_4,line_5,line_6,line_7;
    ImageView imvEN;
    TextView tvAdrApp;


    private static int SPLASH_TIME_OUT=3000;//sét thời gian hiển thị là 3s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        topAnimation= AnimationUtils.loadAnimation(this, R.anim.top_animantion);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animantion);
        middleAnimation= AnimationUtils.loadAnimation(this,R.anim.middle_animantion);

        //Ánh xạ
        line_1=(View) findViewById(R.id.line_1);
        line_2=(View) findViewById(R.id.line_2);
        line_3=(View) findViewById(R.id.line_3);
        line_4=(View) findViewById(R.id.line_4);
        line_5=(View) findViewById(R.id.line_5);
        line_6=(View) findViewById(R.id.line_6);
        line_7=(View) findViewById(R.id.line_7);

        imvEN=(ImageView) findViewById(R.id.imv_EN);
        tvAdrApp=(TextView) findViewById(R.id.tvAdrApp);


        //set hiệu ứng animation
        line_1.setAnimation(topAnimation);
        line_2.setAnimation(topAnimation);
        line_3.setAnimation(topAnimation);
        line_4.setAnimation(topAnimation);
        line_5.setAnimation(topAnimation);
        line_6.setAnimation(topAnimation);
        line_7.setAnimation(topAnimation);


        imvEN.setAnimation(middleAnimation);
        tvAdrApp.setAnimation(bottomAnimation);

        //chuyển màn hình
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },SPLASH_TIME_OUT);//thời gian hiển thị màn hình start

    }

    private void nextActivity() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        //check nếu user bằng null thì tức là chưa login và ngược lại
        //nếu user bằng null thì sẽ chuyển đến giao diện login
        if (user == null){
            Intent intent=new Intent(Start.this, LoginActivity.class);
            startActivity(intent);
        }
        //ngược lại user khác null tức đã dăng nhập trước đây thì sẽ chuyển trực tiếp vào giao diện main
        else {
            Intent intent=new Intent(Start.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}