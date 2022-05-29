package com.example.udhta_enl_app.TuDienTA;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TuVungAdapter extends RecyclerView.Adapter<TuVungAdapter.TuVungViewHolder> implements Filterable {

    private List<TuVung> tuVungList;
    private List<TuVung> tuVungListOld;
    private Context context;
    private IClickListenernTV iClickItemTV;
//    tạo một lớp interface để bắt sự kiện onClick
    public interface IClickListenernTV{
        void OnClickItemTV(TuVung tuVung);
    }

    public TuVungAdapter(List<TuVung> tuVungList, Context context, IClickListenernTV iClickItemTV) {
        this.tuVungList = tuVungList;
        this.tuVungListOld=tuVungList;
        this.context = context;
        this.iClickItemTV = iClickItemTV;
    }

    @NonNull
    @Override
    public TuVungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lisview_tudien,parent,false);
        return new TuVungViewHolder(view);
    }

    //hàm onBindViewHolder dùng đề bind dữ liệu lên
    @Override
    public void onBindViewHolder(@NonNull TuVungViewHolder holder, int position) {
        //khai báo đối tượng Từ vựng
        TuVung tuVung=tuVungList.get(position);
        //kiểm tra nếu tuVung mà bằng null thì sẽ return và không làm gì cả
        //còn nếu nó khác null thì sẽ sét dữ liệu cho thằng holer
        if (tuVung == null){
            return;
        }
        holder.tvTuVung.setText(tuVung.getTuVungTA());
        holder.tvPhienamTV.setText(tuVung.getPhienAmTA());
        holder.tvNghiaTV.setText(tuVung.getNghiaTV());
        holder.tvViduTV.setText(tuVung.getViDu());
        holder.tvDichVidu.setText(tuVung.getDichVD());
        Glide.with(context).load(tuVung.getLinkAnhTV()).into(holder.imageViewAnhTV);

        Animation animationnhay= AnimationUtils.loadAnimation(context,R.anim.animation_nhay);

        holder.voiceTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemTV.OnClickItemTV(tuVung);
                holder.voiceTV.setAnimation(animationnhay);
            }
        });
    }

    @Override
    public int getItemCount() {

        //kiểm tra nếu danh sách từ vựng mà khác rỗng thì return kích thước của list dữ liệu từ vựng
        //con nếu bằng null thì sẽ return bằng 0
        if (tuVungList != null){
            return tuVungList.size();
        }
        return 0;
    }



    public class TuVungViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvTuVung;
        private final TextView tvPhienamTV;
        private final TextView tvNghiaTV;
        private final TextView tvViduTV;
        private final TextView tvDichVidu;
        private final ImageView imageViewAnhTV;
        private final ImageView voiceTV;

        public TuVungViewHolder(@NonNull View itemView) {
            super(itemView);


            tvTuVung=itemView.findViewById(R.id.tvTuVung);
            tvPhienamTV=itemView.findViewById(R.id.tvPhienamTV);
            tvNghiaTV=itemView.findViewById(R.id.tvNghiaTV);
            tvViduTV=itemView.findViewById(R.id.tvViduTV);
            tvDichVidu=itemView.findViewById(R.id.tvDichVidu);
            imageViewAnhTV=itemView.findViewById(R.id.imageViewAnhTV);
            voiceTV=itemView.findViewById(R.id.voiceTV);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String strSearch=constraint.toString();
                if (strSearch.isEmpty()){
                    tuVungList=tuVungListOld;
                }
                else {
                    List<TuVung> list=new  ArrayList<>();
                    for (TuVung tuVung:tuVungListOld){
                        if (tuVung.getTuVungTA().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(tuVung);
                        }
                    }
                    tuVungList=list;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values=tuVungList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                tuVungList=(List<TuVung>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
