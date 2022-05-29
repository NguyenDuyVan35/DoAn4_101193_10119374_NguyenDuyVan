package com.example.udhta_enl_app.Kiemtra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udhta_enl_app.HocTap.CDDodunghoctap.TvCDDodunghoctap;
import com.example.udhta_enl_app.R;
import com.example.udhta_enl_app.TaiKhoan.TaiKhoan;

import java.util.List;

public class DSDiemKTAdapter extends RecyclerView.Adapter<DSDiemKTAdapter.DSDiemKTViewHolder>{
    private List<TaiKhoan> taiKhoanList;

    public DSDiemKTAdapter( List<TaiKhoan> taiKhoanList) {
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public DSDiemKTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diemkt,parent,false);
        return new DSDiemKTViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSDiemKTViewHolder holder, int position) {
        TaiKhoan taiKhoan=taiKhoanList.get(position);
        if (taiKhoan==null){
            return;
        }
        holder.BaiKTso.setText(taiKhoan.getBaikt());
        holder.TvName.setText(taiKhoan.getName());
        holder.TVDiem.setText(taiKhoan.getDiem());
        holder.TVSocaudung.setText(taiKhoan.getSocaudung());
    }

    @Override
    public int getItemCount() {
        if (taiKhoanList !=null){
            return taiKhoanList.size();
        }
        return 0;
    }

    public class DSDiemKTViewHolder extends RecyclerView.ViewHolder{
        TextView BaiKTso,TvName,TVDiem,TVSocaudung;
        public DSDiemKTViewHolder(@NonNull View itemView) {
            super(itemView);


            BaiKTso=itemView.findViewById(R.id.BaiKTso);
            TvName=itemView.findViewById(R.id.TvName);
            TVDiem=itemView.findViewById(R.id.TVDiem);
            TVSocaudung=itemView.findViewById(R.id.TVSocaudung);
        }
    }
}
