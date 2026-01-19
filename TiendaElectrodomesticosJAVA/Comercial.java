
/**
 * Write a description of class comercial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Comercial extends Empleado
{

    /**
     * Crean un empleado de tipo Financiero
     * @param dni empleado
     * @param nombre empleado
     */
    public Comercial(String dni, String nombre,String apellido, String domicilio, String telefono)
    {
         super(dni, nombre,  apellido,  domicilio, telefono, "comercial"); 
    }
}

   