package py.edison.megasoftappv2.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import py.edison.megasoftappv2.entidades.Conductor;

public class ConductorAdapter extends RecyclerView.Adapter<ConductorAdapter.ConductorViewHolder> {
    private List<Conductor> conductores;

    public ConductorAdapter(List<Conductor> conductores) {
        this.conductores = conductores;
    }

    @NonNull
    @Override
    public ConductorAdapter.ConductorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ConductorAdapter.ConductorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    // ... (implementar métodos como en ClientAdapter)
}