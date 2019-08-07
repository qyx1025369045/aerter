package com.example.daye5.modles.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daye5.R;
import com.example.daye5.modles.home.bean.BannerData;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.utils.MainUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<HomeDatasBean> artlist = new ArrayList<>();
    List<BannerData> bannlist = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    List<ImageView> images = new ArrayList<>();

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void initArtData(List<HomeDatasBean> list) {
        if (artlist != null) {
            this.artlist.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void initBannerData(List<BannerData> bannlist) {
        if (bannlist != null) {
            this.bannlist.addAll(bannlist);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if (type == 0) {
            View view = View.inflate(context, R.layout.home_banner_item, null);
            return new BannViewHolder(view);
        } else {
            View view = View.inflate(context, R.layout.home_art_item, null);
            return new ArtViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == 0) {
            BannViewHolder bannViewHolder = (BannViewHolder) viewHolder;
            final ArrayList<String> bannerTitile = new ArrayList<>();
            final ArrayList<String> bannerUrl = new ArrayList<>();
            ArrayList<String> bannerImage = new ArrayList<>();
            final ArrayList<Integer> bannerId = new ArrayList<>();
            for (BannerData bannerData : bannlist) {
                bannerTitile.add(bannerData.getTitle());
                bannerUrl.add(bannerData.getUrl());
                bannerImage.add(bannerData.getImagePath());
                bannerId.add(bannerData.getId());
            }
            //设置banner样式
            bannViewHolder.ban.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置banner动画效果
            bannViewHolder.ban.setBannerAnimation(Transformer.Accordion);
            //设置自动轮播，默认为true
            bannViewHolder.ban.isAutoPlay(true);
            //设置轮播时间
            bannViewHolder.ban.setDelayTime(2500);
            //设置指示器位置（当banner模式中有指示器时）
            bannViewHolder.ban.setIndicatorGravity(BannerConfig.CENTER);
            //设置图片加载器
            bannViewHolder.ban.setImageLoader(new GlideImageLoder());
            //设置banner标题
            bannViewHolder.ban.setBannerTitles(bannerTitile);
            //设置图片
            bannViewHolder.ban.setImages(bannerImage);
            bannViewHolder.ban.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int i1) {
                    MainUtils.startArticleDetailActivity(context, bannerId.get(i1),
                            bannerTitile.get(i1), bannerUrl.get(i1),
                            false, false,
                            -1, "home");
                }
            });
            bannViewHolder.ban.start();
        } else {
            if (bannlist.size() != 0) {
                i = i - 1;
            }
            final HomeDatasBean bean = artlist.get(i);
            ArtViewHolder artViewHolder = (ArtViewHolder) viewHolder;

            artViewHolder.home_art_title.setText(bean.getTitle());
            artViewHolder.tv_article_author.setText(bean.getAuthor());
            if (!TextUtils.isEmpty(bean.getChapterName())) {
                String chapter = bean.getSuperChapterName() + "/" + bean.getChapterName();
                artViewHolder.tv_article_chapterName.setText(chapter);
            }
            if (!TextUtils.isEmpty(bean.getNiceDate())) {
                artViewHolder.home_art_nicedata.setText(bean.getNiceDate());
            }
            artViewHolder.tv_article_top.setVisibility(bean.getType() == 1 ? View.VISIBLE : View.GONE);
            artViewHolder.tv_article_fresh.setVisibility(bean.isFresh() ? View.VISIBLE : View.GONE);

            if (!TextUtils.isEmpty(bean.getEnvelopePic())) {
                artViewHolder.home_art_img.setVisibility(View.VISIBLE);
                Glide.with(context).load(bean.getEnvelopePic()).into(artViewHolder.home_art_img);
            } else {
                artViewHolder.home_art_img.setVisibility(View.GONE);
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickListener.onclicker(bean);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (bannlist.size() > 0) {
            return artlist.size() + 1;
        } else {
            return artlist.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (bannlist.size() > 0 && position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class BannViewHolder extends RecyclerView.ViewHolder {
        private Banner ban;

        public BannViewHolder(@NonNull View itemView) {
            super(itemView);
            ban = itemView.findViewById(R.id.ban);
        }
    }

    class ArtViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_article_top, tv_article_fresh,
                tv_article_tag, tv_article_author, tv_article_chapterName,
                home_art_title, home_art_nicedata;
        private ImageView home_art_img, home_art_like;

        public ArtViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_article_top = itemView.findViewById(R.id.tv_article_top);
            tv_article_fresh = itemView.findViewById(R.id.tv_article_fresh);
            tv_article_tag = itemView.findViewById(R.id.tv_article_tag);
            tv_article_author = itemView.findViewById(R.id.tv_article_author);
            tv_article_chapterName = itemView.findViewById(R.id.tv_article_chapterName);
            home_art_title = itemView.findViewById(R.id.home_art_title);
            home_art_nicedata = itemView.findViewById(R.id.home_art_nicedata);
            home_art_img = itemView.findViewById(R.id.home_art_img);
            home_art_like = itemView.findViewById(R.id.home_art_like);
        }
    }

    class GlideImageLoder extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    public OnClickListener OnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onclicker(HomeDatasBean bean);
    }
}
