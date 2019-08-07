package com.example.daye5.modles.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daye5.R;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;

import java.util.ArrayList;
import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<ProjectListDatasBean> list = new ArrayList<>();

    public ProjectListAdapter(Context context) {
        this.context = context;
    }

    public void initDatar(List<ProjectListDatasBean> list){
        if (list != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_project_list, null);
        return new ProViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final ProjectListDatasBean bean = list.get(i);
        ProViewHolder holder = (ProViewHolder) viewHolder;

        if (!TextUtils.isEmpty(bean.getEnvelopePic())) {
            Glide.with(context).load(bean.getEnvelopePic()).into(holder.project_list_img);
        }
        holder.project_list_title.setText(bean.getTitle());
        holder.project_list_author.setText(bean.getAuthor());
        if (!TextUtils.isEmpty(bean.getDesc())) {
            holder.project_list_content.setText(Html.fromHtml(bean.getDesc()));
        }
        if (!TextUtils.isEmpty(bean.getNiceDate())){
            holder.project_list_time.setText(bean.getNiceDate());
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickListener.onclickes(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProViewHolder extends RecyclerView.ViewHolder{
        private ImageView project_list_img,project_list_like_img;
        private TextView project_list_title,project_list_content,
                project_list_time,project_list_author;
        public ProViewHolder(@NonNull View itemView) {
            super(itemView);
            project_list_img = itemView.findViewById(R.id.project_list_img);
            project_list_like_img = itemView.findViewById(R.id.project_list_like_img);
            project_list_title = itemView.findViewById(R.id.project_list_title);
            project_list_content = itemView.findViewById(R.id.project_list_content);
            project_list_time = itemView.findViewById(R.id.project_list_time);
            project_list_author = itemView.findViewById(R.id.project_list_author);
        }
    }

    private OnClickListener OnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onclickes(ProjectListDatasBean bean);
    }
}
