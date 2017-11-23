package com.example.strangerfinder.strangerfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.strangerfinder.strangerfinder.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                        User user = currentChildren.getValue(User.class);

                        //PASO 3: comprobamos si alguno coincide con nuestra busqueda



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
