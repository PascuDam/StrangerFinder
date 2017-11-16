package com.example.strangerfinder.strangerfinder;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.strangerfinder.strangerfinder.Models.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class HomeActivity extends AppCompatActivity {

    Button btStart;
    Button btStartToChat;
    EditText etUsername;
    RadioGroup rgSex;
    RadioGroup rgPreference;
    RadioButton rbSexChose;
    RadioButton rbPreferenceChose;
    LinearLayout lyMenu;
    FirebaseDatabase database;
    DatabaseReference myRef;

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

        btStartToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEvererythingCorrect;

                //STEP 1: verifying the username is correct
                isEvererythingCorrect = !etUsername.getText().toString().isEmpty();

                //STEP 2: verifying that the kind of sex has been chosen
                if(isEvererythingCorrect)
                    isEvererythingCorrect = verifyRadioButtons(rgSex);
                else{
                    etUsername.setError("You should choose an username");
                }
                //STEP 3: verifying that the preference has been chosen
                if(isEvererythingCorrect)
                    isEvererythingCorrect = verifyRadioButtons(rgPreference);

                //STEP 4: Creating a user and searching a partner
                if(isEvererythingCorrect){

                    //STEP 1: connecting with firebase
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("free_users");

                    //STEP 2: creating the user
                    Usuario user = new Usuario();
                    user.setNombre(etUsername.getText().toString());
                    user.setPreferencia(1);
                    user.setSexo(0);

                    Log.e("USER",user.toString());
                    //STEP 3: going to free_user and generate a new user with data
                    myRef.push().setValue(user);
                }

            }
        });

    }

    private boolean verifyRadioButtons(RadioGroup rg){
        boolean isChosen = false;

        if(rg.getCheckedRadioButtonId() != 0){
            isChosen = true;
        }

        return isChosen;
    }
}
