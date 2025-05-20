/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.estacionamiento.modelo;
/**
 *
 * @author gasto
 */


public class Usuario {
    private String dni;
    private String usuario;
    private String celular;
    private String vehiculo;
    private String patente;
    private String estacionamiento;

    // Constructor, getters y setters
    public Usuario(String dni, String usuario, String celular, String vehiculo, String patente, String estacionamiento) {
        this.dni = dni;
        this.usuario = usuario;
        this.celular = celular;
        this.vehiculo = vehiculo;
        this.patente = patente;
        this.estacionamiento = estacionamiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(String estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
}
