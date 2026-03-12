package com.examen.salones;

public class Salon {

    private int id;
    private String nombre;
    private int capacidad;
    private String edificio;

    public Salon() {}

    public Salon(int id, String nombre, int capacidad, String edificio) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.edificio = edificio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }
}