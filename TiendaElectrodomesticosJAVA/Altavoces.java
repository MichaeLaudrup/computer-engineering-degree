
/**
 * Write a description of class MicroCadena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Altavoces extends Sonido
{
    boolean portable; 
    double frecuencia;
    
    public Altavoces(String[] elementosBasicos, String[] elementosSonido, boolean portable, double frecuencia )
    {
        super(elementosBasicos, elementosSonido);
        setCategoria("ALTAVOCES");
        this.portable = portable;
        this.frecuencia = frecuencia; 
    }
    public Altavoces(String identificador, String marca, String modelo, String color, double precio, int stock, String tipoBateria, String tipoConector, boolean portable, double frecuencia  ){
        super(identificador, marca, modelo, color, precio, stock, tipoBateria, tipoConector);
        setCategoria("ALTAVOCES"); 
        this.portable = portable;
        this.frecuencia = frecuencia; 
    }
    public boolean getPortable(){
        return portable;
    }
    public void setPortable(boolean portable){
        this.portable = portable; 
    }
    public double getFrecuencia(){
        return frecuencia; 
    }
    public void setFrecuencia (double frecuencia){
        this.frecuencia = frecuencia;
    } 
    public String toString(){
        String answer = (portable) ? "Si" : "No";
        return super.toString()  + "\n Portabilidad: " + answer +" \n Frecuencia de altavoces: " + frecuencia + "\n ===================================================";
    }
}
