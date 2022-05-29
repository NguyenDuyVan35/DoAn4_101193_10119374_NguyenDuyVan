package com.example.udhta_enl_app.HocTap.CDDongvat;

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

public class TvCDDongVatAdapter extends RecyclerView.Adapter<TvCDDongVatAdapter.TvCDDongVatViewHolder>{

    private List<TvCDDongVat> dongVats;
    private Context context;
    private  IClickItemTVDV iClickItemTVDV;
    public interface IClickItemTVDV{
        void onClickItemTVDV(TvCDDongVat tvCDDongVat);
    }

    public TvCDDongVatAdapter(List<TvCDDongVat> dongVats, Context context, IClickItemTVDV iClickItemTVDV) {
        this.dongVats = dongVats;
        this.context = context;
        this.iClickItemTVDV = iClickItemTVDV;
    }

    @NonNull
    @Override
    public TvCDDongVatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvcd_dongvat,parent,false);
        return new TvCDDongVatViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TvCDDongVatViewHolder holder, int position) {
        TvCDDongVat tvCDDongVat=dongVats.get(position);
        if (tvCDDongVat ==null){
            return;
        }
        holder.tvTVCDDV.setText(tvCDDongVat.getTuVungDV());
        holder.tvphienamTVCDDV.setText(tvCDDongVat.getPhienAmTVDV());
        holder.tvnghiaTVCDDV.setText(tvCDDongVat.getNghiaTVDV());
        Glide.with(context).load(tvCDDongVat.getLinkAnhtvCDDv()).into(holder.imvAnhTVCDDV);
        holder.ngheTVDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemTVDV.onClickItemTVDV(tvCDDongVat);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (dongVats !=null){
            return dongVats.size();
        }
        return 0;
    }

    public static class TvCDDongVatViewHolder extends RecyclerView.ViewHolder{
        TextView tvTVCDDV,tvphienamTVCDDV,tvnghiaTVCDDV;
        ImageView imvAnhTVCDDV,ngheTVDV;

        public TvCDDongVatViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTVCDDV=itemView.findViewById(R.id.tvTVCDDV);
            tvphienamTVCDDV=itemView.findViewById(R.id.tvphienamTVCDDV);
            tvnghiaTVCDDV=itemView.findViewById(R.id.tvnghiaTVCDDV);
            imvAnhTVCDDV=itemView.findViewById(R.id.imvAnhTVCDDV);
            ngheTVDV=itemView.findViewById(R.id.ngheTVDV);
        }
    }
}
