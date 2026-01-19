
/**
 * Write a description of class Sonido here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sonido extends Electrodomestico
{
    String tipoBateria;
    String tipoConector; 
    
    public Sonido(String[] elementosBasicos, String[] elementosSonido)
    {
        super( elementosBasicos);
        tipoBateria = elementosSonido[0];
        this.tipoBateria = elementosSonido[0]; 
        this.tipoConector = elementosSonido[1];    
    }
    public Sonido(String identificador, String marca, String modelo, String color, double precio, int stock, String tipoBateria, String tipoConector){
        super(identificador, marca, modelo, color, precio, stock);
        this.tipoBateria = tipoBateria; 
        this.tipoConector = tipoConector; 
    }
    public String getTipoBateria(){
        return tipoBateria;
    }
    public void setTipoBateria(String tipoBateria){
        this.tipoBateria = tipoBateria;
    }
      public String getTipoConector(){
        return tipoConector;
    }
    public void setTipoConector(String tipoConector){
        this.tipoConector = tipoConector;
    }
    public String toString(){
        return super.toString() + "\n Tipo de Batería:  " + tipoBateria +"\n Tipo de conector: " + tipoConector ; 
    }
}
