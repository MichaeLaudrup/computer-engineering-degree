/**
 * Abstract class Persona - Esta clase es un persona
 * 
 * @author: 
 * Date: 
 */
public abstract class Persona
{
    // instance variables - replace the example below with your own
    private String dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String telefono;
    private boolean alta;
    
    /**
     * Crear una persona en caso de ser cliente
     * @param dni cliente
     * @param nombre cliente
     * @param apellido cliente
     * @param domicilio cliente
     * @param telefono cliente
     */
    public Persona(String dni, String nombre, String apellido, String domicilio, String telefono){
        this.dni = dni; 
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.telefono = telefono; 
        alta = true; 
    } 
    /**
     * Crear una persona en caso de ser empleado
     * @param dni empleado
     * @param nombre empleado
     */
    public Persona(String dni, String nombre){
        this.dni = dni; 
        this.nombre = nombre;
    }
    
     /**
     * Metodó que devuelve el DNI del cliente
     * 
     * @return DNI del cliente
     */
    public String getDni(){
        return dni; 
    }
    
    /**
     * Método que establece o reemplaza DNI del cliente
     * 
     * @param DNI a sustituir
     */
    public void setDni(String nuevoDni) {
        dni = nuevoDni; 
    }
    
    /**
     * Metodó que devuelve el nombre del cliente
     * 
     * @return Nombre del cliente
     */
    public String getNombre(){
        return nombre; 
    }
    
    /**
     * Método que establece o reemplaza el nombre del cliente
     * 
     * @param Nombre a sustituir
     */
    public void setNombre(String nuevoNombre) {
        nombre = nuevoNombre; 
    }
    
     /**
     * Metodó que devuelve el apellido del cliente
     * 
     * @return Apellido del cliente
     */
    public String getApellido(){
        return apellido; 
    }
    
    /**
     * Método que establece o reemplaza el apellido del cliente
     * 
     * @param Apellido a sustituir
     */
    public void setApellido(String nuevoApellido) {
        apellido = nuevoApellido; 
    }

    /**
     * Metodó que devuelve el domicilio del cliente
     * 
     * @return Domicilio del cliente
     */
    public String getDomicilio(){
        return domicilio; 
    }
    
    /**
     * Método que establece o reemplaza el domicilio del cliente
     * 
     * @param Domicilio a sustituir
     */
    public void setDomicilio(String nuevoDomicilio) {
        domicilio = nuevoDomicilio; 
    }
    /**
     * Metodó que devuelve número de telefono del cliente
     * 
     * @return Numero de telefono del cliente
     */
    public String getNumeroTelefono(){
        return telefono; 
    }
    
    /**
     * Cambiar el número de telefono del cliente
     * 
     * @param Número de telefono a sustituir
     */
    public void setTelefono(String NuevoTelefono) {
        telefono = NuevoTelefono; 
    }
    public String toString(){
        String resultado = "==============="; 
        String resultado = "(dni: "+ dni + " || nombre: " + nombre + " || Apellidos: " +  apellido;
        String estado = (alta == true) ? "activo" : "inactivo";
        resultado+= " || domicilio: " + domicilio + " || telefono:  " + telefono +" || Estado en el sistema: " + estado +")";
        return resultado; 
    }
     /**
     * Metodó que devuelve un booleano que determina
     * si el usuario esta dado de alta o de baja en 
     * el sistema. 
     * @return true: si esta dado de alta false: esta de baja en el sistema
     */
    public boolean getAlta(){
        return alta; 
    }
    
    /**
     * Cambiar el estado del usuario, si esta de alta
     * o de baja en el sistema. 
     * 
     * @param Actualización de alta o baja en el sistema
     */
    public void setAlta(boolean alta) {
        this.alta = alta; 
    }
}
