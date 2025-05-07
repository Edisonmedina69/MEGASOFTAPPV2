package py.edison.megasoftappv2.incluides;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MyToolbar {

    public static void show(AppCompatActivity activity, String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) activity.findViewById(activity);
        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);
            Log.d("ToolbarDebug", "Toolbar configurado correctamente con título: " + title);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        } else {
            Log.e("ToolbarDebug", "Toolbar no encontrado en el layout.");
        }
    }


}

