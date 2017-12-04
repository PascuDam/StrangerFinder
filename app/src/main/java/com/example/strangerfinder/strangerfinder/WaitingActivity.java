package com.example.strangerfinder.strangerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.strangerfinder.strangerfinder.Models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaitingActivity extends AppCompatActivity {

    @BindView(R.id.bt_search_again)
    Button btSearchAqain;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        ButterKnife.bind(this);

        user = getIntent().getParcelableExtra("user");

        btSearchAqain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PASO 1: conectar con firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("free_users");

                //PASO 2: subir el nuevo user a free_users dentro del Json, con push(key)
                DatabaseReference userKey = myRef.push();
                userKey.setValue(user);
                user.setKey(userKey.getKey());
                user.setWantToChange(Boolean.TRUE);

                Intent intent = new Intent(WaitingActivity.this,LookingForActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }
}
