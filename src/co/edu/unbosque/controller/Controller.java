package co.edu.unbosque.controller;

import co.edu.unbosque.model.Archivo;
import co.edu.unbosque.view.Vista;

import java.io.File;

/**
 * The type Controller.
 */
public class Controller {
    /**
     * The Model.
     */
    public Archivo model = new Archivo();
    /**
     * The View.
     */
    public Vista view = new Vista();
    /**
     * The Txt file.
     */
    File txtFile = new File("src/archivo-1.txt");


    /**
     * Instantiates a new Controller.
     */
    Controller() {
        funcionar();
    }

    /**
     * Funcionar.
     */
    public void funcionar() {
        txtFile = new File("src/archivo-1.txt");
        view.mainMenu();
    }
}
