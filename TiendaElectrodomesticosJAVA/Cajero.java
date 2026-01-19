
/**
 * Write a description of class cajero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cajero extends Empleado
{

    /**
     * Crean un empleado de tipo cajero
     * @param dni empleado
     * @param nombre empleado
     */
    public Cajero(String dni, String nombre, String apellido, String domicilio, String telefono)
    {
         super(dni, nombre, apellido, domicilio, telefono, "cajero"); 
    }
}
