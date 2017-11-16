package com.example.strangerfinder.strangerfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class HomeActivity extends AppCompatActivity {

    Button btStart;
    Button btStartToChat;
    EditText etUsername;
    RadioGroup rgSex;
    RadioGroup rgPreference;
    RadioButton rbSexChose;
    RadioButton rbPreferenceChose;
    LinearLayout lyMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btStart = (Button) findViewById(R.id.bt_start);
        btStartToChat = (Button) findViewById(R.id.bt_start_to_chat);
        etUsername = (EditText) findViewById(R.id.et_username);
        rgSex = (RadioGroup) findViewById(R.id.rg_your_sex);
        rgPreference = (RadioGroup) findViewById(R.id.rg_looking_for);
        lyMenu = (LinearLayout) findViewById(R.id.ly_menu);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btStart.setVisibility(View.GONE);
                lyMenu.setVisibility(View.VISIBLE);
            }
        });

    }
}
