/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estacionamiento.Controlador;

/**
 *
 * @author gasto
 */

import com.mycompany.estacionamiento.Vista.LoginEmpleado;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControladorLoginEmpleado {

    private LoginEmpleado vista;

    public ControladorLoginEmpleado(LoginEmpleado vista) {
        this.vista = vista;
    }

    public void irAlInicio() {
        vista.mostrarVistaInicio();
    }

    public void iniciarSesion(String usuario, String contrasena) throws SQLException {
        if (verificarCredenciales(usuario, contrasena)) {
            vista.mostrarVistaEmpleado();
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos");
        }
    }

    private boolean verificarCredenciales(String usuario, String contrasena) {
        // Consultar las credenciales predeterminadas en la base de datos
        String usuarioPredeterminado = "admin";
        String contrasenaPredeterminada = "123";

        // Verificar si las credenciales ingresadas coinciden con las predeterminadas
        return usuario.equals(usuarioPredeterminado) && contrasena.equals(contrasenaPredeterminada);
    }
}
