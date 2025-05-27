package cl.ucn.disc.pa.taller3.servicio;
import cl.ucn.disc.pa.taller3.dominio.*;
import cl.ucn.disc.pa.taller3.utilidades.ValidadorRut;
import ucn.*;
import javax.management.InvalidApplicationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SistemaOfertasUCN implements SistemaOfertas{
    private Usuario usuarioActual;
    private ContenedorOferta oferta = new ContenedorOferta(999);
    private ContenedorUsuarios usuarios = new ContenedorUsuarios(999);

    public SistemaOfertasUCN () throws InvalidApplicationException{
        try {
            this.usuarioActual = null;
            this.lecturaDeArchivo();
        } catch (Exception e){
            e.printStackTrace();
            throw new InvalidApplicationException(e);
        }
    }

    private void lecturaDeArchivo() throws IOException{
        Registro linea;

        final String NOMBRE_ARCHIVO = "OfertasAcademicas.txt";
        ArchivoEntrada archivo = new ArchivoEntrada(NOMBRE_ARCHIVO);

        int lineaActual = 1;

        while (!archivo.isEndFile()) {
            try {
                linea = archivo.getRegistro();
                String tipo = linea.getString(); //A, C o P
                String codigo = linea.getString();
                String titulo = linea.getString();
                String descripcion = linea.getString();
                String departamento = linea.getString();
                int duracionDias = linea.getInt();

                if (tipo.equalsIgnoreCase("A")) {
                    //Ayudantía
                    String asignatura = linea.getString();
                    String rol = linea.getString();
                    int horas = linea.getInt();
                    double promedioMin = linea.getDouble();

                    Ayundantia ayundantia = new Ayundantia(codigo, titulo, descripcion, duracionDias, asignatura, rol, horas, promedioMin, "Taller"); //se puede ajustar el tipo de ayudantia
                    oferta.agregar(ayundantia);
                } else if (tipo.equalsIgnoreCase("C")) {
                    //Capstone
                    String fechaTexto = linea.getString();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaInicio = LocalDate.parse(fechaTexto, formato);
                    String nombreEmpresa = linea.getString();
                    String nombreGuia = linea.getString();
                    String tipoProyecto = linea.getString();
                    int duracionMeses = linea.getInt();
                    String carreras = linea.getString();
                    int cantidadEstudiantes = linea.getInt();

                    String[] carrerasArray = carreras.split("\\|");

                    Capstone capstone = new Capstone(
                            codigo, titulo, descripcion, duracionDias,
                            fechaInicio, nombreEmpresa, nombreGuia,
                            tipoProyecto, duracionMeses, carrerasArray, cantidadEstudiantes);
                    oferta.agregar(capstone);
                } else if (tipo.equalsIgnoreCase("P")) {
                    //Práctica Pre-profesional
                    String fechaTexto = linea.getString();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaInicio = LocalDate.parse(fechaTexto, formato);
                    String nombreEmpresa = linea.getString();
                    String nombreGuia = linea.getString();
                    String remuneracionTexto = linea.getString();

                    boolean tieneRemuneracion = remuneracionTexto.equalsIgnoreCase("Si");

                    PracticaPreProfesional practica = new PracticaPreProfesional(codigo, titulo, descripcion, duracionDias, fechaInicio, nombreEmpresa, nombreGuia, tieneRemuneracion);
                    oferta.agregar(practica);
                }
            }catch (Exception e){
                StdOut.println("Error en la linea: " + lineaActual + " del Archivo: ");
                e.printStackTrace();
                break;
            }
            lineaActual++;
        }
        archivo.close();
    }


    @Override
    public void registrarUsuario(String nombre, String rut, String correo, String contrasenia) {
        //Validaciones
        if (nombre == null || nombre.length() < 3 || nombre.length() > 255) {
            throw new IllegalArgumentException("El nombre debe tener entre 3 y 255 caracteres.");
        }
        if (correo == null || correo.length() < 3 || correo.length() > 255){
            throw new IllegalArgumentException("El correo no es válido.");

        }
        if (contrasenia == null || contrasenia.length() < 6 || contrasenia.length() > 255 ){
            throw new IllegalArgumentException("La contraseña debe tener entre 6 y 255 caracteres.");
        }

        // Validar RUT con clase ValidadorRut
        if (!ValidadorRut.validarRut(rut)) {
            throw new IllegalArgumentException("El RUT ingresado no es válido.");
        }

        // Verificar que el RUT no esté registrado
        if (usuarios.obtenerPorRut(rut) != null) {
            throw new IllegalArgumentException("Ya existe un usuario con este RUT.");
        }

        // Verificar que el correo no esté repetido
        for (int i = 0; i < usuarios.getCantidadActual(); i++) {
            Usuario existente = usuarios.obtenerPorIndice(i);
            if (existente.getCorreo().equalsIgnoreCase(correo)) {
                throw new IllegalArgumentException("Ya existe un usuario con este correo.");
            }
        }

        //se agrega un usuario
        Usuario nuevo = new Usuario(nombre, rut, correo, contrasenia);
        usuarios.agregar(nuevo);
    }


    @Override
    public boolean iniciarSesion(String rut, String contrasenia) {
        if (rut == null || contrasenia == null){
            throw new IllegalArgumentException("Rut y contraseña no pueden ser nulos.");
        }
        Usuario usuario = usuarios.obtenerPorRut(rut);

        if (usuario == null){
            return false; //no existe usuario con ese rut
        }

        if (!usuario.validarContrasenia(contrasenia)){
            return false; //contraseña incorrecta
        }
        //si pasa las validaciones, se inicia sesión
        this.usuarioActual = usuario;
        return true;
    }

    @Override
    public void cerrarSesion() {
        if (this.usuarioActual == null){
            throw new IllegalArgumentException("No hay sesión iniciada.");
        }
        this.usuarioActual = null;
    }

    @Override
    public void verPerfil() {
        if (this.usuarioActual == null){
            throw new IllegalArgumentException("No hay sesión iniciada.");
        }
       StdOut.println(usuarioActual.toString());
    }

    @Override
    public void editarPerfil(String nombre, String correo) {
        if (this.usuarioActual == null){
            throw new IllegalArgumentException("Debe iniciar sesión para editar su perfil.");
        }
        if (nombre == null || nombre.length() < 3 || nombre.length() > 255){
            throw new IllegalArgumentException("El nombre debe tener entre 3 y 255 caracteres.");
        }
        if (correo == null || correo.length() < 3 || correo.length() >255){
            throw new IllegalArgumentException("El correo debe tener entre 3 y 255 caracteres.");
        }

        //Verificar que el correo no se esté usando en otro perfil
        for (int i = 0; i < usuarios.getCantidadActual(); i++) {
            Usuario usuario = usuarios.obtenerPorIndice(i);
            if (usuario != usuarioActual && usuario.getCorreo().equalsIgnoreCase(correo)){
                throw new IllegalArgumentException("El correo ya está en uso por otro usuario, intente con otro.");
            }
        }
        //Actualizar la información del perfil
        usuarioActual.setNombre(nombre);
        usuarioActual.setCorreo(correo);
    }

    @Override
    public void ingresarOferta(OfertaAcademica nuevaOferta) {
        if (usuarioActual == null){
            throw new IllegalArgumentException("Debe iniciar sesión para ingresar una oferta");
        }
        if (nuevaOferta == null){
            throw new IllegalArgumentException("La oferta no puede ser nula.");
        }
        oferta.agregar(nuevaOferta);

    }

    @Override
    public OfertaAcademica buscarOferta(String codigo) {
        int posicion = oferta.buscarPorCodigo(codigo);
        if (posicion == -1){
            throw new IllegalArgumentException("No existe una oferta con ese código.");
        }
        return oferta.obtener(posicion);
    }

    public OfertaAcademica buscarOfertaPorTitulo(String titulo){
        int posicion = oferta.buscarPorTitulo(titulo);
        if (posicion == -1){
            throw new IllegalArgumentException("No existe una oferta con ese código");
        }
        return oferta.obtener(posicion);
    }

    @Override
    public void verOfertas() {
        if (oferta.getCantidadActual() == 0){
            StdOut.println("No hay ofertas registradas en el sistema.");
        } else {
            StdOut.println(oferta.toString());
        }
    }

    @Override
    public void editarOferta(String codigo, OfertaAcademica nuevaOferta) {
        int pos = oferta.buscarPorCodigo(codigo);
        if (pos == -1) {
            throw new IllegalArgumentException("No existe una oferta con ese código.");
        }

        if (nuevaOferta == null) {
            throw new IllegalArgumentException("La nueva oferta no puede ser nula.");
        }

        oferta.eliminar(codigo);
        oferta.agregar(nuevaOferta);

    }

    @Override
    public void eliminarOferta(String codigo) {
        oferta.eliminar(codigo);
        StdOut.println("La oferta con código " + codigo + " fue eliminado correctamente.");
    }
}
