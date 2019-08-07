package com.example.daye5.modles.publics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daye5.R;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;

import java.util.ArrayList;
import java.util.List;

public class PublicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<ProjectListDatasBean> list = new ArrayList<>();

    public PublicListAdapter(Context context) {
        this.context = context;
    }

    public void initDate(List<ProjectListDatasBean> list){
        if (list != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_public_list, null);
        return new PubliViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final ProjectListDatasBean bean = list.get(i);
        PubliViewHolder holder = (PubliViewHolder) viewHolder;
        holder.public_list_title.setText(bean.getTitle());
        holder.public_list_author.setText(bean.getAuthor());
        if (!TextUtils.isEmpty(bean.getDesc())) {
            holder.public_list_content.setText(bean.getDesc());
        }
        if (!TextUtils.isEmpty(bean.getNiceDate())){
            holder.public_list_time.setText(bean.getNiceDate());
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickListener.onclicker(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class PubliViewHolder extends RecyclerView.ViewHolder{
        private TextView public_list_title,public_list_content
                ,public_list_time,public_list_author;
        private ImageView public_list_like_img;
        public PubliViewHolder(@NonNull View itemView) {
            super(itemView);
            public_list_title = itemView.findViewById(R.id.public_list_title);
            public_list_content = itemView.findViewById(R.id.public_list_content);
            public_list_time = itemView.findViewById(R.id.public_list_time);
            public_list_author = itemView.findViewById(R.id.public_list_author);
            public_list_like_img = itemView.findViewById(R.id.public_list_like_img);
        }
    }

    private OnClickListener OnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onclicker(ProjectListDatasBean bean);
    }
}
