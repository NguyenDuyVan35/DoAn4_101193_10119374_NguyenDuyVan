package com.example.udhta_enl_app.Video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.udhta_enl_app.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    ArrayList<Video> videoArrayList;
    Context context;

    public VideoAdapter(ArrayList<Video> videoArrayList, Context context) {
        this.videoArrayList = videoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.video_item,parent,false);

        return new VideoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video=videoArrayList.get(position);
        holder.webViewVideo.loadUrl(video.getLink());
        holder.imageViewFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,PlayVideoActivity.class);
                intent.putExtra("Link",video.getLink());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView webViewVideo;
        ImageView imageViewFullScreen;
        @SuppressLint("SetJavaScriptEnabled")
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            webViewVideo=itemView.findViewById(R.id.webView);
            imageViewFullScreen=itemView.findViewById(R.id.imageViewFullScreen);

            webViewVideo.setWebViewClient(new WebViewClient());
            webViewVideo.setWebChromeClient(new WebChromeClient());
            webViewVideo.getSettings().setJavaScriptEnabled(true);
        }
    }
}
