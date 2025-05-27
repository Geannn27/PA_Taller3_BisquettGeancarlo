package cl.ucn.disc.pa.taller3.dominio;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class Capstone extends ActividadEmpresa{
    private String tipoProyecto;
    private int duracionMeses;
    private String[] carrerasHabilitadas;
    private int cantidadEstudiantesMin;

    public Capstone(String codigo, String titulo, String descripcion, int duracionDias, LocalDate fechaInicio, String nombreEmpresa, String nombreGuia,
                    String tipoProyecto, int duracionMeses,
                    String[] carrerasHabilitadas, int cantidadEstudiantesMin) {
        super(codigo, titulo, descripcion, duracionDias, fechaInicio, nombreEmpresa, nombreGuia);
        this.tipoProyecto = tipoProyecto;
        this.duracionMeses = duracionMeses;
        this.carrerasHabilitadas = carrerasHabilitadas;
        this.cantidadEstudiantesMin = cantidadEstudiantesMin;
    }

    public String getTipoProyecto() {
        return tipoProyecto;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public String[] getCarrerasHabilitadas() {
        return carrerasHabilitadas;
    }

    public int getCantidadEstudiantesMin() {
        return cantidadEstudiantesMin;
    }

    @Override
    public String toString(){
        return super.toString() +
                "=== CAPSTONE ===\n" +
                "Tipo de Proyecto: " + tipoProyecto + "\n" +
                "Duración (meses): " + duracionMeses + "\n" +
                "Carreras Habilitadas: " + Arrays.toString(carrerasHabilitadas) + "\n" +
                "Cantidad Mínima de Estudiantes: " + cantidadEstudiantesMin + "\n" +
                "=====================================================\n";

    }
}
