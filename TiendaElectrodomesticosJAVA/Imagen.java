
/**
 * Write a description of class Imagen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Imagen extends Electrodomestico
{
   
    private int pulgadas;
    private String resolucion; 
    private int frecuencia;
    /**
     * Constructor for objects of class Imagen
     */
    public Imagen(String[] elementosBasicos, String[] elementosImagen)
    {
        super(elementosBasicos);
        pulgadas = Integer.parseInt(elementosImagen[0]);
        resolucion = elementosImagen[1];
        frecuencia = Integer.parseInt(elementosImagen[2]);
    }
    public Imagen(String identificador, String marca, String modelo, String color, double precio, int stock, int pulgadas, String resolucion, int frecuencia){
        super(identificador, marca, modelo, color, precio, stock);
        this.pulgadas = pulgadas; 
        this.resolucion = resolucion; 
        this.frecuencia = frecuencia; 
    }
     public int getPulgadas(){
        return pulgadas;
    }
    public void setPulgadas(int pulgadas ){
        this.pulgadas = pulgadas; 
    }
    public String getResolucion(){
        return resolucion;
    }
    public void setResolucion(String resolucion){
        this.resolucion = resolucion; 
    }
      public int getfrecuencia(){
        return frecuencia;
    }
    public void setFrecuencia(int frecuencia){
        this.frecuencia = frecuencia; 
    } 
    public String toString(){
         String imagen = super.toString() + "\n Pulgadas de la pantalla: " + pulgadas +"\n Resolución de la pantalla: " + resolucion + "\n Frecuencia de la pantalla: " + frecuencia  ;
         return imagen; 
    }
}
