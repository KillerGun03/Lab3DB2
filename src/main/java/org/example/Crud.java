package org.example;

import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class Crud extends Conexion{

    public static void mostrarCitasMedicas(){

        Connection conexion = getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from vw_citaMedica;";

        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("ID");
                String FechaHora = rs.getString("FechaHora");
                String MotivoCita = rs.getString("MotivoCita");
                String Estado = rs.getString("Estado");
                String NombrePaciente = rs.getString("NombrePaciente");
                String NombreMedico = rs.getString("NombreMedico");

                System.out.println("Id de la cita: " + id +
                        "\nFecha y hora de la cita: " + FechaHora +
                        "\nMotivos de la cita: " + MotivoCita +
                        "\nEstado de la cita: " + Estado +
                        "\nNombre del paciente: " + NombrePaciente +
                        "\nNombre del medico: " + NombreMedico +
                        "\n---------------------------------------------------------------------\n");

            }

            ps.close();
            rs.close();
            conexion.close();

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error al agregar al modelo "+e);
        }
    }

    public static void agregarCitaMedica(){
        Connection conexion = getConnection();
        PreparedStatement ps;

        Random random = new Random();
        int medico = random.nextInt(3) + 1;

        String sql = "CALL InsertCitaMedica(DATE_ADD(NOW(), INTERVAL 5 DAY), ?, 'Pendiente', ?, ?);";

        try {
            ps = conexion.prepareStatement(sql);

            String MotivoCita = JOptionPane.showInputDialog("Escriba el motivo de su cita: ");
            int IdPaciente = Integer.parseInt(JOptionPane.showInputDialog("Escriba su ID de paciente: "));

            ps.setString(1, MotivoCita);
            ps.setInt(2, IdPaciente);
            ps.setInt(3, medico);

            ps.executeUpdate();
            ps.close();
            conexion.close();
            JOptionPane.showMessageDialog(null, "Registro Agregado");

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error al agregar al modelo "+e);
        }
    }

    public static void modificarEstadoCita() {

        Connection conexion = getConnection();
        PreparedStatement ps;

        String sql = "CALL UpdateCitaMedica(?);";

        try {
            ps = conexion.prepareStatement(sql);
            int ID = Integer.parseInt(JOptionPane.showInputDialog("Escriba el ID de la cita que desea modificar: "));

            ps.setInt(1, ID);

            ps.executeUpdate();
            conexion.close();
            JOptionPane.showMessageDialog(null, "Registro modificado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar registro "+e);
        }
    }

    public static void eliminarCitaMedica() {
        Connection conexion = getConnection();
        PreparedStatement ps;

        String sql = "delete from CitaMedica where ID = ?;";

        try {
            ps = conexion.prepareStatement(sql);
            int ID = Integer.parseInt(JOptionPane.showInputDialog("Escriba el ID de la cita que desea eliminar: "));

            ps.setInt(1, ID);

            ps.executeUpdate();
            conexion.close();
            JOptionPane.showMessageDialog(null, "Registro eleminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al elminar registro " + e);
        }

    }

    public static void GetRecetasPacienteMayor18() {

        Connection conexion = getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "CALL GetRecetasPacienteMayor18(?);";

        try {
            ps = conexion.prepareStatement(sql);
            int ID = Integer.parseInt(JOptionPane.showInputDialog("Escriba el ID del paciente: "));

            ps.setInt(1, ID);

            rs = ps.executeQuery();
            if (rs.next()) {
                int NumeroRecetas = rs.getInt("NumeroRecetas");
                JOptionPane.showMessageDialog(null, "El número de recetas es: " + NumeroRecetas);
            }
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar registro "+e);
        }
    }
}
