
/**
 * Write a description of class Hogar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hogar extends Electrodomestico
{
    private String eficiencia;   
    public Hogar(String[] elementosBasicos, String eficiencia)
    {
        super(elementosBasicos); 
        this.eficiencia = eficiencia;
    }
    public Hogar(String identificador, String marca, String modelo, String color, double precio, int cantidad, String eficiencia){
        super(identificador, marca, modelo, color, precio, cantidad);
        this.eficiencia = eficiencia;
    }
    public String getEficiencia(){
        return eficiencia;
    }
    public void setEficiencia (String eficiencia){
        this.eficiencia = eficiencia; 
    }
    public String toString(){
        return super.toString() + "\n Eficiencia energetica del electrodoméstico: " + eficiencia; 
    }
}
