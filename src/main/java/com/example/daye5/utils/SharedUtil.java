package com.example.daye5.utils;

import android.content.SharedPreferences;

import com.example.daye5.app.MyApp;

import static android.content.Context.MODE_PRIVATE;

public class SharedUtil {
    private String PREFERENCE_NAME;

    private SharedUtil() {
    }

    private static SharedUtil sharedpreutil;


    final String DARKMODEL = "darkmodel";

    boolean darkmodel;

    private SharedPreferences getSharedPreferences() {
        return MyApp.getContext().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
    }

    public void load() {
        SharedPreferences prefer = getSharedPreferences();

        darkmodel = prefer.getBoolean(DARKMODEL, false);
    }

    public void save() {
        SharedPreferences prefer = getSharedPreferences();
        SharedPreferences.Editor editor = prefer.edit();

        editor.putBoolean(DARKMODEL, darkmodel);
    }

    public void clear() {
        SharedPreferences prefer = getSharedPreferences();
        SharedPreferences.Editor editor = prefer.edit();

        editor.remove(DARKMODEL);
    }

}
