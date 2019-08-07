package com.example.daye5.modles.collection.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daye5.R;
import com.example.daye5.modles.collection.bean.CollDataBean;
import com.example.daye5.modles.collection.bean.ColleBean;

import java.util.ArrayList;
import java.util.List;

public class CollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<CollDataBean> list = new ArrayList<>();

    public CollectAdapter(Context context) {
        this.context = context;
    }

    public void initData(List<CollDataBean> list){
        if (list != null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.home_art_item, null);
        return new ViewHolers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CollDataBean bean = list.get(i);
        ViewHolers holers = (ViewHolers) viewHolder;
        if (!TextUtils.isEmpty(bean.getAuthor())) {
            holers.author.setText(bean.getAuthor());
        }
        if (!TextUtils.isEmpty(bean.getChapterName())) {
            holers.chapterName.setText(bean.getChapterName());
        }
        if (!TextUtils.isEmpty(bean.getNiceDate())) {
            holers.nice.setText(bean.getNiceDate());
        }
        holers.title.setText(bean.getTitle());
        ColleBean colleBean = new ColleBean();
        if (colleBean.isOver() == true) {
            holers.collimg.setImageResource(R.drawable.ic_like);
        } else {
            holers.collimg.setImageResource(R.drawable.ic_like_not);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolers extends RecyclerView.ViewHolder {
        private TextView author, chapterName, title, nice;
        private ImageView collimg;

        public ViewHolers(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_article_author);
            chapterName = itemView.findViewById(R.id.tv_article_chapterName);
            title = itemView.findViewById(R.id.home_art_title);
            nice = itemView.findViewById(R.id.home_art_nicedata);
            collimg = itemView.findViewById(R.id.home_art_like);
        }
    }
}
