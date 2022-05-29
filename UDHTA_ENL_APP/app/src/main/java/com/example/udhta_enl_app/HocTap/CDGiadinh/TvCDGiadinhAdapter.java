package com.example.udhta_enl_app.HocTap.CDGiadinh;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.R;

import java.util.List;


public class TvCDGiadinhAdapter extends RecyclerView.Adapter<TvCDGiadinhAdapter.TvCDGiadinhViewHolder>{

    //khai báo list các đối tượng
    private List<TvCDGiadinh> tvCDGiadinhList;
    private Context context;
    private IClickListener iClickListener;

    public  interface IClickListener{
        void onClickItem(TvCDGiadinh tvCDGiadinh);
    }
//    private IClickItemTV iClickItemTV;

    public TvCDGiadinhAdapter(List<TvCDGiadinh> tvCDGiadinhList, Context context,IClickListener iClickListener) {
        this.tvCDGiadinhList = tvCDGiadinhList;
        this.context = context;
        this.iClickListener=iClickListener;
//        this.iClickItemTV=clickItemTV;
    }

    @NonNull
    @Override
    public TvCDGiadinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvcd_giadinh,parent,false);
        return new TvCDGiadinhViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TvCDGiadinhViewHolder holder, int position) {
        TvCDGiadinh tvCDGiadinh=tvCDGiadinhList.get(position);
        //kiểm tra nếu tvCDGiadinh bằng null thì sẽ return và không làm gì cả, còn nếu khác null thì sẽ set dữ liệu cho holder
        if (tvCDGiadinh == null){
            return;
        }
        holder.tvTVCDGD.setText(tvCDGiadinh.getTuVung());
        holder.tvphienamTVCDGD.setText(tvCDGiadinh.getPhienAm());
        holder.tvnghiaTVCDGD.setText(tvCDGiadinh.getNghia());
        Glide.with(context).load(tvCDGiadinh.getLinkAnhtvCDGD()).into(holder.imvAnhTVCDGD);

        holder.nghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListener.onClickItem(tvCDGiadinh);
            }
        });


    }
    @Override
    public int getItemCount() {
        //kiểm tra nếu list dữ liệu mà khác null thì return kích thước của list dữ liệu này còn nếu bằng null thì return bằng 0
        if (tvCDGiadinhList != null){
            return tvCDGiadinhList.size();
        }
        return 0;
    }

    public static class TvCDGiadinhViewHolder extends RecyclerView.ViewHolder{

        TextView tvTVCDGD,tvphienamTVCDGD,tvnghiaTVCDGD;
        ImageView imvAnhTVCDGD,nghe;
//0-

        public TvCDGiadinhViewHolder(@NonNull View itemView) {
            super(itemView);
            //ánh xạ View
            tvTVCDGD=itemView.findViewById(R.id.tvTVCDGD);
            tvphienamTVCDGD=itemView.findViewById(R.id.tvphienamTVCDGD);
            tvnghiaTVCDGD=itemView.findViewById(R.id.tvnghiaTVCDGD);
            imvAnhTVCDGD=itemView.findViewById(R.id.imvAnhTVCDGD);
            nghe=itemView.findViewById(R.id.nghe);
        }
    }


}
