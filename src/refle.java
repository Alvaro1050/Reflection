/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvar
 */
public class refle {

    public static Paquete pq = new Paquete();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        final File carpeta = new File("C:\\Users\\alvar\\Documents\\NetBeansProjects\\Reflection\\src");

        pq.setNombre(carpeta.getName());
        listarFicherosPorCarpeta(carpeta, pq);

        FrmVentana vist = new FrmVentana(pq);
        vist.setVisible(true);

    }

    public static void listarFicherosPorCarpeta(final File carpeta, Paquete paquete) {

        try {

            for (final File ficheroEntrada : carpeta.listFiles()) {

                if (ficheroEntrada.isDirectory()) {
                    Paquete p = new Paquete();
                    p.setNombre(ficheroEntrada.getName());
                    listarFicherosPorCarpeta(ficheroEntrada, p);
                    paquete.getListaPaquetes().add(p);
                } else {
                    String nombre = ficheroEntrada.getName();
                    String[] parts = nombre.split("\\.");
                    Class clazz;
                    if (paquete.getNombre().equals("src")) {
                        clazz = Class.forName(parts[0]);
                    } else {
                        clazz = Class.forName(paquete.getNombre() + "." + parts[0]);
                    }
                    Clase cla = new Clase();
                    cla.setNombreClase(parts[0]);
                    Method todasLosMetodos[] = clazz.getDeclaredMethods();

                    for (int i = 0; i < todasLosMetodos.length; i++) {
                        cla.getMetodos().add(todasLosMetodos[i].getName());
                    }

                    Field todasLasVariables[] = clazz.getDeclaredFields();

                    for (int i = 0; i < todasLasVariables.length; i++) {
                        cla.getAtributos().add(todasLasVariables[i].getName());
                    }
                    paquete.getListaClase().add(cla);
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
