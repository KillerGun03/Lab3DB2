package org.example;

import javax.swing.*;

public class Main extends Crud{



    public static void main(String[] args) {

        int opcion;

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione la accion que desea realizar: " +
                    "\n1. Agregar cita medica" +
                    "\n2. Modificar el estado de una cita medica" +
                    "\n3. Eliminar una cita medica" +
                    "\n4. Mostrar todas las citas medicas" +
                    "\n5. Mostrar numero de recetas de pacientes mayores a 18 años" +
                    "\n0. Salir"));

            switch (opcion){
                case 1 -> agregarCitaMedica();
                case 2 -> modificarEstadoCita();
                case 3 -> eliminarCitaMedica();
                case 4 -> mostrarCitasMedicas();
                case 5 -> GetRecetasPacienteMayor18();
            }
        } while (opcion != 0);

    }
}