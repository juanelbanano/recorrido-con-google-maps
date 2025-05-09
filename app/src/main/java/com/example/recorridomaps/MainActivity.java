package com.example.recorridomaps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Coordenadas de puntos del recorrido de la mascota
    private final String origen = "4.631876,-74.066213";       // Por ejemplo: Parque Nacional
    private final String destino = "4.634123,-74.064000";      // Por ejemplo: Tienda de mascotas
    private final String waypoints = "4.632339,-74.065350|4.635000,-74.062000"; // Puntos intermedios: fuente, veterinaria

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Enlaza esta actividad con su diseño XML

        // Referencia al botón en la interfaz
        Button btnRuta = findViewById(R.id.btnRuta);

        // Asocia la acción del botón al método para abrir Google Maps
        btnRuta.setOnClickListener(this::abrirRutaConGoogleMaps);
    }

    // Este método se ejecuta cuando el usuario pulsa el botón
    public void abrirRutaConGoogleMaps(View view) {
        // Obtiene el modo de transporte elegido por el usuario desde el Spinner
        Spinner spinnerModo = findViewById(R.id.spinnerModo);
        String modo = spinnerModo.getSelectedItem().toString(); // "walking", "driving" o "bicycling"

        // Construye la URI para abrir la ruta en Google Maps con los parámetros definidos
        Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1"
                + "&origin=" + origen
                + "&destination=" + destino
                + "&waypoints=" + waypoints
                + "&travelmode=" + modo); // Usa el modo elegido

        // Crea un intent implícito para ver la URI en una app compatible (Google Maps)
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps"); // Especifica usar solo Google Maps

        // Verifica que haya una app que pueda manejar el intent (Maps debe estar instalado)
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent); // Abre Google Maps con la ruta trazada
        } else {
            // Si Maps no está instalado, muestra un mensaje al usuario
            Toast.makeText(this, "Google Maps no está instalado", Toast.LENGTH_SHORT).show();
        }
    }
}

