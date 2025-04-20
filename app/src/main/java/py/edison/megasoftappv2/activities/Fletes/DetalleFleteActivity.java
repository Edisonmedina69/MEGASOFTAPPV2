package py.edison.megasoftappv2.activities.Fletes;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import py.edison.megasoftappv2.databinding.ActivityDetalleFleteBinding;
import py.edison.megasoftappv2.entidades.Flete;
import py.edison.megasoftappv2.servicios.FleteService;

public class DetalleFleteActivity extends AppCompatActivity {

    private ActivityDetalleFleteBinding binding;
    private FleteService fleteService;
    private Flete flete;
    private boolean modoEdicion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleFleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String fleteId = getIntent().getStringExtra("FLETE_ID");
        fleteService = new FleteService();

        configurarVistas();
        cargarFlete(fleteId);
    }

    private void configurarVistas() {
        binding.btnEditar.setOnClickListener(v -> {
            if (modoEdicion) {
                guardarCambios();
            } else {
                habilitarEdicion();
            }
        });
        deshabilitarEdicion();
    }

    private void habilitarEdicion() {
        modoEdicion = true;
        binding.btnEditar.setText("Guardar");

        binding.etOrigen.setEnabled(true);
        binding.etDestino.setEnabled(true);
        binding.etDistancia.setEnabled(true);
        binding.etPeso.setEnabled(true);
        binding.etTarifa.setEnabled(true);
        binding.etEstado.setEnabled(true);
        binding.cbUrgente.setEnabled(true);
    }

    private void deshabilitarEdicion() {
        modoEdicion = false;
        binding.btnEditar.setText("Editar");

        binding.etOrigen.setEnabled(false);
        binding.etDestino.setEnabled(false);
        binding.etDistancia.setEnabled(false);
        binding.etPeso.setEnabled(false);
        binding.etTarifa.setEnabled(false);
        binding.etEstado.setEnabled(false);
        binding.cbUrgente.setEnabled(false);
    }

    private void cargarFlete(String id) {
        fleteService.obtenerFlete(id, new FleteService.FleteCallback() {
            @Override
            public void onSuccess(Flete fleteCargado) {
                flete = fleteCargado;
                mostrarDatos();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(DetalleFleteActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarDatos() {
        binding.etOrigen.setText(flete.getOrigen());
        binding.etDestino.setText(flete.getDestino());
        binding.etDistancia.setText(String.valueOf(flete.getDistancia()));
        binding.etPeso.setText(String.valueOf(flete.getPeso()));
        binding.etTarifa.setText(String.valueOf(flete.getTarifa()));
        binding.etEstado.setText(flete.getEstado());
        binding.cbUrgente.setChecked(flete.isUrgente());
    }

    private void guardarCambios() {
        try {
            flete.setOrigen(binding.etOrigen.getText().toString());
            flete.setDestino(binding.etDestino.getText().toString());
            flete.setDistancia(Double.parseDouble(binding.etDistancia.getText().toString()));
            flete.setPeso(Double.parseDouble(binding.etPeso.getText().toString()));
            flete.setTarifa(Double.parseDouble(binding.etTarifa.getText().toString()));
            flete.setEstado(binding.etEstado.getText().toString());
            flete.setUrgente(binding.cbUrgente.isChecked());

            fleteService.actualizarFlete(flete, new FleteService.FleteCallback() {
                @Override
                public void onSuccess(Flete fleteActualizado) {
                    Toast.makeText(DetalleFleteActivity.this, "Flete actualizado", Toast.LENGTH_SHORT).show();
                    deshabilitarEdicion();
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(DetalleFleteActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error en formatos numéricos", Toast.LENGTH_SHORT).show();
        }
    }
}