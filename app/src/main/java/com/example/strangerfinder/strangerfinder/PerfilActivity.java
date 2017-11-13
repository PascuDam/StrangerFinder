package com.example.strangerfinder.strangerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strangerfinder.strangerfinder.Models.Usuario;

public class PerfilActivity extends AppCompatActivity {

    private Button btnComenzar;
    private Usuario usuario;
    private TextView txtNombre;
    private RadioGroup rgSexo;
    private RadioGroup rgPreferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnComenzar = (Button) findViewById(R.id.btnComenzar);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        rgPreferencia = (RadioGroup) findViewById(R.id.rgPreferencia);

        usuario = new Usuario();

        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbHombre){
                    usuario.setSexo(1);
                }else if (checkedId == R.id.rbMujer){
                    usuario.setSexo(0);
                }

            }
        });

        rgPreferencia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbPrefHombre){
                    usuario.setPreferencia(1);
                }else if (checkedId == R.id.rbPrefMujer){
                    usuario.setPreferencia(0);
                }else if (checkedId == R.id.rbPrefAmbos){
                    usuario.setPreferencia(2);
                }

            }
        });

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobamos el formulario
                if(txtNombre.getText().toString().equals("") || usuario.getSexo()==-1 || usuario.getPreferencia() == -1){
                    Toast.makeText(getApplicationContext(), "Hay campos sin rellenar", Toast.LENGTH_LONG).show();
                } else{


                    // comenzamos el chat
                    Intent intent = new Intent(PerfilActivity.this, ChatActivity.class);
                    //intent.putExtra("persona",Usuario);
                    startActivity(intent);

                }



            }
        });

    }
}
