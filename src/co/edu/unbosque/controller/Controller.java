package co.edu.unbosque.controller;

import co.edu.unbosque.model.Archivo;
import co.edu.unbosque.view.Vista;

import java.io.File;

/**
 * The type Controller.
 */
public class Controller {
    /**
     * The instance of the Model.
     */
    public Archivo model = new Archivo();
    /**
     * The instance of the View.
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
     * The method Funcionar(), is the main method of the program that calls all the methods of the other classes.
     */
    public void funcionar() {
        txtFile = new File("src/archivo-1.txt");
        view.mainMenu();
    }
}
