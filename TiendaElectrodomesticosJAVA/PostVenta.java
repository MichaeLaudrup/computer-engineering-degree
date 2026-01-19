
/**
 * Write a description of class PostVenta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PostVenta extends Empleado
{
    /**
     * Crean un empleado de tipo postVenta
     * @param dni empleado
     * @param nombre empleado
     */
    public PostVenta(String dni, String nombre, String apellido, String domicilio, String telefono)
    {
         super(dni, nombre, apellido, domicilio, telefono, "postventa"); 
    }
}
