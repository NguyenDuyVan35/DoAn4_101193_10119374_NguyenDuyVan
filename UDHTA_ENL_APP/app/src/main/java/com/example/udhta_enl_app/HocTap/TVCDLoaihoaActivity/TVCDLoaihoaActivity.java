package com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity;

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

import com.example.udhta_enl_app.HocTap.CDGiadinh.CdGiadinhActivity;
import com.example.udhta_enl_app.HocTap.CDGiadinh.TvCDGiadinh;
import com.example.udhta_enl_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TVCDLoaihoaActivity extends AppCompatActivity {

    TextToSpeech textToSpeechtvlh;
    RecyclerView recyclerViewCDTVLH;
    CardView cadviewNextCHLH;

    List<TVCDLoaihoa> tvcdLoaihoaList;
    TVCDLoaihoaAdapter tvcdLoaihoaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvcdloaihoa);

        initUI();
        getListTVlhFromRealtimeDatabase();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerViewCDTVLH.setLayoutManager(gridLayoutManager);
    }



    private void initUI() {
        recyclerViewCDTVLH=findViewById(R.id.recyclerViewCDTVLH);
        cadviewNextCHLH=findViewById(R.id.cadviewNextCHLH);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerViewCDTVLH.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewCDTVLH.addItemDecoration(dividerItemDecoration);

        tvcdLoaihoaList=new ArrayList<>();
        tvcdLoaihoaAdapter=new TVCDLoaihoaAdapter(tvcdLoaihoaList, this, new TVCDLoaihoaAdapter.IClickListenerCDLH() {
            @Override
            public void onClickItem(TVCDLoaihoa tvcdLoaihoa) {
                textToSpeechtvlh=new TextToSpeech(TVCDLoaihoaActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status !=TextToSpeech.ERROR){
                            textToSpeechtvlh.setLanguage(new Locale("en_US"));
                        }else {
                            Toast.makeText(TVCDLoaihoaActivity.this, "Errol", Toast.LENGTH_SHORT).show();
                        }
                        if (tvcdLoaihoa!=null){
                            textToSpeechtvlh.speak(tvcdLoaihoa.getTuvungCDLH(),TextToSpeech.QUEUE_FLUSH,null);

                        }
                    }
                });
            }
        });

    }
    private void getListTVlhFromRealtimeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TVCDLoaihoa");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tvcdLoaihoaList != null){
                    tvcdLoaihoaList.clear();
                }
                //Khi mà nó chạy xong vòng for thì chúng ta sẽ add các item trong TvCDGiadinh vào trong list
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TVCDLoaihoa tvcdLoaihoa=dataSnapshot.getValue(TVCDLoaihoa.class);
                    tvcdLoaihoaList.add(tvcdLoaihoa);

                }
                tvcdLoaihoaAdapter.notifyDataSetChanged();
                recyclerViewCDTVLH.setAdapter(tvcdLoaihoaAdapter);
            }

            @Override

            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TVCDLoaihoaActivity.this,"Get list TvCDLoaihoa faild",Toast.LENGTH_SHORT).show();
            }
        });
    }
}