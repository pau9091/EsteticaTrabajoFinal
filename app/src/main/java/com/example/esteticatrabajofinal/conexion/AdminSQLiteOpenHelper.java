package com.example.esteticatrabajofinal.conexion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.esteticatrabajofinal.conexion.untils.Utilidades;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_USUARIO); //mandamos a llamar la creación de la tabla usuarios
        sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_CITAS); //mandamos a llamar la creación de tabla citas
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_USUARIO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_CITAS);
        onCreate(sqLiteDatabase);    }

    public  Boolean validacion(String correo_electronico){
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor cursor=bd.rawQuery("select * from usuario where correo_electronico=? and password=?", new String[]{correo_electronico});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;

    }
    public  Boolean validacionpass(String correo_electronico, String password){
        SQLiteDatabase bd=this.getWritableDatabase();
        Cursor cursor=bd.rawQuery("select * from usuario where correo_electronico=? and password=?", new String[]{correo_electronico,password});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;

    }
}
