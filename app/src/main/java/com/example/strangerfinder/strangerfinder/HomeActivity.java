package com.example.strangerfinder.strangerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.strangerfinder.strangerfinder.Models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_start_to_chat)
    Button btStartToChat;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.rg_your_sex)
    RadioGroup rgSex;
    @BindView(R.id.rg_looking_for)
    RadioGroup rgPreference;
    @BindView(R.id.rb_female)
    RadioButton rbSexFem;
    @BindView(R.id.rb_male)
    RadioButton rbSexMal;
    @BindView(R.id.rb_lf_female)
    RadioButton rbPreferenceFem;
    @BindView(R.id.rb_lf_male)
    RadioButton rbPreferenceMal;
    @BindView(R.id.rb_lf_both)
    RadioButton rbPreferenceBoth;
    @BindView(R.id.ly_menu)
    LinearLayout lyMenu;

    User user;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Funcion para ejecutar ButterKnife
        ButterKnife.bind(this);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btStart.setVisibility(View.GONE);
                lyMenu.setVisibility(View.VISIBLE);
            }
        });

        /*Funcion para subir un user a free_users
        * */
        btStartToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOk;
                String sex;
                String preference;

                //PASO 1: verificar que el username es correcto
                isOk = !etUsername.getText().toString().isEmpty();

                //PASO 2: comprobar que se ha escogido un sexo
                if(isOk)
                    isOk = verifyRadioButtons(rgSex);
                else{
                    etUsername.setError("You should choose an username");
                }
                //PASO 3: comprobar que se ha escogido una preferencia
                if(isOk)
                    isOk = verifyRadioButtons(rgPreference);

                //PASO 4: Crear un user y subirlo a la BD
                if(isOk){

                    //PASO 1: conectar con firebase
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("free_users");

                    //PASO 2: crear el objeto user
                    user = new User();
                    user.setName(etUsername.getText().toString());

                    //Obtener la preferencia del usuario
                    preference = obtainingSex(Boolean.TRUE);
                    user.setPreference(preference);

                    //Obtener el sexo del usuario
                    sex = obtainingSex(Boolean.FALSE);
                    user.setSex(sex);

                    //PASO 3: Obtener el codigo de la busqueda
                    obtainingCode();

                    //PASO 4: subir el nuevo user a free_users dentro del Json, con push(key)
                    myRef.push().setValue(user);

                    //PASO 5: pasar al LookingForActivity para buscar un user para chatear
                    Intent intent = new Intent(HomeActivity.this,LookingForActivity.class);
                    intent.putExtra("user",user);
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

    //Método para obtener el sexo del usuario y la preferencia de este
    private String obtainingSex(boolean isPreference){
        String sex;

        //Si buscamos la preferencia ejecutamos el siguiente if
        if(isPreference){
            if(rbPreferenceFem.isChecked())
                sex = "female";
            else if(rbPreferenceMal.isChecked())
                sex = "male";
            else
                sex = "both";
        }else{
            if(rbPreferenceFem.isChecked())
                sex = "female";
            else
                sex = "male";
        }

        return sex;
    }


    //Método para obtener el searchCode del usuario
    private void obtainingCode(){
        /**
         * masculino - masculino = code 1
         * femenino - femenino = code 2
         * masculino - femenino = code 3
         * femenino - masculino = code 4
         * masculino - ambos = code 5
         * femenino - ambos = code 6
         */

        if(user.getSex().equals("male")){
            switch(user.getPreference()){
                case "male":
                    user.setSearchCode((byte)1);
                    break;
                case "female":
                    user.setSearchCode((byte)3);
                    break;
                case "both":
                    user.setSearchCode((byte)5);
                    break;
            }
        }

        if(user.getSex().equals("female")){
            switch(user.getPreference()){
                case "female":
                    user.setSearchCode((byte)2);
                    break;
                case "male":
                    user.setSearchCode((byte)4);
                    break;
                case "both":
                    user.setSearchCode((byte)6);
                    break;
            }
        }
    }
}
