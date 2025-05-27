package cl.ucn.disc.pa.taller3.dominio;

public abstract class OfertaAcademica {
    private String codigo;
    private String titulo;
    private String descripcion;
    private int duracionDias;

    public OfertaAcademica(String codigo, String titulo, String descripcion, int duracionDias) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracionDias = duracionDias;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracionDias() {
        return duracionDias;
    }
    @Override
    public String toString(){
        return "Código: " + getCodigo() + "\n" +
                "Título: " + getTitulo() + "\n" +
                "Descripción: " + getDescripcion() + "\n" +
                "Duración (días): " + getDuracionDias() + "\n";
    }
}
