package co.edu.unbosque.controller;

import co.edu.unbosque.model.Archivo;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class AplMain {



    public static void main(String[] args) {
        Archivo model = new Archivo();
        model.ejecutarBatEnCarpeta("src","Archivos.bat");

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        try {
            File archivo = new File("src/archivo-1.txt");
            // formatting the decimals to 2 places
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
            String archivoCsv = "src/archivo-1.csv";
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
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer o escribir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se encontró.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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

        System.out.println("Delitos por zona");
        for (String zona : contadoresZona.keySet()) {
            int cantidad = contadoresZona.get(zona);
            double porcentaje = (double) cantidad / totalDelitos * 100;
            String percent = df.format(porcentaje);
            System.out.println("Zona: " + zona + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Delitos por mes");
        for (String mes : contadoresMes.keySet()) {
            int cantidad = contadoresMes.get(mes);
            double porcentaje = (double) cantidad / totalDelitos * 100;
            String percent = df.format(porcentaje);
            System.out.println("Mes: " + mes + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Delitos por departamento");
        for (String departamento : contadoresDepartamento.keySet()) {
            int cantidad = contadoresDepartamento.get(departamento);
            double porcentaje = (double) cantidad / totalDelitos * 100;
            String percent = df.format(porcentaje);
            System.out.println("Departamento: " + departamento + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Delitos por edad");
        for (String edad : contadoresEdad.keySet()) {
            int cantidad = contadoresEdad.get(edad);
            double porcentaje = (double) cantidad / totalDelitos * 100;
            String percent = df.format(porcentaje);
            System.out.println("Edad: " + edad + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Delitos por modalidad");
        for (String modalidad : contadoresModalidad.keySet()) {
            int cantidad = contadoresModalidad.get(modalidad);
            double porcentaje = (double) cantidad / totalDelitos * 100;
            String percent = df.format(porcentaje);
            System.out.println("Modalidad: " + modalidad + ", cantidad de delitos: " + cantidad + ", porcentaje: " + percent + "%");
        }
        System.out.println("-----------------------------------------------------------");
        JOptionPane.showMessageDialog(null, "Total de delitos: " + totalDelitos);
        System.out.println("Total de delitos: " + totalDelitos);

        scanner.close();
//        String hoy = Instant.now().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        System.out.println("yyyy/MM/dd HH:mm:ss-> " + dtf.format(LocalDateTime.now()));
        String now = dtf.format(LocalDateTime.now());
        now= now.toString();
        JOptionPane.showMessageDialog(null, "Fecha y hora de ejecución: " + now);


        File archivoAntiguo = new File("src/archivo-1.txt");
        File archivoNuevo = new File("src/delito" + now + ".txt");
        if (archivoAntiguo.renameTo(archivoNuevo)) {
            System.out.println("El archivo se ha renombrado correctamente.");
        } else {
            System.out.println("No se ha podido renombrar el archivo.");

        }

    }
}




