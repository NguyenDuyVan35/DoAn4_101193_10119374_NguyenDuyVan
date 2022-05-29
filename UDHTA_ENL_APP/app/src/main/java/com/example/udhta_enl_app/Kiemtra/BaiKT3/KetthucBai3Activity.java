package com.example.udhta_enl_app.Kiemtra.BaiKT3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.udhta_enl_app.Kiemtra.BaiKT2.Bai2Activity;
import com.example.udhta_enl_app.Kiemtra.BaiKT2.KetthucBai2Activity;
import com.example.udhta_enl_app.Kiemtra.DiemKT;
import com.example.udhta_enl_app.Kiemtra.KiemtraActivity;
import com.example.udhta_enl_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.HashMap;

public class KetthucBai3Activity extends AppCompatActivity {

    CircularProgressBar circularProgressBarKTB3;
    TextView resultTextbai3,diembai3,tenBaikt;
    Button btLamLaibai3,btLuudiembai3;
    int correctB3,wrongB3,diemB3,baikt=3;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketthuc_bai3);

        Anhxa();

        correctB3=getIntent().getIntExtra("correctB3",0);
        wrongB3=getIntent().getIntExtra("wrongB3",0);
        diemB3=getIntent().getIntExtra("diem3",0);


        circularProgressBarKTB3.setProgress(correctB3);
        resultTextbai3.setText(correctB3 + "/10");
        diembai3.setText(diemB3+"");
        tenBaikt.setText(baikt+"");

        btLuudiembai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogSave("Lưu diểm");
                databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
//                            String strsocaudung = snapshot.child("socaudung").getValue().toString();
//                            String strdiem = snapshot.child("diem").getValue().toString();

//                            int socaudung = Integer.parseInt(strsocaudung) + correctB3;
//                            int diem = Integer.parseInt(strdiem) + diemB3;
                            String TenBaikt=snapshot.child("Baikt").toString();


                            HashMap hashMap=new HashMap();
                            hashMap.put("socaudung", String.valueOf(correctB3));
                            hashMap.put("diem", String.valueOf(diemB3));
                            hashMap.put("Baikt",String.valueOf(baikt));
                            databaseReference.child(firebaseUser.getUid()).updateChildren(hashMap, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Toast.makeText(KetthucBai3Activity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent ketthuc = new Intent(KetthucBai3Activity.this, KiemtraActivity.class);
                startActivity(ketthuc);
            }
        });
        btLamLaibai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KetthucBai3Activity.this, Bai3Activity.class));
                finish();
            }
        });
    }

    private void Anhxa() {
        circularProgressBarKTB3=findViewById(R.id.circularProgressBarKTB3);
        resultTextbai3=findViewById(R.id.resultTextbai3);
        diembai3=findViewById(R.id.diembai3);
        btLamLaibai3=findViewById(R.id.btLamLaibai3);
        btLuudiembai3=findViewById(R.id.btLuudiembai3);
        tenBaikt=findViewById(R.id.tenBaikt);
    }
}