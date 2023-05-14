package com.example.pacmanracinggame;

import static com.example.pacmanracinggame.GameServices.showToast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class GetName extends AppCompatActivity {
    private MaterialButton score_BTN_save;
    private MaterialButton score_BTN_menu;
    public static final String KEY_SCORE = "KEY_SCORE";
    private TextView score_LBL_score;
    private EditText score_ETXT_name;
    private String name;
    private int score;
    private GPS my_gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        findView();
        initView();
        Intent previousIntent = getIntent();
        score = previousIntent.getExtras().getInt(KEY_SCORE);
        score_LBL_score.setText("Score: " + score);
        my_gps = new GPS(this);

    }

    private void initView() {
        score_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                score_BTN_save.setVisibility(View.INVISIBLE);
            }
        });
        score_BTN_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetName.this, Menu_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void findView() {
        score_BTN_save = findViewById(R.id.score_BTN_save);
        score_BTN_menu = findViewById(R.id.score_BTN_menu);
        score_LBL_score = findViewById(R.id.score_LBL_score);
        score_ETXT_name = findViewById(R.id.score_ETXT_name);
    }

    private void save() {
        name = score_ETXT_name.getText().toString();
        UserDetails userDetails = new UserDetails().setName(name).setScore(score).setLat(my_gps.getLat()).setLag(my_gps.getLag());
        DataManager.getDataManager().updateTopRecords(userDetails);
        showToast(this, "Saved");
    }
}
