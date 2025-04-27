package py.edison.megasoftappv2.interfaces;

public interface TarifaCalculador {
    double calcularTarifa(double distancia, double peso, boolean esUrgente);
}