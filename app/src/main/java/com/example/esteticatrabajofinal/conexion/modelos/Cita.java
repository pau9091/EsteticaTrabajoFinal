package com.example.esteticatrabajofinal.conexion.modelos;

import java.io.Serializable;

public class Cita implements Serializable {
    //para facilitar la conexion y el pase de objetos que guardamos implementamos Serializable
    private String email;
    private String sesion;
    private String fecha;
    private String fecha_agenda;
    //contructor vacio
    public Cita() {
    }
    //construcutor con todos los datos
    public Cita(String email, String sesion, String fecha, String fecha_agenda) {
        this.email = email;
        this.sesion = sesion;
        this.fecha = fecha;
        this.fecha_agenda = fecha_agenda;
    }
//GET A SET DE TODOS LOS CAMPOS
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_agenda() {
        return fecha_agenda;
    }

    public void setFecha_agenda(String fecha_agenda) {
        this.fecha_agenda = fecha_agenda;
    }
}
