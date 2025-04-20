package py.edison.megasoftappv2.servicios;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import py.edison.megasoftappv2.entidades.Flete;

public class FleteService {
    private final DatabaseReference fletesRef;
    private ValueEventListener activeListener;

    public FleteService() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true); // Habilita persistencia offline
        this.fletesRef = database.getReference("fletes");
    }

    public interface FleteCallback {
        void onSuccess(Flete flete);
        void onError(String error);
    }

    public interface FletesListCallback {
        void onSuccess(List<Flete> fletes);
        void onError(String error);
    }

    public void crearFlete(Flete flete, FleteCallback callback) {
        DatabaseReference newRef = fletesRef.push();
        flete.setId(newRef.getKey());

        newRef.setValue(flete)
                .addOnSuccessListener(aVoid -> callback.onSuccess(flete))
                .addOnFailureListener(e -> callback.onError(e.getMessage()));
    }

    public void obtenerFlete(String id, FleteCallback callback) {
        fletesRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Flete flete = snapshot.getValue(Flete.class);
                if (flete != null) {
                    flete.setId(snapshot.getKey());
                    callback.onSuccess(flete);
                } else {
                    callback.onError("Flete no encontrado");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.getMessage());
            }
        });
    }

    public void listarFletes(FletesListCallback callback) {
        limpiarListeners();
        activeListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Flete> fletes = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Flete flete = data.getValue(Flete.class);
                    if (flete != null) {
                        flete.setId(data.getKey());
                        fletes.add(flete);
                    }
                }
                callback.onSuccess(fletes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.getMessage());
            }
        };
        fletesRef.addValueEventListener(activeListener);
    }

    public void actualizarFlete(Flete flete, FleteCallback callback) {
        if (flete.getId() == null) {
            callback.onError("El flete no tiene ID");
            return;
        }

        fletesRef.child(flete.getId()).setValue(flete)
                .addOnSuccessListener(aVoid -> callback.onSuccess(flete))
                .addOnFailureListener(e -> callback.onError(e.getMessage()));
    }

    public void filtrarPorEstado(String estado, FletesListCallback callback) {
        limpiarListeners();
        activeListener = fletesRef.orderByChild("estado")
                .equalTo(estado)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Flete> fletes = new ArrayList<>();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Flete flete = data.getValue(Flete.class);
                            if (flete != null) {
                                flete.setId(data.getKey());
                                fletes.add(flete);
                            }
                        }
                        callback.onSuccess(fletes);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callback.onError(error.getMessage());
                    }
                });
    }

    public void limpiarListeners() {
        if (activeListener != null) {
            fletesRef.removeEventListener(activeListener);
        }
    }
}