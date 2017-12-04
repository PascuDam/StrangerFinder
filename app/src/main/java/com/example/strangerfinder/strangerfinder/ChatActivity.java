package com.example.strangerfinder.strangerfinder;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.strangerfinder.strangerfinder.Helpers.ChatArrayAdapter;
import com.example.strangerfinder.strangerfinder.Models.ChatRoom;
import com.example.strangerfinder.strangerfinder.Models.Mensaje;
import com.example.strangerfinder.strangerfinder.Models.ReceivedMessage;
import com.example.strangerfinder.strangerfinder.Models.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.lw_messages)
    ListView lvMessages;
    @BindView(R.id.bt_send)
    FloatingActionButton btSend;
    @BindView(R.id.et_message)
    EditText et_message;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.bt_next)
    Button btNext;
    ChatRoom room;
    User user;
    private String strangeName;
    private ChatArrayAdapter chatArrayAdapter;
    private DatabaseReference root;
    private DatabaseReference messagesSection;
    private DatabaseReference users;
    private String userGone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        DatabaseReference freeUsers;
        DatabaseReference myKey;

        //PASO 1: Obtener el user, la sala y el nombre del compañero
        user = getIntent().getParcelableExtra("user");
        user.setWantToChange(Boolean.FALSE);
        room = getIntent().getParcelableExtra("room");
        strangeName = getIntent().getExtras().getString("stranger");
        tv_name.setText(tv_name.getText().toString().concat(strangeName));

        //PASO 2: Obtener la referencia de la BD de la sala y creamos la seccion de mensajes
        root = FirebaseDatabase.getInstance().getReference("chats_room").child(room.getName());
        root.child("messages_section");
        messagesSection = root.child("messages_section");

        //Introducir los usuarios en la chat_room
        users = root.child("users");
        users.child("user1").setValue(user.getName());
        users.child("user2").setValue(strangeName);

        //PASO 3: Borramos al usuario de "free_users"
        freeUsers = FirebaseDatabase.getInstance().getReference("free_users");
        myKey = freeUsers.child(user.getKey());
        myKey.removeValue();

        //PASO 4: Preparamos el chat
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.msg_izquierda);
        lvMessages.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        lvMessages.setAdapter(chatArrayAdapter);

        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                lvMessages.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

        //Funcion para enviar el mensaje
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                //PASO 1: Creamos un mapa para gestionar un mensaje en FireBase
                Map<String, Object> map = new HashMap<>();

                messagesSection = root.child("messages_section");
                //PASO 2: Creamos una clave autogenerada para el mensaje
                String rndKey = messagesSection.push().getKey();

                //PASO 3: Añadimos la clave autogenerada a la sala
                messagesSection.updateChildren(map);

                //PASO 4: Obtenemos el root del mensaje nuevo a travez de la clave generada
                DatabaseReference dirMensaje = messagesSection.child(rndKey);

                //PASO 5: Creamos un mapa para el mensaje, indicando el nombre del usuario y el mensaje
                Map<String, Object> map2 = new HashMap<String, Object>();

                map2.put("user", user.getName());
                map2.put("message", et_message.getText().toString());

                //PASO 6: Llevamos el mensaje a la base de datos
                dirMensaje.updateChildren(map2);

                //PASO 7: Mostramos el mensaje en el chat (lado derecho)
                enviarMensaje();
            }
        });

        //Funcion que se ejecuta al modificar la referencia de la sala en la BD
        messagesSection.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                recibirMensaje(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                recibirMensaje(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*Método para saber si el otro usuario ha abandonado la sala de chat, de ser asi
        * inmediatemanete te manda al loookingfor activity para emparearte con otro usuario*/
        users.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                if(user.getName().equals(userGone)){
                    //PASO 1: conectar con firebase
                    DatabaseReference waiting_room = root.child("waiting_room");

                    //PASO 2: subir al usuario a chat_room
                    DatabaseReference userKey = waiting_room.push();
                    userKey.setValue(user);
                    user.setKey(userKey.getKey());

                    Intent intent = new Intent(ChatActivity.this, WaitingActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Botón para buscar otra persona con la que hablar(volvemos al looking for)
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setWantToChange(Boolean.TRUE);
                userGone = user.getName();

                //Borrar al usuario de la chat_room
                users.child("user1").removeValue();

                Intent intent = new Intent(ChatActivity.this, LookingForActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }

    /**
     * Funcion para mostrar el mensaje enviado en el chat
     */

    private void enviarMensaje() {
        //PASO 1: Comprobamos si el EditText esta vacio
        if(!et_message.getText().toString().equals("")){
            //PASO 2: Añadimos un mensaje a la lista a la derecha
            chatArrayAdapter.add(new Mensaje(false, et_message.getText().toString()));
            et_message.setText("");
        }
    }

    /**
     * Funcion para traer el mensaje del extraño y mostrarlo
     * @param dataSnapshot
     */
    private void recibirMensaje(DataSnapshot dataSnapshot){

        //PASO 1: Mapeamos el ultimo mensaje
        ReceivedMessage msg = dataSnapshot.getValue(ReceivedMessage.class);

        //PASO 2: Comprobamos si el ultimo mensaje no lo ha escrito el usuario
        if(!user.getName().equals(msg.getUser())){
            //PASO 3: Mostramos el mensaje a la izquierda
            chatArrayAdapter.add(new Mensaje(true, msg.getMessage()));
        }
    }

}

