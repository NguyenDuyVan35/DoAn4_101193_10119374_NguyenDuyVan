package com.example.udhta_enl_app.HocTap.CDDongvat;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udhta_enl_app.HocTap.CDGiadinh.CdGiadinhActivity;
import com.example.udhta_enl_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CdDongVatActivity extends AppCompatActivity {

    TextToSpeech toSpeechtvDV;
    RecyclerView recyclerViewCDDV;
    CardView cadviewNextDV;
    TvCDDongVatAdapter dongVatAdapter;
    List<TvCDDongVat> dongVats;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd_dongvat);

        initUI();
        getLisTVdvFromRealtimeDatabase();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerViewCDDV.setLayoutManager(gridLayoutManager);
    }

    private void initUI() {
        recyclerViewCDDV=findViewById(R.id.recyclerViewCDDV);
        cadviewNextDV=findViewById(R.id.cadviewNextDV);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerViewCDDV.setLayoutManager(linearLayoutManager);

        //khoảng cách giữa các item
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewCDDV.addItemDecoration(dividerItemDecoration);

        dongVats=new ArrayList<>();
        dongVatAdapter=new TvCDDongVatAdapter(dongVats, this, new TvCDDongVatAdapter.IClickItemTVDV() {
            @Override
            public void onClickItemTVDV(TvCDDongVat tvCDDongVat) {
                toSpeechtvDV=new TextToSpeech(CdDongVatActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR){
                            toSpeechtvDV.setLanguage(new Locale("en_US"));
                        }else {
                            Toast.makeText(CdDongVatActivity.this,"Errol",Toast.LENGTH_SHORT).show();
                        }
                        if (tvCDDongVat != null){
                            toSpeechtvDV.speak(tvCDDongVat.getTuVungDV(),TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }
                });
            }
        });

        recyclerViewCDDV.setAdapter(dongVatAdapter);


    }

    private void getLisTVdvFromRealtimeDatabase(){
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("TvCDDongVat");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (dongVats != null){
                    dongVats.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TvCDDongVat tvCDDongVat=dataSnapshot.getValue(TvCDDongVat.class);
                    dongVats.add(tvCDDongVat);
                }
                dongVatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CdDongVatActivity.this,"Get list TvCDGiadinh faild",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
