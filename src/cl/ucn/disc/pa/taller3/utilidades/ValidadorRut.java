package cl.ucn.disc.pa.taller3.utilidades;

public class ValidadorRut {
    public static boolean validarFormato(String rut) {
        return rut != null && rut.matches("\\d{7,8}-[\\dkK]");
    }
    public static boolean validarRut(String rut) {
        if (!validarFormato(rut)) return false;

        String[] partes = rut.split("-");
        String cuerpo = partes[0];
        String dv = partes[1].toUpperCase();

        int suma = 0;
        int multiplicador = 2;

        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(cuerpo.charAt(i)) * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
        }

        int residuo = 11 - (suma % 11);
        String dvEsperado;
        if (residuo == 11) dvEsperado = "0";
        else if (residuo == 10) dvEsperado = "K";
        else dvEsperado = String.valueOf(residuo);

        return dv.equals(dvEsperado);
    }
}
