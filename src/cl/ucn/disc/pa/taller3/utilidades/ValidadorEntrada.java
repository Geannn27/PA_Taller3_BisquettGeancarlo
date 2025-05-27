package cl.ucn.disc.pa.taller3.utilidades;

import ucn.In;
import ucn.StdIn;
import ucn.StdOut;

public class ValidadorEntrada {
    public static int lecturaEnteros(String frase) {
        String opcion;

        while (true) {
            StdOut.println(frase);
            opcion = StdIn.readLine();

            try {
                return Integer.parseInt(opcion);
            } catch (NumberFormatException e) {
                StdOut.println("Ingrese un numero.");
            }
        }
    }
}