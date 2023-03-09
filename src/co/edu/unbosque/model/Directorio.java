package co.edu.unbosque.model;

import co.edu.unbosque.view.Vista;

import java.io.File;
import java.text.DecimalFormat;

/**
 * The type Directorio.
 */
public class Directorio {
    /**
     * The constant view.
     */
    public static Vista view = new Vista();
    private static final DecimalFormat df = new DecimalFormat("#.##");


    /**
     * List folder contents, by means of a ".bat" file.
     *
     * @param rutaCarpeta the path of the folder to analyze carpeta
     */
    public void listarContenidoCarpeta(String rutaCarpeta) {
        // Crear un objeto de tipo File con la ruta de la carpeta
        File carpeta = new File(rutaCarpeta);
        view.showMsj("The contents of the folder will be listed: " + rutaCarpeta);

        // Verificar si la carpeta existe
        if (!carpeta.exists()) {
            view.showError("The path isn't a folder.");
            return;
        }

        // Verificar si la ruta es una carpeta
        if (!carpeta.isDirectory()) {
            view.showError("The path isn't a folder.");
            return;
        }

        // Obtener la lista de archivos y carpetas en la carpeta
        File[] archivos = carpeta.listFiles();

        // Recorrer la lista de archivos y carpetas y mostrar el nombre en la consola
        System.out.println("Folder content: " + rutaCarpeta);
        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                view.consoleMsj("[Folder] " + archivo.getName());
            } else {
                view.consoleMsj("[File] " + archivo.getName());
            }
        }
    }

    /**
     * Percent files.
     *
     * @param pathDirectory the path directory
     */
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
        view.showMsj("The total size of the directory is: " + totalSize + " bytes");

        // Calcular el porcentaje del tamaño de cada archivo
        for (File archivo : archivos) {
            double porcentaje = 0;
            String percent = df.format(porcentaje);
            if (archivo.isFile()) {
                porcentaje = ((double) archivo.length() / tamanoTotal * 100);
                percent = df.format(porcentaje);
                System.out.println("File " + archivo.getName() + " - " + percent + "%");
            }
            view.consoleMsj(archivo.getName() + " - " + percent + "%");
        }
    }


}

