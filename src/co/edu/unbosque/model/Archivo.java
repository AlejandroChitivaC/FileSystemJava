package co.edu.unbosque.model;

import co.edu.unbosque.view.Vista;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;


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
        try (BufferedReader lector = new BufferedReader(new FileReader(txtFile)); FileWriter writer = new FileWriter(csvFile)) {
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
    public void analizarArchivoDelitos() {

        // Crear un objeto File para representar el archivo CSV
        File archivo = new File("src/archivo-1.csv");

        // Crear un objeto Scanner para leer el archivo CSV
        Scanner scanner;
        try {
            scanner = new Scanner(archivo);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
            return;
        }


        // Crear un objeto HashMap para almacenar los contadores por género
        Map<String, Integer> contadoresGenero = new HashMap<>();
        Map<String, Integer> contadoresZona = new HashMap<>();
        Map<String, Integer> contadoresMes = new HashMap<>();
        Map<String, Integer> contadoresDepartamento = new HashMap<>();
        Map<String, Integer> contadoresEdad = new HashMap<>();
        Map<String, Integer> contadoresModalidad = new HashMap<>();
        int totalDelitos = 0;

        // Leer cada línea del archivo CSV
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            String[] campos = linea.split(",");
            totalDelitos++;
            if (campos.length == 13) {
                String genero = campos[8];
                String zona = campos[7];
                String mes = campos[2].split("/")[0];
                String departamento = campos[0];
                String edad = campos[11];
                String modalidad = campos[5];
                if (modalidad.equals("HALADO")) {
                    totalDelitos--;
                    continue;
                }
                if (modalidad.equals("MODALIDAD")) {
                    continue;
                }


                // Contar delitos por género
                Integer contadorGenero = contadoresGenero.getOrDefault(genero, 0);
                contadoresGenero.put(genero, contadorGenero + 1);

                // Contar delitos por zona
                Integer contadorZona = contadoresZona.getOrDefault(zona, 0);
                contadoresZona.put(zona, contadorZona + 1);

                // Contar delitos por mes
                Integer contadorMes = contadoresMes.getOrDefault(mes, 0);
                contadoresMes.put(mes, contadorMes + 1);

                // Contar delitos por departamento
                Integer contadorDepartamento = contadoresDepartamento.getOrDefault(departamento, 0);
                contadoresDepartamento.put(departamento, contadorDepartamento + 1);

                // Contar delitos por edad
                Integer contadorEdad = contadoresEdad.getOrDefault(edad, 0);
                contadoresEdad.put(edad, contadorEdad + 1);

                // Contar delitos por modalidad
                Integer contadorModalidad = contadoresModalidad.getOrDefault(modalidad, 0);
                contadoresModalidad.put(modalidad, contadorModalidad + 1);
            }
        }
        mostrarTabla(contadoresGenero, "Delitos por Género", "Género");
        mostrarTabla(contadoresZona, "Delitos por Zona", "Zona");
        mostrarTabla(contadoresMes, "Delitos por Mes", "Mes");
        mostrarTabla(contadoresDepartamento, "Delitos por Departamento", "Departamento");
        mostrarTabla(contadoresEdad, "Delitos por Edad", "Edad");
        mostrarTabla(contadoresModalidad, "Delitos por Modalidad", "Modalidad");
    }


    public void mostrarTabla(Map<String, Integer> datos, String titulo, String nombreColumna) {
        DecimalFormat df = new DecimalFormat("0.00");
        int totalDelitos = datos.values().stream().mapToInt(Integer::intValue).sum();
        String[] columnas = {nombreColumna, "Cantidad de Delitos", "Porcentaje (%)"};
        String[][] filas = new String[datos.size()][3];
        int i = 0;
        for (String categoria : datos.keySet()) {
            int cantidad = datos.get(categoria);
            double porcentaje = (double) cantidad / totalDelitos * 100;
            if (datos.equals(nombreColumna)) {
                continue;
            }
            String percent = df.format(porcentaje);
            filas[i][0] = categoria;
            filas[i][1] = Integer.toString(cantidad);
            filas[i][2] = percent;
            i++;
        }
        JTable tabla = new JTable(filas, columnas);
        JOptionPane.showMessageDialog(null, new JScrollPane(tabla), titulo, JOptionPane.PLAIN_MESSAGE);
    }


    public static void splitFile(String inputFile, String outputFolder, int numFiles) {
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            int numRecords = 1;
            for (int i = 1; i <= numFiles && scanner.hasNextLine(); i++) {
                String outputFile = outputFolder + File.separator + String.format("%02d.txt", i);
                try (PrintWriter writer = new PrintWriter(new File(outputFile))) {
                    for (int j = 0; j < numRecords && scanner.hasNextLine(); j++) {
                        String line = scanner.nextLine();
                        writer.println(line);
                    }
                    numRecords++;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void runBatOnFolder(String folderPath, String fileName) {
        try {
            // Construir el comando para ejecutar el archivo bat en la carpeta especificada
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", fileName);
            pb.directory(new File(folderPath));

            // Iniciar el proceso
            Process proceso = pb.start();
            view.showWarning("La ejecución de los procesos .bat han iniciado");

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