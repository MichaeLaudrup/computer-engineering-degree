import java.util.ArrayList; 
/**
 * Write a description of class Empleado here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public  class Empleado extends Persona
{
    // Esta variable controlará la clases de privilegios que tiene este empleado en el sistema

    private String tipoEmpleado; 
    /**
     * Creacion de un empleado
     * @param dni del empleado
     * @param nombre del empleado
     * @param nivel de privilegio
     */
    public Empleado(String dni, String nombre, String apellido, String domicilio, String telefono, String tipoEmpleado)
    {
       super( dni, nombre, apellido, domicilio, telefono);
       this.tipoEmpleado = tipoEmpleado; 
    }
    
    /**
     * Método que devuelve nivel de privilegios del empleado
     * en el sistema.
     * @return nivel de privilegios
     */
    public String getTipoEmpleado()
    {
       return tipoEmpleado;     
    } 
    public String toString(){
        return super.toString() + "\nTipo de empleado: " + tipoEmpleado ; 
    }
    
}
