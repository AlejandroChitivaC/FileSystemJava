package co.edu.unbosque.model;

import javax.swing.*;
import java.io.*;

public class Logica {
    // Método para convertir un archivo de texto a un archivo CSV
    public static void convertirATextoCSV(File archivo) {
        String archivoCsv = "src/archivo-1.csv";
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo));
             FileWriter writer = new FileWriter(archivoCsv)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                for (int i = 0; i < campos.length; i++) {
                    writer.append(campos[i]);
                    if (i < campos.length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            JOptionPane.showMessageDialog(null, "El archivo se ha convertido a CSV correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer o escribir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
