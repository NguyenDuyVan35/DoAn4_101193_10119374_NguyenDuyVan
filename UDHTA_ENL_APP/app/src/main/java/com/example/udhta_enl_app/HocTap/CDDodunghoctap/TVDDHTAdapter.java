package com.example.udhta_enl_app.HocTap.CDDodunghoctap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.HocTap.TvCDRauCu.TvCDRauCu;
import com.example.udhta_enl_app.R;

import java.util.List;

public class TVDDHTAdapter extends RecyclerView.Adapter<TVDDHTAdapter.TVDDHTViewHolder> {
    private Context context;
    private List<TvCDDodunghoctap> tvCDDodunghoctapList;
    private IClickListenerCDDDHT iClickListenerCDRC;

    public interface IClickListenerCDDDHT{
        void onClickItem(TvCDDodunghoctap tvCDDodunghoctap);
    }

    public TVDDHTAdapter(Context context, List<TvCDDodunghoctap> tvCDDodunghoctapList, IClickListenerCDDDHT iClickListenerCDRC) {
        this.context = context;
        this.tvCDDodunghoctapList = tvCDDodunghoctapList;
        this.iClickListenerCDRC = iClickListenerCDRC;
    }

    @NonNull
    @Override
    public TVDDHTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvcd_dodunghoctap,parent,false);

        return new TVDDHTViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TVDDHTViewHolder holder, int position) {
        TvCDDodunghoctap tvCDDodunghoctap=tvCDDodunghoctapList.get(position);
        if (tvCDDodunghoctap==null){
            return;
        }
        holder.tvTVCDDDHT.setText(tvCDDodunghoctap.getTvDDHT());
        holder.tvphienamTVCDDDHT.setText(tvCDDodunghoctap.getPhiemAmTvDDHT());
        holder.tvnghiaTVCDDDHT.setText(tvCDDodunghoctap.getNghiaTvDDHT());
        Glide.with(context).load(tvCDDodunghoctap.getLinkAnhTvDDHT()).into(holder.imvAnhTVCDDDHT);

        holder.ngheDDHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListenerCDRC.onClickItem(tvCDDodunghoctap);
            }
        });

    }
    @Override
    public int getItemCount() {
        if (tvCDDodunghoctapList !=null){
            return tvCDDodunghoctapList.size();
        }
        return 0;
    }

    public class TVDDHTViewHolder extends RecyclerView.ViewHolder{
        TextView tvTVCDDDHT,tvphienamTVCDDDHT,tvnghiaTVCDDDHT;
        ImageView imvAnhTVCDDDHT,ngheDDHT;
        public TVDDHTViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTVCDDDHT=itemView.findViewById(R.id.tvTVCDDDHT);
            tvphienamTVCDDDHT=itemView.findViewById(R.id.tvphienamTVCDDDHT);
            tvnghiaTVCDDDHT=itemView.findViewById(R.id.tvnghiaTVCDDDHT);
            imvAnhTVCDDDHT=itemView.findViewById(R.id.imvAnhTVCDDDHT);
            ngheDDHT=itemView.findViewById(R.id.ngheDDHT);


        }
    }
}
