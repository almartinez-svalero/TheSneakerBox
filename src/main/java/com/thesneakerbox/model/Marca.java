package com.thesneakerbox.model;

import java.time.LocalDate;

public class Marca {

    private int id;
    private String nombre;
    private String pais;
    private boolean premium;
    private LocalDate fechaCreacion;
    private String logo;

    public Marca() {
    }

    public Marca(int id, String nombre, String pais,
                 boolean premium,
                 LocalDate fechaCreacion,
                 String logo) {

        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.premium = premium;
        this.fechaCreacion = fechaCreacion;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", premium=" + premium +
                ", fechaCreacion=" + fechaCreacion +
                ", logo='" + logo + '\'' +
                '}';
    }
}