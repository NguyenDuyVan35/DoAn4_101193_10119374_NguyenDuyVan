package com.example.udhta_enl_app.TuDienTA;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udhta_enl_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class TuVungActivity extends AppCompatActivity {

    TextToSpeech textToSpeechtv;
    RecyclerView recyclerViewTuvung;
    ArrayList<TuVung> tuVungArrayList;
    TuVungAdapter tuVungAdapter;
    CardView cardViewTranslate;
    private SearchView searchView;
    Toolbar toolbarSearch;
    EditText searchTV;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_vung);

        initUi();
        getListTVFromRealtimeDatabase();


    }

    private void initUi() {
        recyclerViewTuvung=findViewById(R.id.recyclerViewTuVung);
        cardViewTranslate=findViewById(R.id.cardViewTranslate);

        cardViewTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TuVungActivity.this, Language_Translate_Activity.class);
                startActivity(intent);
            }
        });

        //xác định linearLayout mà RecyclerView mà nó sử dụng
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //setLayoutManager cho Recyclerview


        //dùng để phân cách giữa các item của recyclerview
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewTuvung.addItemDecoration(dividerItemDecoration);
        recyclerViewTuvung.setLayoutManager(linearLayoutManager);

        //khởi tạo ArrayList
        tuVungArrayList=new ArrayList<>();
        //khỏi tạo TuVungAdapter
        tuVungAdapter=new TuVungAdapter(tuVungArrayList, this, new TuVungAdapter.IClickListenernTV() {
            @Override
            public void OnClickItemTV(TuVung tuVung) {

                textToSpeechtv=new TextToSpeech(TuVungActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {

                        if (i != TextToSpeech.ERROR) {
                            // textToSpeech.setLanguage(Locale.US);
                            textToSpeechtv.setLanguage(new Locale("en_US"));
                        } else {
                            Toast.makeText(TuVungActivity.this, "Errol", Toast.LENGTH_LONG).show();
                        }
                        if (tuVung != null) {
                            textToSpeechtv.speak(tuVung.getTuVungTA(), TextToSpeech.QUEUE_FLUSH, null);
                        }

                    }
                });
            }
        });


    }

    private void getListTVFromRealtimeDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TuVung");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            //Khi mà ta get dữ liệu từ thằng DatabaseReference myRef = database.getReference("TvCDGiadinh");
            // nó sẽ trả về cho ta một thằng DataSnapshot, tuy nhiên thằng DataSnapshot nó trả về  onDataChange(@NonNull DataSnapshot snapshot) là
            // tổng của cái TvCDGiadinh mà trong phầm TvCDGiadinh trong firebase có chứa các cái cái item mà khi ta getChildren()
            // sẽ lấy lần lượt từng cái item và chúng ta dùng for (DataSnapshot dataSnapshot : snapshot.getChildren())
            // để lấy lần lượt từng item một trong TvCDGiadinh
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tuVungArrayList != null){
                    tuVungArrayList.clear();
                }
                //Khi mà nó chạy xong vòng for thì chúng ta sẽ add các item trong TvCDGiadinh vào trong list
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TuVung tuVung=dataSnapshot.getValue(TuVung.class);
                    tuVungArrayList.add(tuVung);

                }
                //Sau khi mà ta thay đổi cái list dữ liệu mà chúng ta set tren adapter rồi thì ta sẽ gọi một câu lệnh
                // để chúng ta refresh cái data thay đổi
                tuVungAdapter.notifyDataSetChanged();
                recyclerViewTuvung.setAdapter(tuVungAdapter);
            }

            @Override
            //onCancelled dùng để khi suất hiện một lỗi gì đấy sẽ thông báo cho người dùng
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TuVungActivity.this,"Get list TvCDGiadinh faild",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_search,menu);

        SearchManager searchManager=(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView=(SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tuVungAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tuVungAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}