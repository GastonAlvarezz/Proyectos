/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estacionamiento.Controlador;

/**
 *
 * @author gasto
 */



import com.mycompany.estacionamiento.Modelo.ModeloUsuario;
import com.mycompany.estacionamiento.Vista.VistaEmpleado;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorEmpleado {

    private VistaEmpleado vista;
    private ModeloUsuario modelo;

    public ControladorEmpleado(VistaEmpleado vista) throws SQLException {
        this.vista = vista;
        this.modelo = new ModeloUsuario();
    }

    public void mostrarUsuarios() {
        ResultSet rs = modelo.obtenerUsuarios();
        DefaultTableModel model = (DefaultTableModel) vista.getTable().getModel();
        model.setRowCount(0);

        try {
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Usuario"),
                    rs.getString("dni"),
                    rs.getString("celular"),
                    rs.getString("patente_vehiculo"),
                    rs.getString("modelo_vehiculo"),
                    rs.getString("num_estacionamiento")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
        }
    }

    public void buscarUsuarioPorEstacionamiento(String estacionamiento) {
        ResultSet rs = modelo.buscarUsuarioPorEstacionamiento(estacionamiento);
        DefaultTableModel model = (DefaultTableModel) vista.getTable().getModel();
        model.setRowCount(0);

        try {
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Usuario"),
                    rs.getString("dni"),
                    rs.getString("celular"),
                    rs.getString("patente_vehiculo"),
                    rs.getString("modelo_vehiculo"),
                    rs.getString("num_estacionamiento")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
        }
    }

    public void modificarEstacionamiento(String dni, String estacionamiento) {
    try {
        // Extrae solo los números de la cadena de estacionamiento
        String estacionamientoSoloNumeros = estacionamiento.replaceAll("[^0-9]", "");

        int num_estacionamiento = Integer.parseInt(estacionamientoSoloNumeros);

        if (num_estacionamiento > 60) {
            vista.mostrarMensaje("Error el número de estacionamiento es hasta el 60");
            return;
        }
    } catch (NumberFormatException e) {
        vista.mostrarMensaje("Error el número de estacionamiento no es válido");
        return;
    }

    boolean resultado = modelo.modificarEstacionamiento(dni, estacionamiento);
    if (resultado) {
        vista.mostrarMensaje("Estacionamiento modificado correctamente");
        mostrarUsuarios();
    } else {
        vista.mostrarMensaje("No se pudo modificar el estacionamiento");
    }
}

    

    public void desocuparEstacionamiento(String dni, int fila) {
        boolean resultado = modelo.desocuparEstacionamiento(dni);
        if (resultado) {
            vista.mostrarMensaje("Usuario desocupado y eliminado correctamente.");
            DefaultTableModel model = (DefaultTableModel) vista.getTable().getModel();
            model.removeRow(fila);
        } else {
            vista.mostrarMensaje("No se pudo desocupar el espacio.");
        }
    }
}
