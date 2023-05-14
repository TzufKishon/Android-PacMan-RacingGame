package com.example.pacmanracinggame;

import android.util.Log;
import java.util.ArrayList;

public class Records {
    private int maxTop = 10;
    private ArrayList<UserDetails> top_records = new ArrayList<>();

    public void updateTop(UserDetails userDetails) {
        top_records.add(userDetails);
        top_records.sort((pl1, pl2) -> pl2.getScore() - pl1.getScore());
        if (top_records.size() >= maxTop)
            for (int index = maxTop; index < top_records.size(); index++) {
                Log.d("mylog", "no need now" + maxTop);
                top_records.remove(index);
            }
    }

    public UserDetails getDetailsByIndex(int index) {
        return top_records.get(index);
    }

    public ArrayList<UserDetails> getTop_records() {
        return top_records;
    }

    public int numOfRecords() {
        return top_records.size();
    }

    @Override
    public String toString() {
        return "Records{" +
                ", top_records=" + top_records.toString() +
                '}';
    }

}