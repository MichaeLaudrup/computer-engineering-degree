
/**
 * Write a description of class Administrador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Administrador extends Empleado
{
    /**
     * Crean un empleado de tipo Administrador
     * @param dni empleado
     * @param nombre empleado
     * @param nivel de privilegio en el sistema
     */
    public Administrador(String dni, String nombre, String apellido, String domicilio, String telefono)
    {
         super(dni, nombre, apellido, domicilio, telefono, "administrador"); 
    }
}
