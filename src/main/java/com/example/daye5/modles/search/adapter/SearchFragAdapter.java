package com.example.daye5.modles.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daye5.R;
import com.example.daye5.activity.ArticleDetailActivity;
import com.example.daye5.modles.home.bean.HomeDatasBean;

import java.util.ArrayList;
import java.util.List;

public class SearchFragAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<HomeDatasBean> list = new ArrayList<>();

    public SearchFragAdapter(Context context) {
        this.context = context;
    }

    public void initData(List<HomeDatasBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_seach_list, null);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final HomeDatasBean bean = list.get(i);
        ViewHolders holders = (ViewHolders) viewHolder;
        holders.name.setText(bean.getAuthor());
        if (!TextUtils.isEmpty(bean.getChapterName())) {
            String chapt = bean.getSuperChapterName() + "/" + bean.getChapterName();
            holders.chapName.setText(chapt);
        }
        if (!TextUtils.isEmpty(bean.getNiceDate())) {
            holders.timer.setText(bean.getNiceDate());
        }
        holders.title.setText(bean.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.putExtra("url",bean.getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolders extends RecyclerView.ViewHolder {
        private TextView title, name, chapName, timer;
        private ImageView searimg;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sear_name);
            chapName = itemView.findViewById(R.id.sear_chapName);
            title = itemView.findViewById(R.id.sear_title);
            searimg = itemView.findViewById(R.id.sear_img);
            timer = itemView.findViewById(R.id.sear_timer);
        }
    }
}
