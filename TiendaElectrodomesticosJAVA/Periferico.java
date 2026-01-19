
/**
 * Write a description of class Impresora here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Periferico extends Informatica
{
    // tipo de periferico: ej: impresora, ratón, teclado; 
    String periferico;
    //velocidad de transferencia de datos
    double velocidad;
    
    public Periferico (String[] elementosBasicos, String[] elementosInformatica, String periferico, double velocidad)
    {
       super(elementosBasicos, elementosInformatica);
       setCategoria("PERIFERICO");
       this.periferico = periferico;
       this.velocidad = velocidad; 
    }
    public Periferico(String identificador, String marca, String modelo, String color, double precio, int stock, double almacenamiento, int nEntradas, String periferico, double velocidad ){
        super(identificador, marca, modelo, color, precio, stock, almacenamiento, nEntradas);
        setCategoria("PERIFERICO");
        this.periferico = periferico; 
        this.velocidad = velocidad; 
    }
    public String getPeriferico(){
        return periferico;
    }
    
    public double getVelocidad(){
        return velocidad;
    }
    public void setPeriferico (String periferico){
        this.periferico = periferico; 
    }

    public void setVelocidad( double velocidad){
        this.velocidad = velocidad; 
    }
     public String toString(){
        return super.toString() + "\n Tipo de periférico: "+ periferico + "\n Valocidad de transferencia de datos: "+ velocidad + "\n ==================================================="; 
    }
}
