package py.edison.megasoftappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import py.edison.megasoftappv2.R;
import py.edison.megasoftappv2.activities.Clientes.GestionClientesActivity;
import py.edison.megasoftappv2.activities.Fletes.GestionFletesActivity;
import py.edison.megasoftappv2.activities.Vehiculos.GestionVehiculosActivity;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase.getInstance().setPersistenceEnabled(true); // Opcional para offline
    DatabaseReference fletesRef = FirebaseDatabase.getInstance().getReference("fletes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar listeners para los botones principales
        findViewById(R.id.btnGestionFletes).setOnClickListener(v -> {
            startActivity(new Intent(this, GestionFletesActivity.class));
        });

        findViewById(R.id.btnGestionClientes).setOnClickListener(v -> {
            startActivity(new Intent(this, GestionClientesActivity.class));
        });

        findViewById(R.id.btnGestionVehiculos).setOnClickListener(v -> {
            startActivity(new Intent(this, GestionVehiculosActivity.class));
        });
    }
}