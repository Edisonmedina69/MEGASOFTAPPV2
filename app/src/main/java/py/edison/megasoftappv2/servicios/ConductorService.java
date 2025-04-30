package py.edison.megasoftappv2.servicios;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import py.edison.megasoftappv2.entidades.Conductor;
import py.edison.megasoftappv2.interfaces.ConductorListCallback;

public class ConductorService {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference conductoresRef = db.collection("conductores");

    public void obtenerConductores(ConductorListCallback callback) {
        conductoresRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Conductor> conductores = new ArrayList<>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Conductor conductor = doc.toObject(Conductor.class);
                    conductor.setId(doc.getId());
                    conductores.add(conductor);
                }
                callback.onSuccess(conductores);
            } else {
                callback.onError(task.getException() != null ?
                        task.getException().getMessage() : "Error desconocido");
            }
        });
    }

    public void guardarConductor(Conductor conductor, ConductorListCallback callback) {
        if (conductor.getId() == null || conductor.getId().isEmpty()) {
            conductoresRef.add(conductor)
                    .addOnSuccessListener(documentReference -> {
                        conductor.setId(documentReference.getId());
                        callback.onSuccess(List.of(conductor));
                    })
                    .addOnFailureListener(e -> callback.onError(e.getMessage()));
        } else {
            conductoresRef.document(conductor.getId())
                    .set(conductor)
                    .addOnSuccessListener(aVoid -> callback.onSuccess(List.of(conductor)))
                    .addOnFailureListener(e -> callback.onError(e.getMessage()));
        }
    }

    public void eliminarConductor(String id, ConductorListCallback callback) {
        conductoresRef.document(id).delete()
                .addOnSuccessListener(aVoid -> callback.onSuccess(new ArrayList<>()))
                .addOnFailureListener(e -> callback.onError(e.getMessage()));
    }
}