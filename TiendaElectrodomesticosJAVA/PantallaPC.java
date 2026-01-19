
/**
 * Write a description of class CamaraReflex here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PantallaPC extends Imagen
{
    String tipoConector; 
    boolean tactil; 
    public PantallaPC(String[] elementosBasicos, String[] elementosImagen, String tipoConector, boolean tactil)
    { 
        super(elementosBasicos, elementosImagen);
        setCategoria("PANTALLAPC");
        this.tipoConector = tipoConector;
        this.tactil = tactil; 
    }
    public PantallaPC(String identificador, String marca, String modelo, String color,  double precio, int stock, int pulgadas, String resolucion, int frecuencia, String tipoConector, boolean tactil){
        super(identificador, marca, modelo, color, precio, stock, pulgadas, resolucion, frecuencia); 
        setCategoria("PANTALLAPC");
        this.tipoConector = tipoConector;
        this.tactil = tactil; 
    }
    public String getConector(){
        return tipoConector;
    }
    public void setConector(String tipoConector){
        this.tipoConector = tipoConector; 
    }
    public boolean getTactil(){
        return tactil;
    }
    public void setTactil(boolean tactil){
        this.tactil = tactil; 
    }
    public String toString(){
        String answer = (tactil) ? "Si" : "No"; 
        return super.toString() + "\n Tipo de conector: " + tipoConector + "\n Pantalla táctil: " + answer + "\n ===================================================" ;  
    }
}
