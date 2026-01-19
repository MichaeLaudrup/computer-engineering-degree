import java.util.Date;
import java.util.ArrayList; 
/**
 * Write a description of class Compra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Factura
{
    // instance variables - replace the example below with your own
    private int x;
    private ArrayList<FichaCompra> Adquisiciones; 
    private Date FechaAdquisicion; 
    /**
     * Constructor del objeto "Compra" que contendrá
     * fecha adquisición y producto adquirido
     */
    public Factura(String identificadorProducto, Date FechaAdquisicion)
    {
      
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
