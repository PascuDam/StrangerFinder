package com.example.strangerfinder.strangerfinder;

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

    String room;
    User user;

    private ChatArrayAdapter chatArrayAdapter;
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        user = getIntent().getParcelableExtra("user");
        room = getIntent().getExtras().getString("room").toString();


        // recogemos la refencia de la sala en la base de datos
        root = FirebaseDatabase.getInstance().getReference("chats_room").child(room);

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

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // mapa para gestionar un mensaje en FireBase
                Map<String, Object> map = new HashMap<String, Object>();

                // creamos una clave autogenerada para el mensaje
                String rndKey = root.push().getKey();

                // añadimos la clave autogenerada a la sala
                root.updateChildren(map);

                // Obtenemos el root del mensaje nuevo a travez de la clave generada
                DatabaseReference dirMensaje = root.child(rndKey);

                // creamos un mapa para el mensaje, indicando el nombre del usuario y el mensaje
                Map<String, Object> map2 = new HashMap<String, Object>();

                map2.put("user", user.getName());
                map2.put("message", et_message.getText().toString());

                // llevamos el mensaje a la base de datos
                dirMensaje.updateChildren(map2);

                // mostramos el mensaje en el chat (lado derecho)
                enviarMensaje();
            }
        });

        root.addChildEventListener(new ChildEventListener() {
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
    }

    private void enviarMensaje() {
        // Comprobamos si el EditText esta vacio
        if(!et_message.getText().toString().equals("")){
            // añadimos un mensaje a la lista a la derecha
            chatArrayAdapter.add(new Mensaje(false, et_message.getText().toString()));
            et_message.setText("");
        }
    }

    private void recibirMensaje(DataSnapshot dataSnapshot){

        // mapeamos el ultimo mensaje
        ReceivedMessage msg = dataSnapshot.getValue(ReceivedMessage.class);

        // si el ultimo mensaje no lo has escrito tu, entramos
        if(!user.getName().equals(msg.getUser())){
            // mostramos el mensaje a la izquierda
            chatArrayAdapter.add(new Mensaje(true, msg.getMessage()));
        }
    }

}

