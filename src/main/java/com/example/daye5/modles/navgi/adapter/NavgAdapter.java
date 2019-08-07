package com.example.daye5.modles.navgi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.daye5.R;
import com.example.daye5.modles.navgi.bean.ArticlesBean;
import com.example.daye5.modles.navgi.bean.NavightBean;
import com.example.daye5.utils.MainUtils;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

public class NavgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<NavightBean> nvglist = new ArrayList<>();
    public NavgAdapter(Context context) {
        this.context = context;
    }

    public void initDatas(List<NavightBean> nvglist){
        if (nvglist != null) {
            this.nvglist.addAll(nvglist);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NavgViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_navger, null);
        return new NavgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final NavightBean bean = nvglist.get(i);

        NavgViewHolder holder = (NavgViewHolder) viewHolder;
        holder.nvgchaed.setText(bean.getName());

        final TagFlowLayout mTagflowlayout = holder.mTagflowlayout;
        final List<ArticlesBean> articles = bean.getArticles();
        mTagflowlayout.setAdapter(new TagAdapter<ArticlesBean>(articles) {

            @Override
            public View getView(final com.zhy.view.flowlayout.FlowLayout parent, final int position, ArticlesBean articlesBean) {
                View view = View.inflate(context, R.layout.flow_layout_tv, null);
                TextView title = view.findViewById(R.id.common_title_tv);
                if (articlesBean == null){
                    return null;
                }
                String title1 = articlesBean.getTitle();
                title.setText(title1);
//                title.setTextColor();

                mTagflowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, com.zhy.view.flowlayout.FlowLayout parent) {
                        MainUtils.startArticleDetailActivity(parent.getContext(),
                                articles.get(position).getId(),
                                articles.get(position).getTitle().trim(),
                                articles.get(position).getLink().trim(),
                                articles.get(position).isCollect(),
                                true, -1, "tag_default");
                        return true;
                    }
                });
                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainUtils.startArticleDetailActivity(parent.getContext(),
                                articles.get(position).getId(),
                                articles.get(position).getTitle().trim(),
                                articles.get(position).getLink().trim(),
                                articles.get(position).isCollect(),
                                true, -1, "tag_default");
                    }
                });
                return title;
            }
        });


    }


    @Override
    public int getItemCount() {
        return nvglist.size();
    }

    public class NavgViewHolder extends RecyclerView.ViewHolder {
        private TextView nvgchaed;
        private TagFlowLayout mTagflowlayout;
        public NavgViewHolder(@NonNull View itemView) {
            super(itemView);
            nvgchaed = itemView.findViewById(R.id.nvg_chaed);
            mTagflowlayout = itemView.findViewById(R.id.item_navigation_flow_layout);
        }
    }

    public OnClickListener OnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onclicker(ArticlesBean bean);
    }
}
