package com.example.strangerfinder.strangerfinder;

import android.content.Intent;
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

        //Funcion para subir un usuario a free_users
        btStartToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEvererythingCorrect;

                //PASO 1: verificar que el username es correcto
                isEvererythingCorrect = !etUsername.getText().toString().isEmpty();

                //PASO 2: comprobar que se ha escogido un sexo
                if(isEvererythingCorrect)
                    isEvererythingCorrect = verifyRadioButtons(rgSex);
                else{
                    etUsername.setError("You should choose an username");
                }
                //PASO 3: comprobar que se ha escogido una preferencia
                if(isEvererythingCorrect)
                    isEvererythingCorrect = verifyRadioButtons(rgPreference);

                //PASO 4: Crear un usuario y subirlo a la BD
                if(isEvererythingCorrect){

                    //PASO 1: conectar con firebase
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("free_users");

                    //PASO 2: crear el objeto usuario
                    Usuario user = new Usuario();
                    user.setNombre(etUsername.getText().toString());
                    //TODO: obtener el sexo y la preferencia de los radiobuttons
                    user.setPreferencia(1);
                    user.setSexo(0);

                    Log.e("USER",user.toString());
                    //PASO 3: subir el nuevo usuario a free_users dentro del Json, con push(key)
                    myRef.push().setValue(user);

                    //PASO 4: pasar al LookingForActivity para buscar un user para chatear
                    Intent intent = new Intent(HomeActivity.this,LookingForActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    /**
     * Funcion para comprobar si un RadioButton ha sido seleccionado
     * @param rg - hace referencia al RadioGroup que se le pasa por parametro
     * @return - devuelve un booleano que nos indica si se ha escogido o no un RB
     */
    private boolean verifyRadioButtons(RadioGroup rg){
        boolean isChosen = false;

        if(rg.getCheckedRadioButtonId() != 0){
            isChosen = true;
        }

        return isChosen;
    }
}
