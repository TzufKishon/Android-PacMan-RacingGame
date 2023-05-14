package com.example.pacmanracinggame;

import com.google.gson.Gson;

public class DataManager {
    private static DataManager _instance = new DataManager();
    public static final String GAME_TYPE = "GAME_TYPE";
    public static final int NUM_OF_RECORDS = 10;
    private static final String EXTRA_USERS_DETAILS = "EXTRA_USER_DETAILS";

    public DataManager() {
    }

    public void updateTopRecords(UserDetails userDetails) {
        Records records = getTopRecords();
        records.updateTop(userDetails);
        String top_string = new Gson().toJson(records);
        MySp.get_my_SP().putStringToSP(EXTRA_USERS_DETAILS, top_string);
    }

    public static Records getTopRecords() {
        String top_string = MySp.get_my_SP().getStringFromSP(EXTRA_USERS_DETAILS, "{}");
        Records topRecords = new Gson().fromJson(top_string, Records.class);
        return topRecords;
    }

    public static DataManager getDataManager() {
        return _instance;
    }

}