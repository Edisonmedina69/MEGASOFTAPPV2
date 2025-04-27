package py.edison.megasoftappv2.interfaces;

import java.util.List;
import py.edison.megasoftappv2.entidades.Flete;

public interface FiltroFleteCallback {
    void onFiltrosAplicados(List<Flete> fletesFiltrados);
    void onFiltroError(String error);
}