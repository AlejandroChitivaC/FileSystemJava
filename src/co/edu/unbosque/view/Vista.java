package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static javax.swing.JOptionPane.showMessageDialog;

public class Vista {
    public int opcion;

    public void MainMenu() {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "" +
                "1. Cargar archivo\n" +
                "2. Mostrar tamaños del archivo en bytes, kb y mb\n" +
                "3. Buscar por Codigo\n" +
                "4. Buscar por Nombre\n" +
                "5. Buscar por Descripcion\n"));
        switch (opcion) {
            case 1:
                uploadFile();
                break;
            case 2:

                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                showMsj("Opcion no valida");
                break;
        }


    }

    public void showMsj(String msj) {
        JOptionPane.showMessageDialog(null, msj);
    }

    public void consoleMsj(String msj) {
        System.out.println(msj);
    }

    public void uploadFile() {
        // Crear un JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Mostrar el diálogo para seleccionar un archivo
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // El usuario ha seleccionado un archivo
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Crear una instancia de FileInputStream para leer el archivo seleccionado
                FileInputStream in = new FileInputStream(selectedFile);

                // Crear una instancia de FileOutputStream para escribir el archivo en el directorio src
                FileOutputStream out = new FileOutputStream(new File("src/" + selectedFile.getName()));

                // Leer el archivo y escribirlo en el directorio src
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                // Cerrar los streams
                in.close();
                out.close();

                JOptionPane.showMessageDialog(null, "Archivo subido exitosamente");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al subir el archivo");
            }

        } else {
            // El usuario ha cancelado la selección
            JOptionPane.showMessageDialog(null, "Selección cancelada");
        }
    }

    public void showError(String msj) {
        JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
