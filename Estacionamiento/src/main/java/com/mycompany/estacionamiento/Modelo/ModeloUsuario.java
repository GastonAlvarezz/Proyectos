/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estacionamiento.Modelo;

/**
 *
 * @author gasto
 */



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ModeloUsuario {

    private Connection con;

    public ModeloUsuario() throws SQLException {
        con = Conexion.getConexion();
    }

    public ResultSet obtenerUsuarios() {
        String sql = "SELECT dni, Usuario, celular, patente_vehiculo, modelo_vehiculo, num_estacionamiento FROM usuarios";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al recuperar datos de la base de datos: " + ex.getMessage());
            return null;
        }
    }

    public ResultSet buscarUsuarioPorEstacionamiento(String estacionamiento) {
        String sql = "SELECT dni, Usuario, celular, patente_vehiculo, modelo_vehiculo, num_estacionamiento FROM usuarios WHERE num_estacionamiento = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estacionamiento);
            return ps.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar usuarios por estacionamiento: " + ex.getMessage());
            return null;
        }
    }

    public boolean modificarEstacionamiento(String dni, String estacionamiento) {
        try {
            con.setAutoCommit(false);

            String sqlUsuarios = "UPDATE usuarios SET num_estacionamiento = ? WHERE dni = ?";
            PreparedStatement psUsuarios = con.prepareStatement(sqlUsuarios);
            psUsuarios.setString(1, estacionamiento);
            psUsuarios.setString(2, dni);
            int resultadoUsuarios = psUsuarios.executeUpdate();

            String sqlRegistroDiario = "UPDATE registrodiario SET num_estacionamiento = ? WHERE dni = ?";
            PreparedStatement psRegistroDiario = con.prepareStatement(sqlRegistroDiario);
            psRegistroDiario.setString(1, estacionamiento);
            psRegistroDiario.setString(2, dni);
            int resultadoRegistroDiario = psRegistroDiario.executeUpdate();

            if (resultadoUsuarios > 0 && resultadoRegistroDiario > 0) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException rollbackEx) {
                // Manejar el rollbackEx
            }
            JOptionPane.showMessageDialog(null, "Error al modificar estacionamiento: " + ex.getMessage());
            return false;
        }
    }

    public boolean desocuparEstacionamiento(String dni) {
        String sql = "DELETE FROM usuarios WHERE dni = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al desocupar espacio: " + ex.getMessage());
            return false;
        }
    }
}

