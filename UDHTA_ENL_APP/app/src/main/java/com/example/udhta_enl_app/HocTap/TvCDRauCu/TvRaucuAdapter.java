package com.example.udhta_enl_app.HocTap.TvCDRauCu;

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
import com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity.TVCDLoaihoa;
import com.example.udhta_enl_app.R;

import java.util.List;

public class TvRaucuAdapter extends RecyclerView.Adapter<TvRaucuAdapter.TvRaucuViewHolder> {
    private Context context;
    private List<TvCDRauCu> tvCDRauCuList;
    private IClickListenerCDRC iClickListenerCDRC;

    public interface IClickListenerCDRC{
        void onClickItem(TvCDRauCu tvCDRauCu);
    }

    public TvRaucuAdapter(Context context, List<TvCDRauCu> tvCDRauCuList, IClickListenerCDRC iClickListenerCDRC) {
        this.context = context;
        this.tvCDRauCuList = tvCDRauCuList;
        this.iClickListenerCDRC = iClickListenerCDRC;
    }

    @NonNull
    @Override
    public TvRaucuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvcd_raucu,parent,false);

        return new TvRaucuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TvRaucuViewHolder holder, int position) {
        TvCDRauCu tvCDRauCu=tvCDRauCuList.get(position);
        if (tvCDRauCu==null){
            return;
        }
        holder.tvTVCDRC.setText(tvCDRauCu.getTvraucu());
        holder.tvphienamTVCDRC.setText(tvCDRauCu.getPhienamTVraucu());
        holder.tvnghiaTVCDRC.setText(tvCDRauCu.getNghiaTvraucu());
        Glide.with(context).load(tvCDRauCu.LinkanhTvraucu).into(holder.imvAnhTVCDRC);

        holder.ngheTVRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListenerCDRC.onClickItem(tvCDRauCu);
            }
        });

    }
    @Override
    public int getItemCount() {
        if (tvCDRauCuList !=null){
            return tvCDRauCuList.size();
        }
        return 0;
    }

    public class TvRaucuViewHolder extends RecyclerView.ViewHolder{
        TextView tvTVCDRC,tvphienamTVCDRC,tvnghiaTVCDRC;
        ImageView imvAnhTVCDRC,ngheTVRC;
        public TvRaucuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTVCDRC=itemView.findViewById(R.id.tvTVCDRC);
            tvphienamTVCDRC=itemView.findViewById(R.id.tvphienamTVCDRC);
            tvnghiaTVCDRC=itemView.findViewById(R.id.tvnghiaTVCDRC);
            imvAnhTVCDRC=itemView.findViewById(R.id.imvAnhTVCDRC);
            ngheTVRC=itemView.findViewById(R.id.ngheTVRC);


        }
    }
}
