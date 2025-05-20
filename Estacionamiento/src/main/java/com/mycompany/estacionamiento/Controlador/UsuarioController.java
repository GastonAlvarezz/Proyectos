package com.mycompany.estacionamiento.Controlador;

import com.mycompany.estacionamiento.Vista.estacionaGUI;
import com.mycompany.estacionamiento.modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UsuarioController {

    private estacionaGUI vista;
    private ArrayList<String> lugaresDocentes;
    private ArrayList<String> lugaresAlumnos;

    public UsuarioController(estacionaGUI vista) {
        this.vista = vista;
        this.lugaresDocentes = new ArrayList<>();
        this.lugaresAlumnos = new ArrayList<>();
    }

    public void inicializarLugares(JComboBox<String> jComboBox1, JComboBox<String> jComboBox2) {
        for (int i = 1; i <= 20; i++) {
            lugaresDocentes.add("D" + i);
        }

        for (int i = 1; i <= 40; i++) {
            lugaresAlumnos.add("A" + i);
        }

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(lugaresDocentes.toArray(new String[0])));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(lugaresAlumnos.toArray(new String[0])));
    }

   public void cargarUsuario(JTextField txtDNI, JTextField txtUsuario, JTextField txtCelular, JTextField txtVehiculo, JTextField txtPatente, JComboBox<String> jComboBox1, JComboBox<String> jComboBox2, JTable jTable1) {
    String dni = txtDNI.getText();
    String usuario = txtUsuario.getText();
    String celular = txtCelular.getText();
    String vehiculo = txtVehiculo.getText();
    String patente = txtPatente.getText();
    String estacionamiento = "";

    if (vista.getBtnEstudiante().isEnabled()) {
        estacionamiento = (String) jComboBox1.getSelectedItem();
        lugaresAlumnos.remove(estacionamiento);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(lugaresAlumnos.toArray(new String[0])));
    } else {
        if (vista.getBtnDocente().isEnabled()) {
            estacionamiento = (String) jComboBox2.getSelectedItem();
            lugaresDocentes.remove(estacionamiento);
            jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(lugaresDocentes.toArray(new String[0])));
        }
    }

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/estaciona";
        Connection connect = DriverManager.getConnection(url, "root", "");

        try {
            String sqlUsuarios = "INSERT INTO usuarios(dni, usuario, celular, modelo_vehiculo, patente_vehiculo, num_estacionamiento) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstUsuarios = connect.prepareStatement(sqlUsuarios);
            pstUsuarios.setString(1, dni);
            pstUsuarios.setString(2, usuario);
            pstUsuarios.setString(3, celular);
            pstUsuarios.setString(4, vehiculo); 
            pstUsuarios.setString(5, patente);   
            pstUsuarios.setString(6, estacionamiento);
            pstUsuarios.executeUpdate();

            String sqlRegistroDiario = "INSERT INTO RegistroDiario(dni, usuario, patente_vehiculo, modelo_vehiculo, num_estacionamiento, celular, FechayHora) VALUES (?, ?, ?, ?, ?, ?, NOW())";
            PreparedStatement pstRegistroDiario = connect.prepareStatement(sqlRegistroDiario);
            pstRegistroDiario.setString(1, dni);
            pstRegistroDiario.setString(2, usuario);
            pstRegistroDiario.setString(3, patente);
            pstRegistroDiario.setString(4, vehiculo);
            pstRegistroDiario.setString(5, estacionamiento);
            pstRegistroDiario.setString(6, celular);
            pstRegistroDiario.executeUpdate();

            String[] row = {dni, usuario, celular, vehiculo, patente, estacionamiento};
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(row);
        } finally {
            connect.close();
        }
        vista.clearInputs();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        e.printStackTrace();
    }
}


    public void seleccionarEstudiante(JComboBox<String> jComboBox1, JComboBox<String> jComboBox2, javax.swing.JButton btnDocente) {
        jComboBox1.setVisible(true);
        jComboBox2.setVisible(false);
        btnDocente.setEnabled(true);
    }

    public void seleccionarDocente(JComboBox<String> jComboBox1, JComboBox<String> jComboBox2, javax.swing.JButton btnEstudiante) {
        jComboBox1.setVisible(false);
        jComboBox2.setVisible(true);
        btnEstudiante.setEnabled(true);
    }
}
