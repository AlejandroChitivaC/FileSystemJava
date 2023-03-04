package co.edu.unbosque.model;

import co.edu.unbosque.view.Vista;

import java.awt.*;
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

        // Calcular el porcentaje de delitos por género y mostrar los resultados
        System.out.println("Delitos por género");
        for (String genero : contadoresGenero.keySet()) {
            int cantidad = contadoresGenero.get(genero);
            double porcentaje = (double) cantidad / totalDelitos * 100;
            String percent = df.format(porcentaje);
            System.out.println("Género: " + genero + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
        }
        System.out.println("-----------------------------------------------------------");

//        System.out.println("Delitos por zona");
//        for (String zona : contadoresZona.keySet()) {
//            String[] columnas = {"Edad", "Cantidad de Delitos", "Porcentaje (%)"};
//            String[][] datos = new String[contadoresEdad.size()][3];
//            int cantidad = contadoresZona.get(zona);
//            double porcentaje = (double) cantidad / totalDelitos * 100;
//            String percent = df.format(porcentaje);
//            System.out.println("Zona: " + zona + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
//            JTable tabla = new JTable(datos, columnas);
//            JOptionPane.showMessageDialog(null, new JScrollPane(tabla), "Delitos por Edad", JOptionPane.PLAIN_MESSAGE);
//        }
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("Delitos por mes");
//        for (String mes : contadoresMes.keySet()) {
//            int cantidad = contadoresMes.get(mes);
//            double porcentaje = (double) cantidad / totalDelitos * 100;
//            String percent = df.format(porcentaje);
//            System.out.println("Mes: " + mes + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
//        }
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("Delitos por departamento");
//        for (String departamento : contadoresDepartamento.keySet()) {
//            int cantidad = contadoresDepartamento.get(departamento);
//            double porcentaje = (double) cantidad / totalDelitos * 100;
//            String percent = df.format(porcentaje);
//            System.out.println("Departamento: " + departamento + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
//        }
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("Delitos por edad");
//        for (String edad : contadoresEdad.keySet()) {
//            int cantidad = contadoresEdad.get(edad);
//            double porcentaje = (double) cantidad / totalDelitos * 100;
//            String percent = df.format(porcentaje);
//            System.out.println("Edad: " + edad + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
//        }
//        System.out.println("-----------------------------------------------------------");
//        System.out.println("Delitos por modalidad");
//        for (String modalidad : contadoresModalidad.keySet()) {
//            int cantidad = contadoresModalidad.get(modalidad);
//            double porcentaje = (double) cantidad / totalDelitos * 100;
//            String percent = df.format(porcentaje);
//            System.out.println("Modalidad: " + modalidad + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
//        }
//        System.out.println("-----------------------------------------------------------");
//        JOptionPane.showMessageDialog(null, "Total de delitos: " + totalDelitos);
//        System.out.println("Total de delitos: " + totalDelitos);

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
            String percent = df.format(porcentaje);
            filas[i][0] = categoria;
            filas[i][1] = Integer.toString(cantidad);
            filas[i][2] = percent;
            i++;
        }
        JTable tabla = new JTable(filas, columnas);
        JOptionPane.showMessageDialog(null, new JScrollPane(tabla), titulo, JOptionPane.PLAIN_MESSAGE);
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