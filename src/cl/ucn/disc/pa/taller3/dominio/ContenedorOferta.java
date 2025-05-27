package cl.ucn.disc.pa.taller3.dominio;

public class ContenedorOferta {
    private OfertaAcademica[] ofertas;
    private int cantidadActual;
    private int cantidadMaxima;

    public ContenedorOferta(int cantidadMaxima) {
        if (cantidadMaxima <= 0){
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        this.ofertas = new OfertaAcademica[cantidadMaxima];
        this.cantidadActual = 0;
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void agregar(OfertaAcademica nuevaOferta){
        if (this.cantidadActual>= this.cantidadMaxima){
            throw new IllegalArgumentException("No se pueden agregar nuevas ofertas, el contenedor se encuentra lleno, inténtelo mas tarde.");
        }

        if (nuevaOferta == null){
            throw new IllegalArgumentException("La nueva oferta no puede ser nula");
        }
        if (this.buscarPorCodigo(nuevaOferta.getCodigo())!= -1){
            throw new IllegalArgumentException("Ya existe una oferta con este código.");
        }
        if (this.buscarPorTitulo(nuevaOferta.getTitulo()) != -1){
            throw new IllegalArgumentException("Ya existe una oferta con este titulo");
        }

        this.ofertas[this.cantidadActual] = nuevaOferta;
        this.cantidadActual++;
    }

    public int buscarPorCodigo(String codigo){
        for (int i = 0; i < this.cantidadActual; i++) {
            if (this.ofertas[i].getCodigo().equalsIgnoreCase(codigo)){
                return i;
            }
        }
        return -1;
    }

    public int buscarPorTitulo(String titulo){
        for (int i = 0; i < this.cantidadActual; i++) {
            if (this.ofertas[i].getTitulo().equalsIgnoreCase(titulo)){
                return i;
            }
        }
        return -1;
    }

    public OfertaAcademica obtener(int posicion){
        if (posicion < 0 || posicion >= this.cantidadActual){
            throw new IllegalArgumentException("La posición no es valida");
        }
        return this.ofertas[posicion];
    }

    public void eliminar(String codigo){
        int pos = this.buscarPorCodigo(codigo);
        if (pos == -1) {
            throw new IllegalArgumentException("No existe una oferta con ese código.");
        }

        // Desplazar las ofertas hacia la izquierda
        for (int i = pos; i < this.cantidadActual - 1; i++) {
            this.ofertas[i] = this.ofertas[i + 1];
        }

        // Eliminar referencia del último elemento
        this.ofertas[this.cantidadActual - 1] = null;
        this.cantidadActual--;
    }

    public OfertaAcademica[] listar() {
        OfertaAcademica[] copia = new OfertaAcademica[this.cantidadActual];
        for (int i = 0; i < this.cantidadActual; i++) {
            copia[i] = this.ofertas[i];
        }
        return copia;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.cantidadActual; i++) {
            stringBuilder.append(":::Oferta N°").append(i + 1).append(":::").append("\n");
            stringBuilder.append(this.ofertas[i].toString()).append("\n");
        }
        return stringBuilder.toString();
    }

}
