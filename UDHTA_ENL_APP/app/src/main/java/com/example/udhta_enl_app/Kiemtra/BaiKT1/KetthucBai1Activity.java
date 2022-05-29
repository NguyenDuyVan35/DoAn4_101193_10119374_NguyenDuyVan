package com.example.udhta_enl_app.Kiemtra.BaiKT1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh.CauhoiCDGDActivity;
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

public class KetthucBai1Activity extends AppCompatActivity {

    CircularProgressBar circularProgressBarKTB1;
    TextView resultTextbai1,diembai1;
    Button btLamLaibai1,btLuudiembai1;

    int correctb1,wrongb1,Tongdiem;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketthuc_bai1);

        correctb1=getIntent().getIntExtra("correctb1",0);
        wrongb1=getIntent().getIntExtra("wrongb1",0);
        Tongdiem=getIntent().getIntExtra("diem",0);

        circularProgressBarKTB1=findViewById(R.id.circularProgressBarKTB1);
        resultTextbai1=findViewById(R.id.resultTextbai1);
        diembai1=findViewById(R.id.diembai1);
        btLamLaibai1=findViewById(R.id.btLamLaibai1);
        btLuudiembai1=findViewById(R.id.btLuudiembai1);

        circularProgressBarKTB1.setProgress(correctb1);
        resultTextbai1.setText(correctb1 + "/30");
        diembai1.setText(Tongdiem+"");

        btLuudiembai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogSave("Lưu diểm");
                databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String strsocaudung = snapshot.child("socaudung").getValue().toString();
                            String strdiem = snapshot.child("diem").getValue().toString();

                            int socaudung = Integer.parseInt(strsocaudung) + correctb1;
                            int diem = Integer.parseInt(strdiem) + Tongdiem;

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
                Intent ketthuc = new Intent(KetthucBai1Activity.this, KiemtraActivity.class);
                startActivity(ketthuc);
            }
        });
        btLamLaibai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KetthucBai1Activity.this,Bai1Activity.class));
                finish();
            }
        });

    }
//    private void DialogSave(String xau) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(xau);
//        builder.setCancelable(false);
//
//        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if (snapshot.exists()) {
//                            String strsocaudung = snapshot.child("socaudung").getValue().toString();
//                            String strdiem = snapshot.child("diem").getValue().toString();
//
//                            int socaudung = Integer.parseInt(strsocaudung) + correctb1;
//                            int diem = Integer.parseInt(strdiem) + Tongdiem;
//
//                            HashMap hashMap=new HashMap();
//                            hashMap.put("socaudung", String.valueOf(socaudung));
//                            hashMap.put("diem", String.valueOf(diem));
//                            databaseReference.child(firebaseUser.getUid()).updateChildren(hashMap, new DatabaseReference.CompletionListener() {
//                                @Override
//                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                                }
//                            });
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//                Intent ketthuc = new Intent(KetthucBai1Activity.this, KiemtraActivity.class);
//                startActivity(ketthuc);
//            }
//        });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}
