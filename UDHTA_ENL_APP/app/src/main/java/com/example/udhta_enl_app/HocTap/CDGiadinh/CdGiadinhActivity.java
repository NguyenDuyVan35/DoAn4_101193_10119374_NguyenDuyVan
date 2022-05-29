package com.example.udhta_enl_app.HocTap.CDGiadinh;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh.CauhoiCDGDActivity;
import com.example.udhta_enl_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CdGiadinhActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    RecyclerView recyclerViewCDGD;
    TvCDGiadinhAdapter tvCDGiadinhAdapter;
    List<TvCDGiadinh> mtvCDGiadinhList;
    TvCDGiadinh tvCDGiadinh;
    CardView cadviewNext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd_giadinh);

        initUi();
        getListTVgdFromRealtimeDatabase();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerViewCDGD.setLayoutManager(gridLayoutManager);

        cadviewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CdGiadinhActivity.this, CauhoiCDGDActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initUi() {
        recyclerViewCDGD=findViewById(R.id.recyclerViewCDGD);
        cadviewNext=findViewById(R.id.cadviewNext);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerViewCDGD.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewCDGD.addItemDecoration(dividerItemDecoration);

        mtvCDGiadinhList=new ArrayList<>();
        tvCDGiadinhAdapter=new TvCDGiadinhAdapter(mtvCDGiadinhList, this, new TvCDGiadinhAdapter.IClickListener() {
            @Override
            public void onClickItem(TvCDGiadinh tvCDGiadinh) {
                textToSpeech=new TextToSpeech(CdGiadinhActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if (i != TextToSpeech.ERROR) {
                            // textToSpeech.setLanguage(Locale.US);
                            textToSpeech.setLanguage(new Locale("en_US"));
                        } else {
                            Toast.makeText(CdGiadinhActivity.this, "Errol", Toast.LENGTH_LONG).show();
                        }
                        if (tvCDGiadinh != null) {
                            textToSpeech.speak(tvCDGiadinh.getTuVung(), TextToSpeech.QUEUE_FLUSH, null);
                        }

                    }
                });
            }
        });

        recyclerViewCDGD.setAdapter(tvCDGiadinhAdapter);


    }

    private void getListTVgdFromRealtimeDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TvCDGiadinh");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(mtvCDGiadinhList != null){
                    mtvCDGiadinhList.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TvCDGiadinh tvCDGiadinh=dataSnapshot.getValue(TvCDGiadinh.class);
                    mtvCDGiadinhList.add(tvCDGiadinh);

                }
                tvCDGiadinhAdapter.notifyDataSetChanged();
                Log.e("data",mtvCDGiadinhList.size()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CdGiadinhActivity.this,"Get list TvCDGiadinh faild",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
