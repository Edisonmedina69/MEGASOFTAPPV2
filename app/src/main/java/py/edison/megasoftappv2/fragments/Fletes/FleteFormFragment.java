    package py.edison.megasoftappv2.fragments.Fletes;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.Toast;

    import androidx.fragment.app.Fragment;

    import py.edison.megasoftappv2.R;
    import py.edison.megasoftappv2.entidades.ConfiguracionTarifa;
    import py.edison.megasoftappv2.entidades.Flete;
    import py.edison.megasoftappv2.servicios.FleteService;

    public class FleteFormFragment extends Fragment {
        private EditText etOrigen, etDestino, etDistancia, etPeso, etTarifa;
        private CheckBox cbUrgente;
        private FleteService fleteService;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_flete_form, container, false);

            // Inicializar vistas
            inicializarVistas(view);

            fleteService = new FleteService();

            return view;
        }

        private void inicializarVistas(View view) {
            etOrigen = view.findViewById(R.id.etOrigen);
            etDestino = view.findViewById(R.id.etDestino);
            etDistancia = view.findViewById(R.id.etDistancia);
            etPeso = view.findViewById(R.id.etPeso);
            etTarifa = view.findViewById(R.id.etTarifa);
            cbUrgente = view.findViewById(R.id.cbUrgente);

            view.findViewById(R.id.btnGuardarFlete).setOnClickListener(v -> guardarFlete());
        }

        private void guardarFlete() {
            if (validarCampos()) {
                Flete nuevoFlete = new Flete();
                nuevoFlete.setOrigen(etOrigen.getText().toString());
                // ... (setear otros campos)

                // Calcular tarifa automática
                ConfiguracionTarifa tarifa = configuracionService.obtenerTarifaActual();
                double precio = fleteService.calcularPrecioFlete(
                        nuevoFlete.getDistancia(),
                        nuevoFlete.getPeso(),
                        tarifa,
                        nuevoFlete.isUrgente()
                );
                nuevoFlete.setTarifa(precio);

                fleteService.crearFlete(nuevoFlete, new FleteService.FleteCallback() {
                    @Override
                    public void onSuccess(Flete flete) {
                        Toast.makeText(getContext(), "¡Flete creado!", Toast.LENGTH_SHORT).show();
                        // Regresar a la lista
                        getParentFragmentManager().popBackStack();
                    }
                    // ... manejo de errores
                });
            }
        }
    }