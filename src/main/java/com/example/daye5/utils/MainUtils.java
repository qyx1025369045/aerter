package com.example.daye5.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.example.daye5.activity.ArticleDetailActivity;
import com.example.daye5.app.MyApp;

import java.util.Random;

public class MainUtils {
    public static void startArticleDetailActivity(Context context, int articleId, String articleTitle,
                                                  String articleLink, boolean isCollected,
                                                  boolean isShowCollectIcon, int articleItemPosition,
                                                  String eventBusTag) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra("id", articleId);
        intent.putExtra("title", articleTitle);
        intent.putExtra("url", articleLink);
        intent.putExtra("collected", isCollected);
        intent.putExtra("img", isShowCollectIcon);
        intent.putExtra("items", articleItemPosition);
        intent.putExtra("evet", eventBusTag);
        context.startActivity(intent);
    }

    public static int getRandomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red;
        int green;
        int blue;
        if (MyApp.isNightMode()) {
//            150-255
            red = random.nextInt(105) + 150;
            green = random.nextInt(105) + 150;
            blue = random.nextInt(105) + 150;
        } else {
            red = random.nextInt(190);
            green = random.nextInt(190);
            blue = random.nextInt(190);
        }
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }


    public static final int TYPE_HOME_PAGER = 0;
    public static final int TYPE_KNOWLEDGE = 1;
    public static final int TYPE_NAVIGATION = 2;
    public static final int TYPE_WX_ARTICLE = 3;
    public static final int TYPE_PROJECT = 4;

    public static final int TYPE_COLLECT = 5;

    public static final int TYPE_SETTING = 6;
    public static final int TYPE_USEFULSITES = 7;
    public static final int TYPE_SEARCH_RESULT = 8;
    public static final int TYPE_ABOUT_US = 9;

    public static final String TYPE_FRAGMENT_KEY = "type_fragment";
    public static final String SEARCH_KEY = "search_key";
}

