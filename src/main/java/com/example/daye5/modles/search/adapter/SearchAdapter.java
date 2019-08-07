package com.example.daye5.modles.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daye5.R;
import com.example.daye5.modles.search.bean.SeachBean;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<SeachBean> list = new ArrayList<>();

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void initData(List<SeachBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_search_history, null);
        return new Viewholder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SeachBean bean = list.get(i);
        Viewholder1 holder = (Viewholder1) viewHolder;
        holder.searchkey.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder1 extends RecyclerView.ViewHolder{
        TextView searchkey;
        ImageView clear;
        public Viewholder1(@NonNull View itemView) {
            super(itemView);
            searchkey = itemView.findViewById(R.id.tv_search_key);
            clear = itemView.findViewById(R.id.iv_clear);
        }
    }
}
