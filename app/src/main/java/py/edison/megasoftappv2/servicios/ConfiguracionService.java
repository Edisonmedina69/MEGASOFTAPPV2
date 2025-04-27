package py.edison.megasoftappv2.servicios;

import py.edison.megasoftappv2.entidades.ConfiguracionTarifa;

public class ConfiguracionService {
    private ConfiguracionTarifa tarifaDefault = new ConfiguracionTarifa(10.0, 5.0, 20.0);

    public ConfiguracionTarifa getTarifaActual() {
        return tarifaDefault;
    }
}