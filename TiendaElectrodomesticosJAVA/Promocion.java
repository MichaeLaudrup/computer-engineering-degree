import java.util.Date;
import java.util.Random;  

public class Promocion
{
    // Fecha de inicio de la promoción.
    private Date fechaInicio;   
    // Fecha de final de la promoción.
    private Date fehcaFinalPromocion; 
    // Descripción de la promoción.
    private String descripcion; 
    //ID de promoción.
    private int ID; 
    //Generador de números aleatorios (necesario para generar el identificador de promocion).
    Random aleatorio; 
    
    public Promocion(){
        aleatorio = new Random(); 
        ID = aleatorio.nextInt(); 
    }
}
