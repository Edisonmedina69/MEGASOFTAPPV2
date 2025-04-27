package py.edison.megasoftappv2.interfaces;

import py.edison.megasoftappv2.entidades.Conductor;

public interface ConductorOperaciones {
    void onConductorAsignado(Conductor conductor);
    void onDisponibilidadCambiada(boolean disponible);
}