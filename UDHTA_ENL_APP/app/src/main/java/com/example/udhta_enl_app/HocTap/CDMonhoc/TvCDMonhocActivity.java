package com.example.udhta_enl_app.HocTap.CDMonhoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TVDDHTAdapter;
import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TestCDddht.ChCdddhtActivity;
import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TvCDDodunghoctap;
import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TvCDDodunghoctapActivity;
import com.example.udhta_enl_app.HocTap.CDMonhoc.TestCDMonHoc.CauhoiCDMHActivity;
import com.example.udhta_enl_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TvCDMonhocActivity extends AppCompatActivity {

    TextToSpeech textToSpeechtvMH;
    RecyclerView recyclerViewCDMH;
    CardView cadviewNextCDMH;
    List<TvCDMonhoc>tvCDMonhocList;
    TvCDMonhocAdapter tvCDMonhocAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_cdmonhoc);

        initUI();
        getListTVMHFromRealtimeDatabase();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerViewCDMH.setLayoutManager(gridLayoutManager);
    }

    private void initUI() {
        recyclerViewCDMH=findViewById(R.id.recyclerViewCDMH);
        cadviewNextCDMH=findViewById(R.id.cadviewNextCDMH);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerViewCDMH.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewCDMH.addItemDecoration(dividerItemDecoration);

        tvCDMonhocList=new ArrayList<>();
        tvCDMonhocAdapter=new TvCDMonhocAdapter(tvCDMonhocList, this, new TvCDMonhocAdapter.IClickListenerTVCDMH() {
            @Override
            public void onClickItem(TvCDMonhoc tvCDMonhoc) {
                textToSpeechtvMH=new TextToSpeech(TvCDMonhocActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status !=TextToSpeech.ERROR){
                            textToSpeechtvMH.setLanguage(new Locale("en_US"));
                        }else {
                            Toast.makeText(TvCDMonhocActivity.this, "Errol", Toast.LENGTH_SHORT).show();
                        }
                        if (tvCDMonhoc!=null){
                            textToSpeechtvMH.speak(tvCDMonhoc.getTvMonhoc(),TextToSpeech.QUEUE_FLUSH,null);

                        }
                    }
                });
            }
        });
        cadviewNextCDMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TvCDMonhocActivity.this, CauhoiCDMHActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void getListTVMHFromRealtimeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TvCDMonhoc");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tvCDMonhocList != null){
                    tvCDMonhocList.clear();
                }

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TvCDMonhoc tvCDMonhoc=dataSnapshot.getValue(TvCDMonhoc.class);
                    tvCDMonhocList.add(tvCDMonhoc);

                }
                tvCDMonhocAdapter.notifyDataSetChanged();
                recyclerViewCDMH.setAdapter(tvCDMonhocAdapter);
            }

            @Override

            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TvCDMonhocActivity.this,"Get list TvCDLoaihoa faild",Toast.LENGTH_SHORT).show();
            }
        });
    }
}