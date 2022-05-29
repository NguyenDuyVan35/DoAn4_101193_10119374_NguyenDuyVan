package com.example.udhta_enl_app;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udhta_enl_app.Video.Video;
import com.example.udhta_enl_app.Video.VideoAdapter;

import java.util.ArrayList;


public class VideoActivity extends AppCompatActivity {

    RecyclerView recyclerViewVideo;
    ArrayList<Video> videoArrayList;
    VideoAdapter videoAdapter;

    public static Video video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerViewVideo=findViewById(R.id.recyclerViewVideo);
        recyclerViewVideo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewVideo.setHasFixedSize(true);

        videoArrayList=new ArrayList<Video>();

        video=new Video("https://youtu.be/uvE985PF_08");
        videoArrayList.add(video);
        video=new Video("https://youtu.be/v_A90rm8gF8");
        videoArrayList.add(video);
        video=new Video("https://youtu.be/6GMY_uChs0A");
        videoArrayList.add(video);
        video=new Video("https://youtu.be/-P3wK_TfHVk");
        videoArrayList.add(video);



        videoAdapter=new VideoAdapter(videoArrayList,getApplicationContext());
        recyclerViewVideo.setAdapter(videoAdapter);


    }



}