package Modelo;

public class UsuarioVo {

    // Campos de la clase
    private String nombre;
    private String documento;
    private String profesion;
    private int edad;
    private String direccion;
    private String telefono;
    private int tipo;
    private String password;
    private String username;
    private int estado;

    // Definir constantes para el estado
    public static final int ESTADO_ACTIVO = 1;
    public static final int ESTADO_INACTIVO = 2;

    public UsuarioVo(String nombre, String documento, String profesion, int edad,
                     String direccion, String telefono, int tipo, String password,
                     String username, int estado) {
        this.nombre = nombre;
        this.documento = documento;
        this.profesion = profesion;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.password = password;
        this.username = username;
        this.estado = estado;
    }

    public UsuarioVo() {

    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    // Método que devuelve si el usuario está activo
    public boolean estaActivo() {
        return this.estado == ESTADO_ACTIVO;
    }

    // Método que activa al usuario
    public void activar() {
        this.estado = ESTADO_ACTIVO;
    }

    // Método que desactiva al usuario
    public void desactivar() {
        this.estado = ESTADO_INACTIVO;
    }
}
