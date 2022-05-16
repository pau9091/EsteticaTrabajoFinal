package com.example.esteticatrabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {
   public TextView nomusuario;
   public ImageButton agendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        agendar=findViewById(R.id.agenda);
//agrege esto, para que tome el nombre del usuario
        nomusuario= findViewById(R.id.nomusuario);
        SharedPreferences sharedPref = this.getSharedPreferences("nombre", Context.MODE_PRIVATE);
        String nombre = sharedPref.getString(getString(R.string.usr), "");
        nomusuario.setText("BIENVENID@" + nombre);
    }
    //metodos para dirigir a los boton a su clase correspondiente
    public void agendar(View view){
       Intent intent=new Intent(this,RealizarCita.class);
        startActivity(intent);
    }
    public void citas(View view){
        Intent intent=new Intent(this,ver_citas.class);
        startActivity(intent);
    }
    public void mapa(View view){
        Intent intent=new Intent(this,MapFragment.class);
        startActivity(intent);
    }
    public void salir (View view){
        SharedPreferences sharedPref = this.getSharedPreferences("correo_electronico", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }
}
