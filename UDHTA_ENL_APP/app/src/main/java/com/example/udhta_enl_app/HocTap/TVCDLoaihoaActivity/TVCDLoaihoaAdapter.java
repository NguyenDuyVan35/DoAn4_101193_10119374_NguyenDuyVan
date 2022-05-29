package com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.R;

import java.util.List;

public class TVCDLoaihoaAdapter extends RecyclerView.Adapter<TVCDLoaihoaAdapter.TVCDLoaihoaViewHolder> {

    private List<TVCDLoaihoa> tvcdLoaihoaList;
    private Context context;
    private IClickListenerCDLH iClickListenerCDLH;

    public interface IClickListenerCDLH{
        void onClickItem(TVCDLoaihoa tvcdLoaihoa);
    }
    public TVCDLoaihoaAdapter(List<TVCDLoaihoa> tvcdLoaihoaList, Context context, IClickListenerCDLH iClickListenerCDLH) {
        this.tvcdLoaihoaList = tvcdLoaihoaList;
        this.context = context;
        this.iClickListenerCDLH = iClickListenerCDLH;
    }
    @NonNull
    @Override
    public TVCDLoaihoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvcd_loaihoa,parent,false);
        return new TVCDLoaihoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVCDLoaihoaViewHolder holder, int position) {

        TVCDLoaihoa tvcdLoaihoa=tvcdLoaihoaList.get(position);
        if (tvcdLoaihoa==null){
            return;
        }
        holder.tvTVCDLH.setText(tvcdLoaihoa.getTuvungCDLH());
        holder.tvphienamTVCDLH.setText(tvcdLoaihoa.getPhienamTVLH());
        holder.tvnghiaTVCDLH.setText(tvcdLoaihoa.getNghiaTVLH());
        Glide.with(context).load(tvcdLoaihoa.LinkanhTVLH).into(holder.imvAnhTVCDLH);

        holder.ngheTVLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListenerCDLH.onClickItem(tvcdLoaihoa);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tvcdLoaihoaList !=null){
            return tvcdLoaihoaList.size();
        }
        return 0;
    }

    public static class TVCDLoaihoaViewHolder extends RecyclerView.ViewHolder{
        TextView tvTVCDLH,tvphienamTVCDLH,tvnghiaTVCDLH;
        ImageView imvAnhTVCDLH,ngheTVLH;
        public TVCDLoaihoaViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTVCDLH=itemView.findViewById(R.id.tvTVCDLH);
            tvphienamTVCDLH=itemView.findViewById(R.id.tvphienamTVCDLH);
            tvnghiaTVCDLH=itemView.findViewById(R.id.tvnghiaTVCDLH);
            imvAnhTVCDLH=itemView.findViewById(R.id.imvAnhTVCDLH);
            ngheTVLH=itemView.findViewById(R.id.ngheTVLH);
        }
    }
}
