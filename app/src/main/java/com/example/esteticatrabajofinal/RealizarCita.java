package com.example.esteticatrabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RealizarCita extends AppCompatActivity {
    / Constantes
    final int STARTHOUR = 9;
    final String[] SESSION_TYPE =  {"Pedicura","Corte", "Colorimetría del cabello", "Manicura", "Maquillaje y peinado"};

    // Adaptador para spinner
    ArrayList<String> spinnerArray;
    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_cita);
         textViewType = findViewById(R.id.textViewType);
        textViewDate = findViewById(R.id.textViewDate);
        textViewTime = findViewById(R.id.textViewTime); 
        // Colocar tipo de sesión
        fillTypes();
        // Colocar fechas en spinner
        fillDates();
        // Colocar horas en spinner
        fillTime();
    }
    // Método para guardar la fecha agendada
    public void buttonSave(View view) {
        if (spinnerValidations()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog);
            builder.setTitle(R.string.confirm_date);
            builder.setMessage(getDialogText());
            builder.setPositiveButton("Guardar", (dialog, which) -> {
                // Hacer cosas aqui al hacer clic en el boton de agendar
                try {
                    long resultado = insertDate();
                    if (resultado != -1) {
                        Intent intent = new Intent(this, Home.class);
                        startActivity(intent);
                        Toast.makeText(this, "Cita guardada correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Ya existe una cita reservada a esa fecha y hora", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, e + "", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("Atrás", (dialog, which) -> {

            });
            builder.show();
        }
    }

    private long insertDate() {
        SharedPreferences sharedPref = this.getSharedPreferences("correo_electronico", Context.MODE_PRIVATE);
        String email = sharedPref.getString(getString(R.string.email), "");

        String type = spinnerType.getSelectedItem().toString().trim();
        String date = spinnerDate.getSelectedItem().toString().trim() + " " + spinnerTime.getSelectedItem().toString().trim();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID_EMAIL, correo_electronico);
        values.put(Utilidades.CAMPO_TIPO_SESION, tipo_sesion);
        values.put(Utilidades.CAMPO_FECHA_CITA, fecha_cita);

        long id = db.insert(Utilidades.TABLA_CITAS, Utilidades.CAMPO_ID_EMAIL, values);

        db.close();

        return id;
    }

    private String getDialogText() {
        String type = spinnerType.getSelectedItem().toString().trim();
        String date = spinnerDate.getSelectedItem().toString().trim();
        String time = spinnerTime.getSelectedItem().toString().trim();

        return "Sesión: " + type + "\n" +
                "Fecha: " + date + "\n" +
                "Hora: " + time;
    }

    private boolean spinnerValidations() {

        if (spinnerType.getSelectedItem().toString().trim().equals("<Selecciona un tipo de servicio>")) {
            textViewType.setVisibility(View.VISIBLE);
        } else {
            textViewType.setVisibility(View.GONE);
        }

        if (spinnerDate.getSelectedItem().toString().trim().equals("<Selecciona una fecha>")) {
            textViewDate.setVisibility(View.VISIBLE);
        } else {
            textViewDate.setVisibility(View.GONE);
        }

        if (spinnerTime.getSelectedItem().toString().trim().equals("<Selecciona una hora>")) {
            textViewTime.setVisibility(View.VISIBLE);
        } else {
            textViewTime.setVisibility(View.GONE);
        }

        return textViewType.getVisibility() != View.VISIBLE && textViewDate.getVisibility() != View.VISIBLE && textViewTime.getVisibility() != View.VISIBLE;
    }

    // Métodos privados
    // Método que llena los tipos de sesión
    private void fillTypes() {
        spinnerArray = new ArrayList<>();

        spinnerArray.add("<Selecciona un tipo de servicio>");

        Collections.addAll(spinnerArray, SESSION_TYPE);

        spinnerType = findViewById(R.id.spinnerType);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                custom_spinner,
                spinnerArray
        );
        adapter.setDropDownViewResource(R.layout.menupersonalizado);
        spinnerType.setAdapter(adapter);

    }

    // Método que llena las fechas
    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void fillDates() {
        Calendar calendar = Calendar.getInstance();
        spinnerArray = new ArrayList<>();

        spinnerArray.add("<Selecciona una fecha>");

        int monthNumber = calendar.get(Calendar.MONTH);

        int i = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, 1);

        while (i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            String date = twoDigits(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + twoDigits(monthNumber + 1) + "/" + calendar.get(Calendar.YEAR);
            calendar.add(Calendar.DATE, 1);
            spinnerArray.add(date);
            i++;
        }

        spinnerDate = findViewById(R.id.spinnerDate);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                custom_spinner,
                spinnerArray
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerDate.setAdapter(adapter);

    }

    // Método que llena las horas
    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void fillTime() {
        spinnerArray = new ArrayList<>();

        spinnerArray.add("<Selecciona una hora>");

        for (int i = STARTHOUR; i <= 20; i++) {
            String hour = format("%02d", i) + ":00 hrs";
            spinnerArray.add(hour);
        }

        spinnerTime = findViewById(R.id.spinnerTime);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                custom_spinner,
                spinnerArray
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerTime.setAdapter(adapter);

    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}
