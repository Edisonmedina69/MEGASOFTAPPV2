package py.edison.megasoftappv2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import py.edison.megasoftappv2.R;
import py.edison.megasoftappv2.entidades.Conductor;

public class ConductorAdapter extends RecyclerView.Adapter<ConductorAdapter.ConductorViewHolder> {

    private List<Conductor> conductores;
    private OnConductorListener onConductorListener;

    public interface OnConductorListener {
        void onConductorClick(int position);
        void onConductorLongClick(int position);
    }

    public ConductorAdapter(List<Conductor> conductores, OnConductorListener onConductorListener) {
        this.conductores = conductores;
        this.onConductorListener = onConductorListener;
    }

    @NonNull
    @Override
    public ConductorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conductor, parent, false);
        return new ConductorViewHolder(view, onConductorListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConductorViewHolder holder, int position) {
        Conductor conductor = conductores.get(position);
        holder.tvNombre.setText(conductor.getNombre());
        holder.tvTelefono.setText(conductor.getTelefono());
    }

    @Override
    public int getItemCount() {
        return conductores.size();
    }

    public void actualizarLista(List<Conductor> nuevaLista) {
        conductores = nuevaLista;
        notifyDataSetChanged();
    }

    public static class ConductorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvNombre;
        TextView tvTelefono;
        OnConductorListener onConductorListener;

        public ConductorViewHolder(@NonNull View itemView, OnConductorListener onConductorListener) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            this.onConductorListener = onConductorListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onConductorListener.onConductorClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onConductorListener.onConductorLongClick(getAdapterPosition());
            return true;
        }
    }
}