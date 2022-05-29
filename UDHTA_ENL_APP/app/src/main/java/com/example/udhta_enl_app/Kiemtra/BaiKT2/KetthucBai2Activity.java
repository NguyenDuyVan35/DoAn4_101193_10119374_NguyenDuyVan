package com.example.udhta_enl_app.Kiemtra.BaiKT2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.udhta_enl_app.Kiemtra.BaiKT1.Bai1Activity;
import com.example.udhta_enl_app.Kiemtra.BaiKT1.KetthucBai1Activity;
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

public class KetthucBai2Activity extends AppCompatActivity {

    CircularProgressBar circularProgressBarKTB2;
    TextView resultTextbai2,diembai2;
    Button btLamLaibai2,btLuudiembai2;
    DiemKT diemKT;
    int correctB2,wrongB2,diemB2;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketthuc_bai2);
        Anhxa();

        correctB2=getIntent().getIntExtra("correctB2",0);
        wrongB2=getIntent().getIntExtra("wrongB2",0);
        diemB2=getIntent().getIntExtra("diem",0);


        circularProgressBarKTB2.setProgress(correctB2);
        resultTextbai2.setText(correctB2 + "/10");
        diembai2.setText(diembai2+"");

        btLuudiembai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogSave("Lưu diểm");
                databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String strsocaudung = snapshot.child("socaudung").getValue().toString();
                            String strdiem = snapshot.child("diem").getValue().toString();

                            int socaudung = Integer.parseInt(strsocaudung) + correctB2;
                            int diem = Integer.parseInt(strdiem) + diemB2;

                            HashMap hashMap=new HashMap();
                            hashMap.put("socaudung", String.valueOf(socaudung));
                            hashMap.put("diem", String.valueOf(diem));
                            databaseReference.child(firebaseUser.getUid()).updateChildren(hashMap, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent ketthuc = new Intent(KetthucBai2Activity.this, KiemtraActivity.class);
                startActivity(ketthuc);
            }
        });
        btLamLaibai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KetthucBai2Activity.this, Bai2Activity.class));
                finish();
            }
        });

    }

    private void Anhxa() {
        circularProgressBarKTB2=findViewById(R.id.circularProgressBarKTB2);
        resultTextbai2=findViewById(R.id.resultTextbai2);
        diembai2=findViewById(R.id.diembai2);
        btLamLaibai2=findViewById(R.id.btLamLaibai2);
        btLuudiembai2=findViewById(R.id.btLuudiembai2);

    }
}