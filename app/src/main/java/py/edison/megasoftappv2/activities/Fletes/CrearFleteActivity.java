package py.edison.megasoftappv2.activities.Fletes;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import py.edison.megasoftappv2.R;
import py.edison.megasoftappv2.databinding.ActivityCrearFleteBinding;
import py.edison.megasoftappv2.entidades.Flete;
import py.edison.megasoftappv2.servicios.FleteService;

public class CrearFleteActivity extends AppCompatActivity {

    private ActivityCrearFleteBinding binding;
    private FleteService fleteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearFleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fleteService = new FleteService();

        binding.btnGuardar.setOnClickListener(v -> {
            if (validarCampos()) {
                guardarFlete();
            }
        });
    }

    private boolean validarCampos() {
        boolean valido = true;

        if (binding.etOrigen.getText().toString().trim().isEmpty()) {
            binding.tilOrigen.setError("Ingrese el origen");
            valido = false;
        } else {
            binding.tilOrigen.setError(null);
        }

        if (binding.etDestino.getText().toString().trim().isEmpty()) {
            binding.tilDestino.setError("Ingrese el destino");
            valido = false;
        } else {
            binding.tilDestino.setError(null);
        }

        try {
            Double.parseDouble(binding.etDistancia.getText().toString());
            binding.tilDistancia.setError(null);
        } catch (NumberFormatException e) {
            binding.tilDistancia.setError("Distancia inválida");
            valido = false;
        }

        // Validaciones similares para peso y tarifa...

        return valido;
    }

    private void guardarFlete() {
        Flete nuevoFlete = new Flete();
        nuevoFlete.setOrigen(binding.etOrigen.getText().toString());
        nuevoFlete.setDestino(binding.etDestino.getText().toString());
        nuevoFlete.setDistancia(Double.parseDouble(binding.etDistancia.getText().toString()));
        nuevoFlete.setPeso(Double.parseDouble(binding.etPeso.getText().toString()));
        nuevoFlete.setTarifa(Double.parseDouble(binding.etTarifa.getText().toString()));
        nuevoFlete.setEstado(Flete.PENDIENTE);
        nuevoFlete.setUrgente(binding.cbUrgente.isChecked());

        fleteService.crearFlete(nuevoFlete, new FleteService.FleteCallback() {
            @Override
            public void onSuccess(Flete flete) {
                Toast.makeText(CrearFleteActivity.this, "Flete creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(CrearFleteActivity.this, "Error al crear flete: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}