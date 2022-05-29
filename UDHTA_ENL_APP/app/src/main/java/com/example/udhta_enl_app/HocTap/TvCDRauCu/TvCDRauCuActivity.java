package com.example.udhta_enl_app.HocTap.TvCDRauCu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity.TVCDLoaihoa;
import com.example.udhta_enl_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TvCDRauCuActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    RecyclerView recyclerViewCDTVRC;
    CardView cadviewNextCHRC;
    List<TvCDRauCu> tvCDRauCuList;
    TvRaucuAdapter tvRaucuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_cdrau_cu);

        initUi();
        getListTVrcFromRealtimeDatabase();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerViewCDTVRC.setLayoutManager(gridLayoutManager);
    }

    private void initUi() {
        recyclerViewCDTVRC=findViewById(R.id.recyclerViewCDTVRC);
        cadviewNextCHRC=findViewById(R.id.cadviewNextCHRC);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerViewCDTVRC.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewCDTVRC.addItemDecoration(dividerItemDecoration);

        tvCDRauCuList=new ArrayList<>();

        tvRaucuAdapter=new TvRaucuAdapter(this,tvCDRauCuList, new TvRaucuAdapter.IClickListenerCDRC() {
            @Override
            public void onClickItem(TvCDRauCu tvCDRauCu) {
                textToSpeech=new TextToSpeech(TvCDRauCuActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status!=TextToSpeech.ERROR){
                            textToSpeech.setLanguage(new Locale("en_US"));

                        }else {
                            Toast.makeText(TvCDRauCuActivity.this, "Errol", Toast.LENGTH_SHORT).show();
                        }
                        if (tvCDRauCu!=null){
                            textToSpeech.speak(tvCDRauCu.getTvraucu(),TextToSpeech.QUEUE_FLUSH,null);

                        }                    }
                });
            }
        });

    }

    private void getListTVrcFromRealtimeDatabase(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("TvCDRauCu");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (tvCDRauCuList!=null){
                    tvCDRauCuList.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TvCDRauCu tvCDRauCu=dataSnapshot.getValue(TvCDRauCu.class);
                    tvCDRauCuList.add(tvCDRauCu);

                }
                tvRaucuAdapter.notifyDataSetChanged();
                recyclerViewCDTVRC.setAdapter(tvRaucuAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TvCDRauCuActivity.this, "Get list TvCDRaucu faild", Toast.LENGTH_SHORT).show();
            }
        });
    }
}