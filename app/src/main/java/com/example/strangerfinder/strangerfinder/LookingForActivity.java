package com.example.strangerfinder.strangerfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.strangerfinder.strangerfinder.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.ListIterator;

public class LookingForActivity extends AppCompatActivity {

    User user;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Boolean match;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_for);

        //PASO 1: Obtener el user creado en HomeActivity
        user = getIntent().getParcelableExtra("user");

        //PASO 2: Obtener la DB y la referencia
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("free_users");

        //PASO 3: Buscar en free_users, usuarios que coincidan
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                match = false;
                //Los datos de free_users quedan obtenidos en el parametro dataSnapshot

                //PASO 1: comprobamos que el dataSnapshot contiene hijos
                if(dataSnapshot.hasChildren()){

                    //PASO 2: si contiene hijos los recorremos en un bucle
                    for(DataSnapshot currentChildren : dataSnapshot.getChildren()){
                        User currentUser = currentChildren.getValue(User.class);
                        Log.e("USER", currentUser.toString());
                        /*PASO 3: comprobamos si alguno coincide con nuestra busqueda
                        * para ello utilizamos el sistema de codigos (searchCode)*/
                        /**
                         * Code 1 - code 1 y 5
                         * Code 2 - code 2 y 6
                         * Code 3 - code 4 y 5
                         * code 4 - code 3 y 6
                         * Code 5 - 1,3,6
                         * Code 6 - 2,4,5
                         */

                        //ANTES DE NADA COMPROBAMOS QUE NO SEA EL MISMO USUARIO EL QUE ENCUENTRA
                        if(currentUser.getId() != user.getId()){
                            switch (user.getSearchCode()){
                                case 1:
                                    if(currentUser.getSearchCode() == 1 || currentUser.getSearchCode() == 5)
                                        //PASO 4: si hemos hecho match entonces creamos una nueva chat_room

                                        //PASO 5: guardamos nuestros users en la chat_room

                                        //PASO 6: borramos el user de free_user
                                        match = true;
                                    break;
                                case 2:
                                    if(currentUser.getSearchCode() == 2 || currentUser.getSearchCode() == 6)
                                        match = true;
                                    break;
                                case 3:
                                    if(currentUser.getSearchCode() == 4 || currentUser.getSearchCode() == 5)
                                        match = true;
                                    break;
                                case 4:
                                    if(currentUser.getSearchCode() == 3 || currentUser.getSearchCode() == 6)
                                        match = true;
                                    break;
                                case 5:
                                    if(currentUser.getSearchCode() == 1 || currentUser.getSearchCode() == 3 || currentUser.getSearchCode() == 6)
                                        match = true;
                                    break;
                                case 6:
                                    if(currentUser.getSearchCode() == 2 || currentUser.getSearchCode() == 4 || currentUser.getSearchCode() == 5)
                                        match = true;
                                    break;
                            }
                        }
                    }
                }else{
                    //De lo contrario lanzamos un mensaje para indicar que no hay users
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
