package co.edu.unbosque.view;

import co.edu.unbosque.model.Archivo;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static javax.swing.JOptionPane.showMessageDialog;

public class Vista {
    Archivo model = new Archivo();
    File txtFile = new File("src/archivo-1.txt");


    public void mainMenu() {
        try {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción:\n" +
                    "1. Subir archivo\n"));
            if (opcion != 1) {
                showError("Opcion no valida!");
                mainMenu();
            }
            if (opcion == 1) {
                uploadFile();
                menuOptions();
            }

        } catch (NumberFormatException e) {
            showError("Debe ingresar un número");
            mainMenu();
        }

    }

    public void menuOptions() {
        File csv;
        csv = new File("src/archivo-1.csv");
        try {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción:\n" +
                    "1. Procesar archivo\n" +
                    "2. Salir..."));
            if (opcion > 2 || opcion < 1) {
                showError("Opcion no valida");
                menuOptions();
            }
            switch (opcion) {
                case 1:
                    model.getFileSize(txtFile);
                    csv = model.createCsv(txtFile, new File("src/archivo-1.csv"));
                    model.analizarArchivoDelitos();
                    break;
                case 2:
                    csv.delete();
                    //Creo la carpeta Apellidos1_Apellidos2 para guardar los archivos
                    model.runBatOnFolder("src", "Folder_files.bat");
                    model.splitFile("src/archivo-1.txt", "src/ChitivaCastillo_PedrazaSanabria/Numeros",20);
                    model.runBatOnFolder("src", "Rename.bat");
                    System.exit(0);
                    break;
            }
        } catch (NumberFormatException e) {
            showError("Debe ingresar un número");
            mainMenu();
        }

    }

    public void showMsj(String msj) {
        JOptionPane.showMessageDialog(null, msj);
    }

    public String getMsj(String msj) {
        JOptionPane.showInputDialog(null, (msj));
        return msj;
    }

    public void consoleMsj(String msj) {
        System.out.println(msj);
    }

    public void showError(String msj) {
        JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showWarning(String msj) {
        JOptionPane.showMessageDialog(null, msj, "Alerta", JOptionPane.WARNING_MESSAGE);
    }


    public void uploadFile() {
        // Crear un JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        // Mostrar el diálogo para seleccionar un archivo
        var result = fileChooser.showOpenDialog(null);

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
            this.showError("Selección cancelada");
        }
        menuOptions();
    }

    //GETTER AND SETTER OF THE FILE
    public File getTxtFile() {
        return txtFile;
    }

    public void setTxtFile(File txtFile) {
        this.txtFile = txtFile;
    }
}
