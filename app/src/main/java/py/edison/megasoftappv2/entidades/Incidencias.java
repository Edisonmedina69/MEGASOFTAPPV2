package py.edison.megasoftappv2.entidades;

import java.util.Date;

public class Incidencias {
    private String id;
    private String fleteId;
    private String descripcion;
    private Date fecha;
    private String gravedad; // Leve, Moderada, Grave
    private boolean resuelta;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFleteId() {
        return fleteId;
    }

    public void setFleteId(String fleteId) {
        this.fleteId = fleteId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public boolean isResuelta() {
        return resuelta;
    }

    public void setResuelta(boolean resuelta) {
        this.resuelta = resuelta;
    }
}