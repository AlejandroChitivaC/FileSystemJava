package co.edu.unbosque.model;

import co.edu.unbosque.view.Vista;

import java.io.File;
import java.text.DecimalFormat;

public class Directorio {
    public static Vista view = new Vista();
    private static DecimalFormat df = new DecimalFormat("#.##");


    public void listarContenidoCarpeta(String rutaCarpeta) {
        // Crear un objeto de tipo File con la ruta de la carpeta
        File carpeta = new File(rutaCarpeta);
        view.showMsj("Se va a listar el contenido de la carpeta: " + rutaCarpeta);

        // Verificar si la carpeta existe
        if (!carpeta.exists()) {
            view.showError("La ruta no es una carpeta.");
            return;
        }

        // Verificar si la ruta es una carpeta
        if (!carpeta.isDirectory()) {
            view.showError("La ruta no es una carpeta.");
            return;
        }

        // Obtener la lista de archivos y carpetas en la carpeta
        File[] archivos = carpeta.listFiles();

        // Recorrer la lista de archivos y carpetas y mostrar el nombre en la consola
        System.out.println("Contenido de la carpeta: " + rutaCarpeta);
        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                view.consoleMsj("[Carpeta] " + archivo.getName());
            } else {
                view.consoleMsj("[Archivo] " + archivo.getName());
            }
        }
    }

    public void percentFiles(String pathDirectory) {

        // Especificar la ruta del directorio a analizar
        File dir = new File(pathDirectory);

        // Obtener una lista de todos los archivos en el directorio
        File[] archivos = dir.listFiles();

        // Inicializar la variable para almacenar el tamaño total del directorio
        double tamanoTotal = 0;

        // Calcular el tamaño total del directorio
        for (File archivo : archivos) {
            if (archivo.isFile()) {
                tamanoTotal += archivo.length();
            }
        }
        String totalSize = df.format(tamanoTotal);
        view.showMsj("El tamaño total del directorio es: " + totalSize + " bytes");

        // Calcular el porcentaje del tamaño de cada archivo
        for (File archivo : archivos) {
            double porcentaje = 0;
            String percent = df.format(porcentaje);
            if (archivo.isFile()) {
                porcentaje = ((double) archivo.length() / tamanoTotal * 100);
                percent = df.format(porcentaje);
                System.out.println("Archivo " + archivo.getName() + " - " + percent + "%");
            }
            view.consoleMsj(archivo.getName() + " - " + percent + "%");
        }
    }


}

