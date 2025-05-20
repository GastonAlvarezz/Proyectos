package com.mycompany.estacionamiento;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gasto
 */



import com.mycompany.estacionamiento.Vista.VistaInicio;
import com.mycompany.estacionamiento.Modelo.Conexion;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Estacionamiento {

    private static JTextField txtDNI;
    private static JTextField txtUsuario;
    private static JTextField txtVehiculo;
    private static JTextField txtPatente;
    private static JComboBox<String> listAlum;
    private static JComboBox<String> listDoc;

    public static void main(String[] args) {

        // Configurar el aspecto Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VistaInicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Iniciar la aplicación
        java.awt.EventQueue.invokeLater(() -> {
            initComponents();
            VistaInicio inicio = new VistaInicio();
            inicio.setVisible(true);
        });
    }

    private static void initComponents() {
        txtDNI = new JTextField();
        txtUsuario = new JTextField();
        txtVehiculo = new JTextField();
        txtPatente = new JTextField();
        listAlum = new JComboBox<>();
        listDoc = new JComboBox<>();
    }

    // Método que inserta los datos en la base de datos
    public static void insertarDatos() {
        String dni = txtDNI.getText();
        String usuario = txtUsuario.getText();
        String vehiculo = txtVehiculo.getText();
        String patente = txtPatente.getText();
        String estacionamientoDocente = listDoc.getSelectedItem().toString();
        String estacionamientoAlumno = listAlum.getSelectedItem().toString();
        String celular = java.time.LocalDateTime.now().toString();

        Conexion.insertarDatos(dni, usuario, celular, vehiculo, patente, estacionamientoDocente, estacionamientoAlumno);
    }
}