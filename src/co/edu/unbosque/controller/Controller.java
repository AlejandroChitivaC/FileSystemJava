package co.edu.unbosque.controller;

import co.edu.unbosque.model.Archivo;
import co.edu.unbosque.view.Vista;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Controller {
    public Archivo model = new Archivo();
    public Vista view = new Vista();

    Controller() {
        funcionar();
    }

    public void funcionar() {
        File txtFile = new File("src/archivo-1.txt");
        int opcion = Integer.parseInt(JOptionPane.showInputDialog("1. Subir archivo\n2. Ver tamaño de archivo\n3. Ver tamaño de carpeta\n4. Ver tamaño de carpeta y subcarpetas\n5. Salir"));
        if (opcion > 5 || opcion < 1) {
            view.showMsj("Opcion no valida");
            funcionar();
        }
        switch (opcion) {
            case 1:
                view.uploadFile();
            case 2:
                model.getFileSize(txtFile);
                break;

        }


//        model.runBatOnFolder("src", "Archivos.bat");

//        File txtFile= new File("src/archivo-1.txt");
//        model.getFileSize(txtFile);

    }
}
