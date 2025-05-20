/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estacionamiento.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/ESTACIONA";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para obtener la conexión
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para insertar datos en las tablas usuarios y RegistroDiario
    public static void insertarDatos(String dni, String usuario, String celular, String vehiculo, String patente, String estacionamientoDocente, String estacionamientoAlumno) {
        Connection con = null;
        PreparedStatement psUsuarios = null;
        PreparedStatement psRegistroDiario = null;

        try {
            con = getConexion();

            // Inserción en la tabla usuarios
            String sqlUsuarios = "INSERT INTO usuarios (dni, Usuario, celular, modelo_vehiculo, patente_vehiculo, num_estacionamiento_docente, num_estacionamiento_alumno) VALUES (?, ?, ?, ?, ?, ?, ?)";
            psUsuarios = con.prepareStatement(sqlUsuarios);
            psUsuarios.setString(1, dni);
            psUsuarios.setString(2, usuario);
            psUsuarios.setString(3, celular);
            psUsuarios.setString(4, vehiculo);
            psUsuarios.setString(5, patente);
            psUsuarios.setString(6, estacionamientoDocente);
            psUsuarios.setString(7, estacionamientoAlumno);
            psUsuarios.executeUpdate();

            // Inserción en la tabla RegistroDiario
            String sqlRegistroDiario = "INSERT INTO RegistroDiario (dni, Usuario, patente_vehiculo, modelo_vehiculo, num_estacionamiento, celular, FechayHora) VALUES (?, ?, ?, ?, ?, ?, NOW())";
            psRegistroDiario = con.prepareStatement(sqlRegistroDiario);
            psRegistroDiario.setString(1, dni);
            psRegistroDiario.setString(2, usuario);
            psRegistroDiario.setString(3, patente);
            psRegistroDiario.setString(4, vehiculo);
            psRegistroDiario.setString(5, estacionamientoAlumno);
            psRegistroDiario.setString(6, celular);
            psRegistroDiario.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar PreparedStatement y Connection en el bloque finally para asegurar que se cierren siempre
            if (psUsuarios != null) {
                try {
                    psUsuarios.close();
                } catch (SQLException e) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (psRegistroDiario != null) {
                try {
                    psRegistroDiario.close();
                } catch (SQLException e) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    // Método de ejemplo para obtener estacionamientos asignados
    public static ArrayList<String> obtenerEstacionamientosAsignados() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
