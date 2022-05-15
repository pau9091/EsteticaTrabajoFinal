package com.example.esteticatrabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.esteticatrabajofinal.conexion.AdminSQLiteOpenHelper;
import com.example.esteticatrabajofinal.conexion.untils.Utilidades;

public class MainActivity extends AppCompatActivity {
    EditText usr, contraseña; //campos a llenar




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usr = (EditText) findViewById(R.id.usr);
        contraseña = (EditText) findViewById(R.id.password);



    }

    //boton para validacion
    public void Login(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"bd_usuarios",null,1);
        String u=usr.getText().toString();
        String p=contraseña.getText().toString();
        if (TextUtils.isEmpty(u) || TextUtils.isEmpty(p))
            Toast.makeText(MainActivity.this,"contraseña u usuario incorrecto", Toast.LENGTH_SHORT).show();
        else{
            Boolean validacion =admin.validacionpass(u,p);
            if (validacion==true){
                Toast.makeText(MainActivity.this,"inicio correcto", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(this,Home.class);
                startActivity(intent);
            }
        }
    }
    public  void Registro(View view){
        Intent intent= new Intent(this,Registro.class);
        startActivity(intent);
    }


}
