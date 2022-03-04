package org.izv.flora.model.entity;

public class Imagen {

    public long id, idflora;
    public String nombre, descr;

    public Imagen(long id, long idflora, String nombre, String descr) {
        this.id = id;
        this.idflora = idflora;
        this.nombre = nombre;
        this.descr = descr;
    }

    public Imagen() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdflora() {
        return idflora;
    }

    public void setIdflora(long idflora) {
        this.idflora = idflora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", idflora=" + idflora +
                ", name='" + nombre + '\'' +
                ", descr='" + descr + '\'' +
                '}';
    }
}
