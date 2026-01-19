import java.util.Date; 
/**
 * Write a description of class Operaciones here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Operacion
{
    Empleado ejecutor;
    Cliente afectado; 
    String tipoOperacion; 
    Electrodomestico electroInvolucrado; 
    Date fechaOperacion;
    public Operacion(Empleado ejecutor, Cliente afectado, String tipoOperacion, Electrodomestico electroInvolcrado, Date fechaOperacion ){
    this.ejecutor = ejecutor; 
    this.afectado = afectado; 
    this.tipoOperacion = tipoOperacion; 
    this.electroInvolucrado = electroInvolcrado; 
    this.fechaOperacion = fechaOperacion; 
    }
}
