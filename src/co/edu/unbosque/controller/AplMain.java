package co.edu.unbosque.controller;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class AplMain {

    public static void main(String[] args) {

        try {
            File archivo = new File("src/archivo-1.txt");
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.DOWN);
            double s=archivo.length();
            String size = df.format(archivo.length());
            double kb=s/1000;
            double megabytes= s / (1024 * 1024);
            String mb = df.format(megabytes);


            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }
            JOptionPane.showMessageDialog(null,"El tamaño del archivo es: "+size+" bytes");
            JOptionPane.showMessageDialog(null,"El tamaño del archivo es: "+kb+" Kilobytes");
            JOptionPane.showMessageDialog(null,"El tamaño del archivo es: "+mb+" Megabytes");
            scanner.close();

        } catch (FileNotFoundException e) {
           JOptionPane.showMessageDialog(null,"El archivo no se encontró.","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }
}
