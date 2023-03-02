package co.edu.unbosque.controller;

import co.edu.unbosque.model.Archivo;
import co.edu.unbosque.view.Vista;

import java.io.File;

public class Controller {
    public Archivo model = new Archivo();
    public Vista view = new Vista();

    Controller() {
        funcionar();
    }

    public void funcionar() {
        view.MainMenu();|
        model.runBatOnFolder("src", "Archivos.bat");
        File txtFile= new File("src/archivo-1.txt");
        model.getFileSize(txtFile);

    }
}
