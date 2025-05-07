package py.edison.megasoftappv2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import py.edison.megasoftappv2.entidades.Client;

// ClientAdapter.java mejorado
public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {
    private List<Client> clients;
    private ItemClickListener clickListener;

    @NonNull
    @Override
    public ClientAdapter.ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientAdapter.ClientViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface ItemClickListener {
        void onItemClick(Client client);
        void onEditClick(Client client);
        void onDeleteClick(Client client);
    }

    // Constructor y métodos del ViewHolder...

    public void setOnItemClickListener(ItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.tvNombre.setText(client.getNombre());
        holder.tvTelefono.setText(client.getTelefono());

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) clickListener.onItemClick(client);
        });
    }

    @Override
    public int getItemCount() {
        return clients != null ? clients.size() : 0;
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvTelefono;
        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
        }
    }
}
