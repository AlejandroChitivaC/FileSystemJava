package co.edu.unbosque.controller;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class AplMain {

    public static void main(String[] args) {

        try {
            File archivo = new File("src/archivo-1.txt");
            // formatting the decimals to 2 places
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.DOWN);
            double s = archivo.length();
            String size = df.format(archivo.length());
            double kb = s / 1000;
            double megabytes = s / (1024 * 1024);
            String mb = df.format(megabytes);

            Scanner scanner = new Scanner(archivo);

            JOptionPane.showMessageDialog(null, "El tamaño del archivo es: " + size + " bytes");
            JOptionPane.showMessageDialog(null, "El tamaño del archivo es: " + kb + " Kilobytes");
            JOptionPane.showMessageDialog(null, "El tamaño del archivo es: " + mb + " Megabytes");
            scanner.close();
            //Archivo CSV de Salida
            String archivoCsv = "archivo-1.csv";
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo));
                 FileWriter writer = new FileWriter(archivoCsv)) {
                // Leer cada línea del archivo de texto y escribir en el archivo CSV
                String linea;
                while ((linea = lector.readLine()) != null) {
                    // Dividir la línea en campos utilizando el separador adecuado
                    String[] campos = linea.split(",");

                    // Escribir los campos en el archivo CSV
                    for (int i = 0; i < campos.length; i++) {
                        writer.append(campos[i]);
                        if (i < campos.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
                JOptionPane.showMessageDialog(null, "El archivo se ha convertido a CSV correctamente.");
//                //Cargar el archivo CSV
//                File archivoCSV = new File("archivo-1.csv");
//                Scanner scannerCSV = new Scanner(archivoCSV);
//                while (scannerCSV.hasNextLine()) {
//                    String lineaCSV = scannerCSV.nextLine();
//                    System.out.println(lineaCSV);
//                }
//                scannerCSV.close();

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer o escribir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontró.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}





