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

        //x??c ?????nh linearLayout m?? RecyclerView m?? n?? s??? d???ng
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //setLayoutManager cho Recyclerview


        //d??ng ????? ph??n c??ch gi???a c??c item c???a recyclerview
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewTuvung.addItemDecoration(dividerItemDecoration);
        recyclerViewTuvung.setLayoutManager(linearLayoutManager);

        //kh???i t???o ArrayList
        tuVungArrayList=new ArrayList<>();
        //kh???i t???o TuVungAdapter
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

            //Khi m?? ta get d??? li???u t??? th???ng DatabaseReference myRef = database.getReference("TvCDGiadinh");
            // n?? s??? tr??? v??? cho ta m???t th???ng DataSnapshot, tuy nhi??n th???ng DataSnapshot n?? tr??? v???  onDataChange(@NonNull DataSnapshot snapshot) l??
            // t???ng c???a c??i TvCDGiadinh m?? trong ph???m TvCDGiadinh trong firebase c?? ch???a c??c c??i c??i item m?? khi ta getChildren()
            // s??? l???y l???n l?????t t???ng c??i item v?? ch??ng ta d??ng for (DataSnapshot dataSnapshot : snapshot.getChildren())
            // ????? l???y l???n l?????t t???ng item m???t trong TvCDGiadinh
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tuVungArrayList != null){
                    tuVungArrayList.clear();
                }
                //Khi m?? n?? ch???y xong v??ng for th?? ch??ng ta s??? add c??c item trong TvCDGiadinh v??o trong list
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TuVung tuVung=dataSnapshot.getValue(TuVung.class);
                    tuVungArrayList.add(tuVung);

                }
                //Sau khi m?? ta thay ?????i c??i list d??? li???u m?? ch??ng ta set tren adapter r???i th?? ta s??? g???i m???t c??u l???nh
                // ????? ch??ng ta refresh c??i data thay ?????i
                tuVungAdapter.notifyDataSetChanged();
                recyclerViewTuvung.setAdapter(tuVungAdapter);
            }

            @Override
            //onCancelled d??ng ????? khi su???t hi???n m???t l???i g?? ?????y s??? th??ng b??o cho ng?????i d??ng
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