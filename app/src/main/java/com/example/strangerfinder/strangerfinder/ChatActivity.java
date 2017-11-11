package com.example.strangerfinder.strangerfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.strangerfinder.strangerfinder.Models.Usuario;


public class ChatActivity extends AppCompatActivity {

    Usuario usuario;
    Usuario extraño;
    TextView nombre;
    EditText txtTexto;
    Button btnEnviar;
    Button btnRecibir;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        txtTexto = (EditText) findViewById(R.id.txtTexto);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        // Este boton es de prueba para ver como se representan los mensajes con otro estilo
        btnRecibir = (Button) findViewById(R.id.btnRecibir);



    }
}
