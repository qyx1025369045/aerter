package com.example.daye5.modles.know.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daye5.R;
import com.example.daye5.modles.know.bean.KnowDataBean;
import com.example.daye5.modles.know.presenter.KnowActivity;

import java.util.ArrayList;
import java.util.List;

public class KnowTreeAdapter extends RecyclerView.Adapter<KnowTreeAdapter.KnowViewHolder> {
    private Context context;
    List<KnowDataBean> konwbean = new ArrayList<>();

    public KnowTreeAdapter(Context context) {
        this.context = context;
    }

    public void initKnowData(List<KnowDataBean> konwbean){
        if (konwbean != null) {
            this.konwbean.addAll(konwbean);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KnowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_knowledge_tree_list, null);
        return new KnowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KnowViewHolder knowViewHolder, final int i) {
        final KnowDataBean bean = konwbean.get(i);

        knowViewHolder.knowledge_title.setText(bean.getName());
        if (bean.getChildren() == null){
            return;
        }
        StringBuilder childTitles = new StringBuilder();
        for (KnowDataBean dataBean : bean.getChildren()){
            childTitles.append(dataBean.getName()).append("   ");
        }
        knowViewHolder.title_child.setText(childTitles.toString());

        knowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KnowActivity.class);
                intent.putExtra("know",bean);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return konwbean.size();
    }

    public class KnowViewHolder extends RecyclerView.ViewHolder {
        private TextView knowledge_title, title_child;
        public KnowViewHolder(@NonNull View itemView) {
            super(itemView);
            knowledge_title =itemView.findViewById(R.id.knowledge_title);
            title_child = itemView.findViewById(R.id.title_child);
        }
    }

    public OnClickListener OnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onclicker(KnowDataBean bean);
    }
}
