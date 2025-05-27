package cl.ucn.disc.pa.taller3;

import cl.ucn.disc.pa.taller3.servicio.SistemaOfertas;
import cl.ucn.disc.pa.taller3.servicio.SistemaOfertasUCN;
import cl.ucn.disc.pa.taller3.vista.VistaConsola;
import ucn.StdOut;

import javax.management.InvalidApplicationException;

public class Main {
    public static void main(String[] args) {
        try {
            SistemaOfertas parteTrasera = new SistemaOfertasUCN();
            VistaConsola parteFrontal = new VistaConsola(parteTrasera);
            parteFrontal.iniciar();
        } catch (InvalidApplicationException e) {
            StdOut.println("::: Error en la configuración de la aplicación :::");
            if (e.getCause() != null) {
                StdOut.println("Causa real del error:");
                e.getCause().printStackTrace();
            } else {
                e.printStackTrace();
            }

            StdOut.println("Intente en otro momento.");
        }
    }
}