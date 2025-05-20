/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estacionamiento.Controlador;

/**
 *
 * @author gasto
 */

import com.mycompany.estacionamiento.vista.RegistroDiario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.mycompany.estacionamiento.Modelo.Conexion;

public class RegistroDiarioController {

    private RegistroDiario vista;

    public RegistroDiarioController(RegistroDiario vista) {
        this.vista = vista;
    }

    public void mostrarUsuarios(JTable jTable1) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            String sql = "SELECT dni, Usuario, patente_vehiculo, modelo_vehiculo, num_estacionamiento, celular, FechayHora FROM RegistroDiario";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                    rs.getString("Usuario"),
                    rs.getString("dni"),
                    rs.getString("celular"),
                    rs.getString("modelo_vehiculo"),
                    rs.getString("patente_vehiculo"),
                    rs.getString("num_estacionamiento"),
                    rs.getString("FechayHora")
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al recuperar datos de la base de datos: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void buscarUsuario(String criterio, JTable jTable1) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            String sql = "SELECT dni, Usuario, patente_vehiculo, modelo_vehiculo, num_estacionamiento, celular, FechayHora FROM RegistroDiario WHERE num_estacionamiento = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, criterio);
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                    rs.getString("Usuario"),
                    rs.getString("dni"),
                    rs.getString("celular"),
                    rs.getString("modelo_vehiculo"),
                    rs.getString("patente_vehiculo"),
                    rs.getString("num_estacionamiento"),
                    rs.getString("FechayHora")
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al recuperar datos de la base de datos: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void borrarTodo(JTable jTable1) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.getConexion();
            String sql = "DELETE FROM RegistroDiario";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            JOptionPane.showMessageDialog(null, "Todos los registros han sido borrados.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar datos de la base de datos: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

