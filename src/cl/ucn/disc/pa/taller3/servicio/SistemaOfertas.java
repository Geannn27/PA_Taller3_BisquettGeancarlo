package cl.ucn.disc.pa.taller3.servicio;

import cl.ucn.disc.pa.taller3.dominio.OfertaAcademica;

public interface SistemaOfertas {

    // Registro y autenticación
    void registrarUsuario(String nombre, String rut, String correo, String contrasenia);
    boolean iniciarSesion(String rut, String contrasenia);
    void cerrarSesion();

    // Perfil de usuario
    void verPerfil();
    void editarPerfil(String nombre, String correo);

    // Gestión de ofertas
    void ingresarOferta(OfertaAcademica nuevaOferta); // Versión simplificada
    OfertaAcademica buscarOferta(String codigo);
    void verOfertas();
    void editarOferta(String codigo, OfertaAcademica nuevaOferta);
    void eliminarOferta(String codigo);
    OfertaAcademica buscarOfertaPorTitulo(String titulo);
}
