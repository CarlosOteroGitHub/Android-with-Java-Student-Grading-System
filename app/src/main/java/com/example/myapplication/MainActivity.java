package com.example.myapplication;

/*

Author: ING. CARLOS OTERO RAMÍREZ

 */

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Calificaciones de Alumnos");

        button1 = findViewById(R.id.AM1_id1);
        button1.setOnClickListener(this);
        button1.setEnabled(true);
        button2 = findViewById(R.id.AM1_id2);
        button2.setOnClickListener(this);
        button2.setEnabled(true);
    }

    public void dialogoAlerta(String titulo, String mensaje){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(mensaje)
                .setTitle(titulo)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int posicion) {
                        dialog.cancel();
                    }
                });
        dialog.show();
    }

    public void agregarCalificaciones(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.form_signin, null);

        final EditText editText1 = v.findViewById(R.id.FS_id1);
        editText1.setHint("Introduce el nombre del alumno:");

        final EditText editText2 = v.findViewById(R.id.FS_id2);
        editText2.setHint("Introduce la calificación no.1:");

        final EditText editText3 = v.findViewById(R.id.FS_id3);
        editText3.setHint("Introduce la calificación no.2:");

        final EditText editText4 = v.findViewById(R.id.FS_id4);
        editText4.setHint("Introduce la calificación no.3:");

        final EditText editText5 = v.findViewById(R.id.FS_id5);
        editText5.setHint("Introduce la calificación no.4:");

        dialog.setView(v)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int posicion) {
                        dialog.dismiss();
                        String nombre  = editText1.getText().toString(), cal1 = editText2.getText().toString(), cal2 = editText3.getText().toString(), cal3 = editText4.getText().toString(), cal4 = editText5.getText().toString();
                        float suma = 0, arreglo[] = new float[4];
                        if(nombre.isEmpty() && cal1.isEmpty() && cal2.isEmpty() && cal3.isEmpty() && cal4.isEmpty()) {
                            dialogoAlerta("Mensaje de Error", "Los campos de texto están vacios.");
                        } else if(nombre.isEmpty() || cal1.isEmpty() || cal2.isEmpty() || cal3.isEmpty() || cal4.isEmpty()) {
                            dialogoAlerta("Mensaje de Error", "Alguno de los campos de texto están vacios.");
                        } else if(Float.parseFloat(cal1) < 0 || Float.parseFloat(cal1) > 100 && Float.parseFloat(cal2) < 0 || Float.parseFloat(cal2) > 100 && Float.parseFloat(cal3) < 0 || Float.parseFloat(cal3) > 100 && Float.parseFloat(cal4) < 0 || Float.parseFloat(cal4) > 100){
                            dialogoAlerta("Mensaje de Error", "Las calificaciones no pueden ser menor que cero, ni mayor a cien.");
                        } else {
                            arreglo[0] = Float.parseFloat(cal1);
                            arreglo[1] = Float.parseFloat(cal2);
                            arreglo[2] = Float.parseFloat(cal3);
                            arreglo[3] = Float.parseFloat(cal4);
                            for(int i = 0; i < arreglo.length; i++){
                                suma += arreglo[i];
                            }

                            float promedio = suma / 4;
                            if (promedio < 70) {
                                dialogoAlerta("Mensaje de Información", "El Alumno: " + nombre + ", a Reprobado el Curso, con un Promedio de: " + promedio);
                            } else if (promedio >= 70 && promedio < 90) {
                                dialogoAlerta("Mensaje de Información", "El Alumno: " + nombre + ", a Aprobado el Curso, con un Promedio de: " + promedio);
                            } else {
                                dialogoAlerta("Mensaje de Información", "El Alumno: " + nombre + ", a Aprobado el Curso con Excelencia, Obteniendo Un Promedio de: " + promedio);
                            }
                            dialogoAlerta("Las Calificaciones que Obtuvo Fueron:", ImprimirCalificaciones(arreglo));
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int posicion) {
                        dialog.cancel();
                    }
                });
        dialog.show();
    }

    public String ImprimirCalificaciones(float[] arreglo) {
        String guardar = "";
        for (int i = 0; i < arreglo.length; i++) {
            guardar += arreglo[i] + ", ";
        }
        return guardar;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AM1_id1:
                agregarCalificaciones();
                break;
            case R.id.AM1_id2:
                System.exit(0);
                break;
        }
    }
}