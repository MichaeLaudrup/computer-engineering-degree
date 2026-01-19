
/**
 * Write a description of class Financiacion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Financiero extends Empleado
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Crean un empleado de tipo Financiero
     * @param dni empleado
     * @param nombre empleado
     */
    public Financiero(String dni, String nombre, String apellido, String domicilio, String telefono)
    {
         super(dni, nombre,apellido, domicilio,  telefono, "financiero"); 
    }
}


