package py.edison.megasoftappv2;

import android.util.Log;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.firebase.database.DatabaseError;
import org.junit.Test;
import org.junit.runner.RunWith;
import py.edison.megasoftappv2.entidades.Client;
import py.edison.megasoftappv2.utils.FirebaseDatabaseHelper;

@RunWith(AndroidJUnit4.class)
public class FirebaseDatabaseHelperTest {

    @Test
    public void testAgregarCliente() {
        FirebaseDatabaseHelper dbHelper = new FirebaseDatabaseHelper();
        Client testClient = new Client("cli001", "Juan Pérez", "0981122334", "juan@mail.com");

        dbHelper.agregarCliente(testClient, (error, ref) -> {
            if (error != null) {
                Log.e("FirebaseTest", "Error al agregar cliente", error.toException());
                assert false; // Falla la prueba
            } else {
                Log.d("FirebaseTest", "Cliente agregado con ID: " + testClient.getId());
                assert true; // Prueba exitosa
            }
        });

        // Pequeña pausa para esperar la operación async
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}