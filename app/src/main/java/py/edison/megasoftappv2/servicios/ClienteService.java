package py.edison.megasoftappv2.servicios;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import py.edison.megasoftappv2.entidades.Client;
import py.edison.megasoftappv2.interfaces.ClienteListCallback;

public class ClienteService {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference clientesRef = db.collection("clientes");

    public void obtenerClientes(ClienteListCallback callback) {
        clientesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Client> clientes = new ArrayList<>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Client cliente = doc.toObject(Client.class);
                    cliente.setId(doc.getId());
                    clientes.add(cliente);
                }
                callback.onSuccess(clientes);
            } else {
                callback.onError(task.getException() != null ? task.getException().getMessage() : "Error desconocido");
            }
        });
    }

    public void guardarCliente(Client cliente, ClienteListCallback callback) {
        if (cliente.getId() == null || cliente.getId().isEmpty()) {
            clientesRef.add(cliente)
                    .addOnSuccessListener(documentReference -> {
                        cliente.setId(documentReference.getId());
                        callback.onSuccess(List.of(cliente));
                    })
                    .addOnFailureListener(e -> callback.onError(e.getMessage()));
        } else {
            clientesRef.document(cliente.getId())
                    .set(cliente)
                    .addOnSuccessListener(aVoid -> callback.onSuccess(List.of(cliente)))
                    .addOnFailureListener(e -> callback.onError(e.getMessage()));
        }
    }

    public void eliminarCliente(String id, ClienteListCallback callback) {
        clientesRef.document(id).delete()
                .addOnSuccessListener(aVoid -> callback.onSuccess(new ArrayList<>()))
                .addOnFailureListener(e -> callback.onError(e.getMessage()));
    }

    public void actualizarCliente(Client cliente, ClienteListCallback callback) {
        if (cliente.getId() == null || cliente.getId().isEmpty()) {
            callback.onError("ID de cliente inválido");
            return;
        }

        clientesRef.document(cliente.getId())
                .set(cliente)
                .addOnSuccessListener(aVoid -> callback.onSuccess(Collections.singletonList(cliente)))
                .addOnFailureListener(e -> callback.onError(e.getMessage()));
    }
}