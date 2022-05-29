package com.example.udhta_enl_app.HocTap.CDDodunghoctap;

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

import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TestCDddht.ChCdddhtActivity;
import com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity.TVCDLoaihoa;
import com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity.TVCDLoaihoaActivity;
import com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity.TVCDLoaihoaAdapter;
import com.example.udhta_enl_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TvCDDodunghoctapActivity extends AppCompatActivity {

    TextToSpeech textToSpeechtvddht;
    RecyclerView recyclerViewCDDDHT;
    CardView cadviewNextDDHT;

    List<TvCDDodunghoctap> tvCDDodunghoctapList;
    TVDDHTAdapter tvddhtAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_cddodunghoctap);

        initUI();
        getListTVddhtFromRealtimeDatabase();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerViewCDDDHT.setLayoutManager(gridLayoutManager);

        cadviewNextDDHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TvCDDodunghoctapActivity.this, ChCdddhtActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void initUI() {
        recyclerViewCDDDHT=findViewById(R.id.recyclerViewCDDDHT);
        cadviewNextDDHT=findViewById(R.id.cadviewNextDDHT);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerViewCDDDHT.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewCDDDHT.addItemDecoration(dividerItemDecoration);

        tvCDDodunghoctapList=new ArrayList<>();
        tvddhtAdapter=new TVDDHTAdapter(this,tvCDDodunghoctapList, new TVDDHTAdapter.IClickListenerCDDDHT() {
            @Override
            public void onClickItem(TvCDDodunghoctap tvCDDodunghoctap) {
                textToSpeechtvddht=new TextToSpeech(TvCDDodunghoctapActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status !=TextToSpeech.ERROR){
                            textToSpeechtvddht.setLanguage(new Locale("en_US"));
                        }else {
                            Toast.makeText(TvCDDodunghoctapActivity.this, "Errol", Toast.LENGTH_SHORT).show();
                        }
                        if (tvCDDodunghoctap!=null){
                            textToSpeechtvddht.speak(tvCDDodunghoctap.getTvDDHT(),TextToSpeech.QUEUE_FLUSH,null);

                        }
                    }
                });
            }
        });

    }
    private void getListTVddhtFromRealtimeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TvCDDodunghoctap");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tvCDDodunghoctapList != null){
                    tvCDDodunghoctapList.clear();
                }
                //Khi mà nó chạy xong vòng for thì chúng ta sẽ add các item trong TvCDGiadinh vào trong list
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TvCDDodunghoctap tvCDDodunghoctap=dataSnapshot.getValue(TvCDDodunghoctap.class);
                    tvCDDodunghoctapList.add(tvCDDodunghoctap);

                }
                tvddhtAdapter.notifyDataSetChanged();
                recyclerViewCDDDHT.setAdapter(tvddhtAdapter);
            }

            @Override

            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TvCDDodunghoctapActivity.this,"Get list TvCDLoaihoa faild",Toast.LENGTH_SHORT).show();
            }
        });
    }
}