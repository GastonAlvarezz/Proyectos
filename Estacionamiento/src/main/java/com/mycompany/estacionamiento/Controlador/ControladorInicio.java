/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estacionamiento.Controlador;

/**
 *
 * @author gasto
 */

import com.mycompany.estacionamiento.Vista.VistaInicio;
import com.mycompany.estacionamiento.Vista.estacionaGUI;
import com.mycompany.estacionamiento.Vista.LoginEmpleado;
import com.mycompany.estacionamiento.vista.RegistroDiario;

public class ControladorInicio {

    private VistaInicio vista;

    public ControladorInicio(VistaInicio vista) {
        this.vista = vista;
    }

    public void mostrarRegistroDiario() {
        RegistroDiario registro = new RegistroDiario();
        registro.setVisible(true);
        vista.dispose();
    }

    public void mostrarEstacionaGUI() {
        estacionaGUI estacionar = new estacionaGUI();
        estacionar.setVisible(true);
        vista.dispose();
    }

    public void mostrarLoginEmpleado() {
        LoginEmpleado login = new LoginEmpleado();
        login.setVisible(true);
        vista.dispose();
    }

    public void cerrarAplicacion() {
        System.exit(0);
    }
}
