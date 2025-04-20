package py.edison.megasoftappv2.adapters;

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
}