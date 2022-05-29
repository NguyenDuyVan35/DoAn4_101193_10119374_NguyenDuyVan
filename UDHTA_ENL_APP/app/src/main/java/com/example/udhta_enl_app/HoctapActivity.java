package com.example.udhta_enl_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TvCDDodunghoctapActivity;
import com.example.udhta_enl_app.HocTap.CDDongvat.CdDongVatActivity;
import com.example.udhta_enl_app.HocTap.CDGiadinh.CdGiadinhActivity;
import com.example.udhta_enl_app.HocTap.CDMonhoc.TvCDMonhocActivity;
import com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity.TVCDLoaihoaActivity;
import com.example.udhta_enl_app.HocTap.TvCDRauCu.TvCDRauCuActivity;


public class HoctapActivity extends AppCompatActivity {
    CardView cadviewCDgiadinh,cadviewCDdongvat,cadviewCDhoa,cadviewCDraucu,cadviewCDdodungHT,cadviewCDmonhoc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoctap);

        Anhxa();

        cadviewCDgiadinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoctapActivity.this, CdGiadinhActivity.class));
            }
        });
        cadviewCDdongvat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoctapActivity.this,CdDongVatActivity.class));
            }
        });
        cadviewCDhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoctapActivity.this, TVCDLoaihoaActivity.class));
            }
        });
        cadviewCDraucu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoctapActivity.this, TvCDRauCuActivity.class));
            }
        });
        cadviewCDdodungHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoctapActivity.this, TvCDDodunghoctapActivity.class));
            }
        });
        cadviewCDmonhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoctapActivity.this, TvCDMonhocActivity.class));
            }
        });
    }

    private void Anhxa() {
        cadviewCDgiadinh=(CardView) findViewById(R.id.cadviewCDgiadinh);
        cadviewCDdongvat=(CardView) findViewById(R.id.cadviewCDdongvat);
        cadviewCDhoa=(CardView) findViewById(R.id.cadviewCDhoa);
        cadviewCDraucu=(CardView) findViewById(R.id.cadviewCDraucu);
        cadviewCDdodungHT=(CardView) findViewById(R.id.cadviewCDdodungHT);
        cadviewCDmonhoc=(CardView) findViewById(R.id.cadviewCDmonhoc);
    }
}
