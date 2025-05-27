package cl.ucn.disc.pa.taller3.vista;
import cl.ucn.disc.pa.taller3.dominio.Ayundantia;
import cl.ucn.disc.pa.taller3.dominio.Capstone;
import cl.ucn.disc.pa.taller3.dominio.OfertaAcademica;
import cl.ucn.disc.pa.taller3.dominio.PracticaPreProfesional;
import cl.ucn.disc.pa.taller3.servicio.SistemaOfertas;
import cl.ucn.disc.pa.taller3.utilidades.ValidadorEntrada;
import ucn.StdIn;
import ucn.StdOut;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VistaConsola {
    private SistemaOfertas sistemaOfertas;
    
    public VistaConsola(SistemaOfertas sistemaOfertas){
        this.sistemaOfertas = sistemaOfertas;
    }
    
    public void iniciar(){
        StdOut.println("::: SISTEMA OFERTAS UCN :::");
        this.menuLogeo();
    }

    private void menuLogeo() {
        int opcion;

        while(true){
            StdOut.println("=== BIENVENIDO AL INICIO DE SESION ===");
            StdOut.println("[1] Iniciar Sesión.");
            StdOut.println("[2] Registrarse.");
            StdOut.println("[3] Salir del programa.");
            opcion = ValidadorEntrada.lecturaEnteros("Ingrese una opción.");

            if (opcion == 1){
                this.iniciarSesion();
                continue;
            }
            if (opcion == 2){
                this.registrarUsuario();
                continue;
            }
            if (opcion == 3){
                break;
            }
            StdOut.println("Ingrese una opción válida");
        }
        StdOut.println("Hasta Luego!.");
    }

    private void iniciarSesion(){
        boolean exito = false;

        StdOut.println("Ingrese su Rut(sin puntos y con guíon): ");
        String rut = StdIn.readLine();
        StdOut.println("Ingrese su contraseña");
        String contrasenia = StdIn.readLine();

        try {
         exito = sistemaOfertas.iniciarSesion(rut,contrasenia);
        }catch (Exception e){
            StdOut.println("Error: " + e.getMessage());
        }
        if (exito){
            StdOut.println("Sesión iniciada con éxito.");
            this.menuPrincipal();
        }else {
            StdOut.println("Rut o contraseña incorrectos.");
        }
    }

    private void registrarUsuario(){
        StdOut.println("Ingrese su nombre: ");
        String nombre = StdIn.readLine();
        StdOut.println("Ingrese su Rut(sin puntos y con guíon): ");
        String rut = StdIn.readLine();
        StdOut.println("Ingrese su correo electrónico");
        String correo = StdIn.readLine();
        StdOut.println("Ingrese su contraseña: ");
        String contrasenia = StdIn.readLine();

        try{
            sistemaOfertas.registrarUsuario(nombre,rut,correo,contrasenia);
            StdOut.println("Usuario registrado correctamente.");
        }catch (Exception e) {
            StdOut.println("Error : " + e.getMessage());
        }
    }

    private void menuPrincipal(){

        while(true){
            StdOut.println("=== MENÚ PRINCIPAL ===");
            StdOut.println("[1] Ingresar Oferta");
            StdOut.println("[2] Buscar Oferta");
            StdOut.println("[3] Ver Ofertas");
            StdOut.println("[4] Editar Oferta");
            StdOut.println("[5] Eliminar Oferta");
            StdOut.println("[6] Ver Perfil");
            StdOut.println("[7] Editar Perfil");
            StdOut.println("[8] Cerrar Sesión");

            int opcion = ValidadorEntrada.lecturaEnteros("Ingrese una opción:");
            try {
                switch (opcion){
                    case 1:
                        this.ingresarOferta();
                        break;
                    case 2:
                       this.buscarOferta();
                        break;
                    case 3:
                        sistemaOfertas.verOfertas();
                        break;
                    case 4:
                        this.editarOferta();
                        break;
                    case 5:
                        this.eliminarOferta();
                        break;
                    case 6:
                        sistemaOfertas.verPerfil();
                        break;
                    case 7:
                        this.editarPerfil();
                        break;
                    case 8:
                        sistemaOfertas.cerrarSesion();
                        return;
                    default:
                        StdOut.println("Opción no válida.");
                }
            }catch (Exception e){
                StdOut.println("Error: " + e.getMessage());
            }
        }
    }


    private void ingresarOferta(){
        StdOut.println("Ingrese el código único:");
        String codigo = StdIn.readLine();

        OfertaAcademica nueva = construirNuevaOferta(codigo);
        if (nueva != null) {
            try {
                sistemaOfertas.ingresarOferta(nueva);
                StdOut.println("Oferta registrada con éxito.");
            } catch (Exception e) {
                StdOut.println("Error: " + e.getMessage());
            }
        }
    }

    private OfertaAcademica construirNuevaOferta(String codigo) {
        StdOut.println("Seleccione el tipo de oferta: ");
        StdOut.println("[1] Ayudantía ");
        StdOut.println("[2] Capstone ");
        StdOut.println("[3] Práctica Pre-Profesional ");
        int tipo = ValidadorEntrada.lecturaEnteros("Ingrese una opción: ");

        StdOut.println("Ingresa el título: ");
        String titulo = StdIn.readLine();

        StdOut.println("Ingresa la descripción: ");
        String descripcion = StdIn.readLine();

        int duracionDias = ValidadorEntrada.lecturaEnteros("Ingrese la duración en días:");

        if (tipo == 1){
            //Ayudantía
            StdOut.println("Asignatura: ");
            String asignatura = StdIn.readLine();

            StdOut.println("Rol de ayudante: ");
            String rol = StdIn.readLine();

            int horasSemanales = ValidadorEntrada.lecturaEnteros("Horas semanales: ");

            StdOut.println("Promedio mínimo: ");
            double promedioMinimo = Double.parseDouble(StdIn.readLine());

            StdOut.println("Tipo de ayudantía (taller, ayuda en clases): ");
            String tipoAyudantia = StdIn.readLine();

            return new Ayundantia(codigo, titulo, descripcion, duracionDias,
                    asignatura, rol, horasSemanales, promedioMinimo, tipoAyudantia);

        } else if(tipo == 2){
            //Capstone
            StdOut.println("Fecha de inicio (dd-MM-yyyy): ");
            String fechaTexto = StdIn.readLine();
            LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            StdOut.println("Empresa: ");
            String empresa = StdIn.readLine();

            StdOut.println("Guía: ");
            String guia = StdIn.readLine();

            StdOut.println("Tipo de proyecto: ");
            String tipoProyecto = StdIn.readLine();

            int duracionMeses = ValidadorEntrada.lecturaEnteros("Duración en meses: ");

            StdOut.println("Carreras habilitadas (separadas por coma): ");
            String[] carreras = StdIn.readLine().split(",");

            int cantidadEstudiantes = ValidadorEntrada.lecturaEnteros("Cantidad mínima de estudiantes: ");

            return new Capstone(codigo, titulo, descripcion, duracionDias,
                    fecha, empresa, guia, tipoProyecto, duracionMeses, carreras, cantidadEstudiantes);

        } else if (tipo == 3) {
            //Práctica Pre-Profesional
            StdOut.println("Fecha de inicio (dd-MM-yyyy): ");
            String fechaTexto = StdIn.readLine();
            LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            StdOut.println("Empresa: ");
            String empresa = StdIn.readLine();

            StdOut.println("Guía: ");
            String guia = StdIn.readLine();

            StdOut.println("¿Tiene remuneración? (Si/No): ");
            String remunaracion = StdIn.readLine();
            boolean tieneRemuneracion = remunaracion.equalsIgnoreCase("Si");

            return new PracticaPreProfesional(codigo, titulo, descripcion, duracionDias,
                    fecha, empresa, guia, tieneRemuneracion);
        }

        StdOut.println("Tipo de oferta no válido. Operación cancelada.");
        return null;
    }

    private void buscarOferta(){
        StdOut.println("¿Cómo desea buscar la Oferta?");
        StdOut.println("[1] Por código único");
        StdOut.println("[2] Por título");
        StdOut.println("[3] Volver al menú");

        int opcion = ValidadorEntrada.lecturaEnteros("Seleccione una opción:");

        if (opcion == 1){
            StdOut.println("Ingrese el código de la oferta:");
            String codigo = StdIn.readLine();

            OfertaAcademica ofertaAcademica = sistemaOfertas.buscarOferta(codigo);

            if (ofertaAcademica != null){
                StdOut.println("Oferta encontrada: ");
                StdOut.println(ofertaAcademica);
            }else {
                StdOut.println("La oferta no existe");
            }
        } else if (opcion == 2) {
            StdOut.println("Ingrese el título de la oferta: ");
            String titulo = StdIn.readLine();

            OfertaAcademica ofertaAcademica = sistemaOfertas.buscarOfertaPorTitulo(titulo);

            if (ofertaAcademica !=null){
                StdOut.println("Oferta encontrada: ");
                StdOut.println(ofertaAcademica);
            }else{
                StdOut.println("No se encontró oferta con ese título");
            }
        }else {
            StdOut.println("Ingrese una opción valida");
        }
    }

    private void editarOferta(){
        StdOut.println("Ingrese el código de la oferta que desea editar: ");
        String codigo = StdIn.readLine();

        OfertaAcademica existente = sistemaOfertas.buscarOferta(codigo);

        if (existente == null){
            StdOut.println("No se encontró ninguna oferta con ese código.");
            return;
        }
        StdOut.println("Oferta actual: ");
        StdOut.println(existente);

        StdOut.println("Ingresa los nuevos datos de la oferta: ");
        OfertaAcademica nuevo = construirNuevaOferta(codigo);

        if (nuevo != null){
            try{
                sistemaOfertas.editarOferta(codigo, nuevo);
                StdOut.println("Oferta actualizada con éxito.");
            }catch (Exception e){
                StdOut.println("Error: " + e.getMessage());
            }
        }
    }

    private void eliminarOferta(){
        StdOut.println("Ingrese el código de la oferta que desea eliminar:");
        String codigo = StdIn.readLine();

        OfertaAcademica oferta = sistemaOfertas.buscarOferta(codigo);

        if (oferta == null){
            StdOut.println("No se encontró ninguna oferta con ese código.");
            return;
        }
        StdOut.println("Oferta encontrada: ");
        StdOut.println(oferta);

        StdOut.println("¿Está seguro de que sea eliminar esta oferta? (si/no): ");
        String confirmacion = StdIn.readLine();

        if (confirmacion.equalsIgnoreCase("Si")){
            try{
                sistemaOfertas.eliminarOferta(codigo);
                StdOut.println("Oferta eliminada exitosamente.");
            }catch (Exception e){
                StdOut.println("Error al eliminar la oferta: " + e.getMessage());
            }
        }else {
            StdOut.println("Operación cancelada");
        }
    }

    private void editarPerfil() {
        StdOut.println("::: EDICIÓN DE PERFIL :::");

        StdOut.println("Ingrese el nuevo nombre:");
        String nuevoNombre = StdIn.readLine();

        StdOut.println("Ingrese el nuevo correo:");
        String nuevoCorreo = StdIn.readLine();

        try {
            sistemaOfertas.editarPerfil(nuevoNombre, nuevoCorreo);
            StdOut.println("Perfil actualizado exitosamente.");
        } catch (Exception e) {
            StdOut.println("Error al editar el perfil: " + e.getMessage());
        }
    }
}
