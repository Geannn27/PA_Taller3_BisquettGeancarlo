package cl.ucn.disc.pa.taller3.dominio;

public class Usuario {
    private String nombre;
    private String rut;
    private String correo;
    private String contrasenia;

    public Usuario (String nombre, String rut, String correo, String contrasenia){
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }
    public String getNombre(){
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean validarContrasenia(String clave){
        return this.contrasenia.equals(clave);
    }

    @Override
    public String toString(){
        return  "=========== PERFIL DE USUARIO ===========" + "\n" +
                "Nombre: " + this.nombre + "\n" +
                "Rut: " + this.rut + "\n" +
                "Correo: " + this.correo + "\n" +
                "==============================================";
    }
}
