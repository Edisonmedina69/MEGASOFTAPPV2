package py.edison.megasoftappv2.servicios;

import org.junit.*;
import py.edison.megasoftappv2.entidades.ConfiguracionTarifa;
import static org.junit.Assert.*;

public class FleteServiceTest {
    private FleteService service;
    private ConfiguracionTarifa tarifa;

    @Before
    public void setUp() {
        service = new FleteService();
        tarifa = new ConfiguracionTarifa(10.0, 5.0, 20.0); // $10/km, $5/kg, 20% recargo
    }

    @Test
    public void testCalculoPrecioNormal() {
        double precio = service.calcularPrecioFlete(100, 50, tarifa, false);
        assertEquals(1250.0, precio, 0.001); // (100*10 + 50*5)
    }

    @Test
    public void testCalculoPrecioUrgente() {
        double precio = service.calcularPrecioFlete(100, 50, tarifa, true);
        assertEquals(1500.0, precio, 0.001); // 1250 + 20%
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDistanciaNegativa() {
        service.calcularPrecioFlete(-100, 50, tarifa, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPesoNegativo() {
        service.calcularPrecioFlete(100, -50, tarifa, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTarifaNula() {
        service.calcularPrecioFlete(100, 50, null, false);
    }
}