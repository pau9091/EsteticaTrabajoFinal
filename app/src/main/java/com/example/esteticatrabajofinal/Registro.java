package com.example.esteticatrabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.esteticatrabajofinal.conexion.AdminSQLiteOpenHelper;
import com.example.esteticatrabajofinal.conexion.untils.Utilidades;

public class Registro extends AppCompatActivity {
    public EditText editTextFirstName, editTextLastName, editTextDOB, editTextEmail, editTextPassword1, editTextPassword2, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextPassword1 = findViewById(R.id.editTextPassword1);
        editTextPhone = findViewById(R.id.editTextPhone);
    }
    public void buttonSignUp(View view) {
        long resultado = insertUser();
        if (resultado != -1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Usuario registrado!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show();
        }
    }
    public void buttonRegresar(View view){
        Intent intent= new Intent(this,Registro.class);
        startActivity(intent);

    }
    private long insertUser() {

        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "bd_usuarios", null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID_EMAIL, editTextEmail.getText().toString());
        values.put(Utilidades.CAMPO_PASSWORD, editTextPassword1.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, editTextFirstName.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDO, editTextLastName.getText().toString());
        values.put(Utilidades.CAMPO_FEC_NAC, editTextDOB.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, editTextPhone.getText().toString());

        long id = bd.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID_EMAIL, values);

        bd.close();

        return id;
    }
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 because January is zero
            final String selectedDate = twoDigits(day) + "/" + twoDigits(month + 1) + "/" + year;
            editTextDOB.setText(selectedDate);
        });

        newFragment.show (getSupportFragmentManager(), "datePicker");
    }
    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

}