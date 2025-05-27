package cl.ucn.disc.pa.taller3.dominio;

import java.time.LocalDate;

public abstract class ActividadEmpresa extends OfertaAcademica {
    private LocalDate fechaInicio;
    private String nombreEmpresa;
    private String nombreGuia;

    public ActividadEmpresa(String codigo, String titulo, String descripcion, int duracionDias, LocalDate fechaInicio, String nombreEmpresa, String nombreGuia) {
        super(codigo, titulo, descripcion, duracionDias);
        this.fechaInicio = fechaInicio;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreGuia = nombreGuia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getNombreGuia() {
        return nombreGuia;
    }
    @Override
    public String toString (){
        return super.toString() +
                "Fecha de Inicio: " + this.fechaInicio + "\n" +
                "Nombre de la Empresa: " + this.nombreEmpresa + "\n" +
                "Nombre del Guía: " + this.nombreGuia + "\n";
    }
}
