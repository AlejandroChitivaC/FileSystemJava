package co.edu.unbosque.model;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Archivo {
    private static DecimalFormat df = new DecimalFormat("#.##");

    // Método para obtener el tamaño de un archivo en bytes, kilobytes y megabytes
    public static void obtenerTamanioArchivo(File archivo) {
        df.setRoundingMode(RoundingMode.DOWN);
        double s = archivo.length();
        String size = df.format(archivo.length());
        double kb = s / 1000;
        double megabytes = s / (1024 * 1024);
        String mb = df.format(megabytes);

        JOptionPane.showMessageDialog(null, "El tamaño del archivo es: " + size + " bytes");
        JOptionPane.showMessageDialog(null, "El tamaño del archivo es: " + kb + " Kilobytes");
        JOptionPane.showMessageDialog(null, "El tamaño del archivo es: " + mb + " Megabytes");
    }
    private static void convertirArchivoCSV(String archivoTxt, String archivoCsv) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoTxt));
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
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer o escribir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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

    // Método para procesar un archivo CSV y contar los delitos por diferentes categorías
    public static void contarDelitos(File archivo) {
        Scanner scanner;
        try {
            scanner = new Scanner(archivo);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
            return;
        }
    }

    public void ejecutarBatEnCarpeta(String rutaCarpeta, String nombreArchivoBat) {
        try {
            // Construir el comando para ejecutar el archivo bat en la carpeta especificada
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", nombreArchivoBat);
            pb.directory(new File(rutaCarpeta));

            // Iniciar el proceso
            Process proceso = pb.start();
            JOptionPane.showMessageDialog(null, "El proceso .bat se ha iniciado");
            System.out.println(proceso.info());

            // Esperar a que termine el proceso
            proceso.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}