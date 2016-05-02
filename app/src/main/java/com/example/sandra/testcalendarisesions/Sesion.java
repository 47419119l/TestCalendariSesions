package com.example.sandra.testcalendarisesions;

/**
 * Created by sandra on 02/05/2016.
 */
public class Sesion {

    private String id;
    private String data;
    private String hora;
    private int places;
    private int nAsesitents;
    private String monitor;
    private String nom;

    public Sesion(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getnAsesitents() {
        return nAsesitents;
    }

    public void setnAsesitents(int nAsesitents) {
        this.nAsesitents = nAsesitents;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }
}
