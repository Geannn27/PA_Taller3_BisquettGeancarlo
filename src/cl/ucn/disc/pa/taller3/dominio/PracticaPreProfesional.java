package cl.ucn.disc.pa.taller3.dominio;

import java.time.LocalDate;

public class PracticaPreProfesional extends ActividadEmpresa {
    private boolean tieneRemuneracion;


    public PracticaPreProfesional(String codigo, String titulo, String descripcion, int duracionDias, LocalDate fechaInicio, String nombreEmpresa, String nombreGuia,
                                  boolean tieneRemuneracion) {
        super(codigo, titulo, descripcion, duracionDias, fechaInicio, nombreEmpresa, nombreGuia);
        this.tieneRemuneracion = tieneRemuneracion;
    }

    public boolean isTieneRemuneracion() {
        return tieneRemuneracion;
    }
    @Override
    public String toString() {
        return super.toString() +
                "=== PRÁCTICA PRE-PROFESIONAL ===\n" +
                "Remuneración: " + (tieneRemuneracion ? "Sí" : "No") + "\n" +
                "=====================================================\n";
    }
}
