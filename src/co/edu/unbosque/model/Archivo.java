package co.edu.unbosque.model;

import co.edu.unbosque.view.Vista;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Archivo {
    File txtFile = new File("src/archivo-1.txt");

    public static Vista view = new Vista();
    private static DecimalFormat df = new DecimalFormat("#.##");

    // Método para obtener el tamaño de un archivo en bytes, kilobytes y megabytes
    public void getFileSize(File archivo) {

        df.setRoundingMode(RoundingMode.DOWN);
        double s = archivo.length();
        String size = df.format(archivo.length());
        double kb = s / 1000;
        double megabytes = s / (1024 * 1024);
        String mb = df.format(megabytes);

        view.showMsj("El tamaño del archivo es: " + size + " bytes");
        view.showMsj("El tamaño del archivo es: " + kb + " Kilobytes");
        view.showMsj("El tamaño del archivo es: " + mb + " Megabytes");
    }
    public static File createCsv(File txtFile, File csvFile) {
        try (BufferedReader lector = new BufferedReader(new FileReader(txtFile));
             FileWriter writer = new FileWriter(csvFile)) {
            // Leer cada línea del archivo de texto y escribir en el archivo CSV
            String linea;
            while ((linea = lector.readLine()) != null) {
                // Dividir la línea en campos utilizando el separador adecuado
                String[] campos = linea.split(",");
                // Escribir los campos en el archivo CSV
                for (int i = 0; i < campos.length; i++) {
                    writer.append(campos[i]);
                    view.consoleMsj(linea);
                    if (i < campos.length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");

            }
            view.showMsj("El archivo se ha convertido a CSV correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            view.showError("Error al leer o escribir el archivo: " + e.getMessage());
        }
        return csvFile;
    }

    // Método para procesar un archivo CSV y contar los delitos por diferentes categorías
    public static void contarDelitos(File archivo) {
        Scanner scanner;
        try {
            scanner = new Scanner(archivo);
        } catch (FileNotFoundException e) {
            view.showError("El archivo no existe.");
            return;
        }
    }

    public void runBatOnFolder(String folderPath, String fileName) {
        try {
            // Construir el comando para ejecutar el archivo bat en la carpeta especificada
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", fileName);
            pb.directory(new File(folderPath));

            // Iniciar el proceso
            Process proceso = pb.start();
            JOptionPane.showMessageDialog(null, "El proceso .bat se ha iniciado");
            view.consoleMsj(proceso.info().toString());

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

    public File getTxtFile() {
        return txtFile;
    }

    public void setTxtFile(File txtFile) {
        this.txtFile = txtFile;
    }


}