package cl.ucn.disc.pa.taller3.dominio;

public class ContenedorUsuarios {
    private Usuario[] usuarios;
    private int cantidadMaxima;
    private int cantidadActual;

    public ContenedorUsuarios(int cantidadMaxima){
        if (cantidadMaxima <= 0) {
            throw new IllegalArgumentException("La cantidad máxima debe ser mayor a 0.");
        }
        this.usuarios = new Usuario[cantidadMaxima];
        this.cantidadActual = 0;
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void agregar(Usuario nuevoUsuario) {
        if (this.cantidadActual >= this.cantidadMaxima) {
            throw new IllegalArgumentException("No se pueden agregar más usuarios. Límite alcanzado.");
        }

        if (nuevoUsuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }

        if (this.buscarPorRut(nuevoUsuario.getRut()) != -1) {
            throw new IllegalArgumentException("Ya existe un usuario con este RUT.");
        }

        this.usuarios[this.cantidadActual] = nuevoUsuario;
        this.cantidadActual++;
    }

    public int buscarPorRut(String rut) {
        for (int i = 0; i < this.cantidadActual; i++) {
            if (this.usuarios[i].getRut().equalsIgnoreCase(rut)) {
                return i;
            }
        }
        return -1;
    }

    public Usuario obtenerPorRut(String rut) {
        int pos = this.buscarPorRut(rut);
        if (pos == -1) {
            return null;
        }
        return this.usuarios[pos];
    }
    public Usuario obtenerPorIndice(int i) {
        if (i < 0 || i >= this.cantidadActual) {
            throw new IllegalArgumentException("Índice fuera de rango.");
        }
        return this.usuarios[i];
    }
}
