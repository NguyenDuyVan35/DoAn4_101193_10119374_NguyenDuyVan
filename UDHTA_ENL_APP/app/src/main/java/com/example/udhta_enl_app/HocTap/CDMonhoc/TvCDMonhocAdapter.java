package com.example.udhta_enl_app.HocTap.CDMonhoc;

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

public class TvCDMonhocAdapter extends RecyclerView.Adapter<TvCDMonhocAdapter.TvCDMonhocViewHolder> {

    private List<TvCDMonhoc>tvCDMonhocList;
    private Context context;
    private IClickListenerTVCDMH iClickListenerTVCDMH;

    public interface IClickListenerTVCDMH{
        void onClickItem(TvCDMonhoc tvCDMonhoc);
    }

    public TvCDMonhocAdapter(List<TvCDMonhoc> tvCDMonhocList, Context context, IClickListenerTVCDMH iClickListenerTVCDMH) {
        this.tvCDMonhocList = tvCDMonhocList;
        this.context = context;
        this.iClickListenerTVCDMH = iClickListenerTVCDMH;
    }

    @NonNull
    @Override
    public TvCDMonhocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvcd_monhoc,parent,false);
        return new TvCDMonhocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvCDMonhocViewHolder holder, int position) {
        TvCDMonhoc tvCDMonhoc= tvCDMonhocList.get(position);
        if (tvCDMonhoc==null){
            return;
        }

        holder.tvTVCDMH.setText(tvCDMonhoc.getTvMonhoc());
        holder.tvphienamTVCDMH.setText(tvCDMonhoc.getPhienAmTvMonhoc());
        holder.tvnghiaTVCDMH.setText(tvCDMonhoc.getNghiaTvMonhoc());
        Glide.with(context).load(tvCDMonhoc.getLinkAnhTvMonHoc()).into(holder.imvAnhTVCDMH);

        holder.ngheTVMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListenerTVCDMH.onClickItem(tvCDMonhoc);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (tvCDMonhocList!=null){
            return tvCDMonhocList.size();

        }        return 0;
    }

    public class TvCDMonhocViewHolder extends RecyclerView.ViewHolder{
        TextView tvTVCDMH,tvphienamTVCDMH,tvnghiaTVCDMH;
        ImageView imvAnhTVCDMH,ngheTVMH;
        public TvCDMonhocViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTVCDMH=itemView.findViewById(R.id.tvTVCDMH);
            tvphienamTVCDMH=itemView.findViewById(R.id.tvphienamTVCDMH);
            tvnghiaTVCDMH=itemView.findViewById(R.id.tvnghiaTVCDMH);
            imvAnhTVCDMH=itemView.findViewById(R.id.imvAnhTVCDMH);
            ngheTVMH=itemView.findViewById(R.id.ngheTVMH);

        }
    }
}
