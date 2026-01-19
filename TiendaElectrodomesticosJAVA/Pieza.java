
/**
 * Write a description of class Piezas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pieza
{
    //cantidad necesaria de piezas.
    int cantidad;
    //Nombre de pieza.
    String nombrePieza;
    //Electrodomestico relacionado con la pieza necesaria.
    Electrodomestico electro; 
    //tecnico que solicita la pieza.
    Tecnico tec; 
    /**
     * Constructor de un objeto de tipo pieza.
     */
    public Pieza(int cantidad, String nombrePieza, Electrodomestico electro, Tecnico tec)
    {
       this.cantidad = cantidad;
       this.nombrePieza = nombrePieza; 
       this.electro = electro; 
       this.tec = tec; 
    }
}
