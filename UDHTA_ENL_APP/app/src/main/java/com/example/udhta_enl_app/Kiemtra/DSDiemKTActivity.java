package com.example.udhta_enl_app.Kiemtra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TVDDHTAdapter;
import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TvCDDodunghoctap;
import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TvCDDodunghoctapActivity;
import com.example.udhta_enl_app.R;
import com.example.udhta_enl_app.TaiKhoan.TaiKhoan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DSDiemKTActivity extends AppCompatActivity {
    RecyclerView rvDSDiemKT;

    List<TaiKhoan> taiKhoanList;
    DSDiemKTAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsdiem_ktactivity);
        
        initUI();
        getListDiemktFromRealtimeDatabase();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1);
        rvDSDiemKT.setLayoutManager(gridLayoutManager);
    }
    private void initUI() {
        rvDSDiemKT=findViewById(R.id.rvDSDiemKT);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvDSDiemKT.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvDSDiemKT.addItemDecoration(dividerItemDecoration);

        taiKhoanList=new ArrayList<>();
        adapter=new DSDiemKTAdapter(taiKhoanList);
        rvDSDiemKT.setAdapter(adapter);
    }
    private void getListDiemktFromRealtimeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("User");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                TaiKhoan taiKhoan = snapshot.getValue(TaiKhoan.class);
                if (taiKhoan != null){
                    taiKhoanList.add(0,taiKhoan);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}