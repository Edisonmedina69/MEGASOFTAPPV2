package py.edison.megasoftappv2.servicios;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import py.edison.megasoftappv2.entidades.Conductor;

public class ConductorService {
    private DatabaseReference conductoresRef = FirebaseDatabase.getInstance().getReference("conductores");

    public void listarConductoresDisponibles(FleteService.FletesListCallback callback) {
        conductoresRef.orderByChild("disponible").equalTo(true)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Conductor> conductores = new ArrayList<>();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            conductores.add(data.getValue(Conductor.class));
                        }
                        callback.onSuccess(conductores);
                    }
                    // ... onCancelled
                });
    }
}