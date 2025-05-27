package cl.ucn.disc.pa.taller3.dominio;

public class Ayundantia extends OfertaAcademica {
    private String asignatura;
    private String rolAyudante;
    private int horasSemanales;
    private double promedioMinimo;
    private String tipoAyudantia;

    public Ayundantia(String codigo, String titulo, String descripcion, int duracionDias, String asignatura, String rolAyudante, int horasSemanales, double promedioMinimo, String tipoAyudantia) {
        super(codigo, titulo, descripcion, duracionDias);
        this.asignatura = asignatura;
        this.rolAyudante = rolAyudante;
        this.horasSemanales = horasSemanales;
        this.promedioMinimo = promedioMinimo;
        this.tipoAyudantia = tipoAyudantia;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public String getRolAyudante() {
        return rolAyudante;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public double getPromedioMinimo() {
        return promedioMinimo;
    }

    public String getTipoAyudantia() {
        return tipoAyudantia;
    }

    @Override
    public String toString(){
        return super.toString() +
                "=== AYUDANTÍA ===\n" +
                "Asignatura: " + asignatura + "\n" +
                "Rol Ayudante: " + rolAyudante + "\n" +
                "Horas Semanales: " + horasSemanales + "\n" +
                "Promedio Mínimo: " + promedioMinimo + "\n" +
                "Tipo de Ayudantía: " + tipoAyudantia + "\n" +
                "=====================================================\n";
    }
}
