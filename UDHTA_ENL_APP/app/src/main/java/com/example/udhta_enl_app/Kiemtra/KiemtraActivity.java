package com.example.udhta_enl_app.Kiemtra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.udhta_enl_app.Kiemtra.BaiKT1.Bai1Activity;
import com.example.udhta_enl_app.Kiemtra.BaiKT2.Bai2Activity;
import com.example.udhta_enl_app.Kiemtra.BaiKT3.Bai3Activity;
import com.example.udhta_enl_app.R;

public class KiemtraActivity extends AppCompatActivity {

    CardView cadviewBaiKT1,cadviewBaiKT2,cadviewBaiKT3,cadviewDSdiem;
    TextView tvdiembaikt1;

    int diem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiemtra);

        Anhxa();
        cadviewBaiKT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KiemtraActivity.this, Bai1Activity.class);
                startActivity(intent);
            }
        });
        cadviewBaiKT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KiemtraActivity.this, Bai2Activity.class);
                startActivity(intent);
            }
        });
        cadviewBaiKT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KiemtraActivity.this, Bai3Activity.class);
                startActivity(intent);
            }
        });

        cadviewDSdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KiemtraActivity.this, DSDiemKTActivity.class);
                startActivity(intent);
            }
        });




    }

    private void Anhxa() {
        cadviewBaiKT1=findViewById(R.id.cadviewBaiKT1);
        cadviewBaiKT2=findViewById(R.id.cadviewBaiKT2);
        cadviewBaiKT3=findViewById(R.id.cadviewBaiKT3);
        cadviewDSdiem=findViewById(R.id.cadviewDSdiem);

    }
}