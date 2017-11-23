package com.example.strangerfinder.strangerfinder;

import android.database.DataSetObserver;
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
import com.example.strangerfinder.strangerfinder.Models.User;



public class ChatActivity extends AppCompatActivity {

    User user;
    User extraño;
    TextView nombre;
    EditText txtTexto;
    Button btnEnviar;
    Button btnRecibir;

    private ListView listaMensajes;
    private ChatArrayAdapter chatArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listaMensajes = (ListView)findViewById(R.id.listaMensajes);
        txtTexto = (EditText) findViewById(R.id.txtTexto);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        // Este boton es de prueba para ver como se representan los mensajes con otro estilo
        btnRecibir = (Button) findViewById(R.id.btnRecibir);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.msg_izquierda);
        listaMensajes.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listaMensajes.setAdapter(chatArrayAdapter);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enviarMensaje();
            }
        });

        btnRecibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                recibirMensaje();
            }
        });


        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listaMensajes.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
    }

    private void enviarMensaje() {
        // Comprobamos si el EditText esta vacio
        if(!txtTexto.getText().toString().equals("")){
            // añadimos un mensaje a la lista a la derecha
            chatArrayAdapter.add(new Mensaje(false, txtTexto.getText().toString()));
            txtTexto.setText("");
        }
    }

    private void recibirMensaje(){
        //Comprobamos si el EditText esta vacio
        if(!txtTexto.getText().toString().equals("")){
            // añadimos un mensaje a la lista a la izquierda
            chatArrayAdapter.add(new Mensaje(true, txtTexto.getText().toString()));
            txtTexto.setText("");
        }
    }

}

