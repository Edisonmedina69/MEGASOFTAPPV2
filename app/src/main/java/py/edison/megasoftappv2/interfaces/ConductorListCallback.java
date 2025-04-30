package py.edison.megasoftappv2.interfaces;

import java.util.List;
import py.edison.megasoftappv2.entidades.Conductor;

public interface ConductorListCallback {
    void onSuccess(List<Conductor> conductores);
    void onError(String errorMessage);
}