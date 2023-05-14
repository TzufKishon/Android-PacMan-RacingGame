package com.example.pacmanracinggame;

import android.content.Context;
import android.content.SharedPreferences;

public class MySp {

    private final String SP_FILE_NAME = "MY_SP_FILE";
    private SharedPreferences prefs = null;
    private static MySp _instance = null;

    private MySp(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void initHelper(Context context) {
        if (_instance == null) {
            _instance = new MySp(context);
        }
    }

    public static MySp get_my_SP() {
        return _instance;
    }

    public void putStringToSP(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringFromSP(String key, String def) {
        return prefs.getString(key, def);
    }
}