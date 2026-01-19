
/**
 * Write a description of class Informatica here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Informatica extends Electrodomestico
{
    //almacenamiento en GB
    private Double almacenamiento;
    //numero de entradas del dispositivo
    private int nEntradas; 
    
    public Informatica(String[] elementosBasicos, String[] elementosInformatica)
    {
         super(elementosBasicos);
         this.almacenamiento = Double.parseDouble(elementosInformatica[0]);
         this.nEntradas = Integer.parseInt(elementosInformatica[1]); 
    }
    public Informatica(String identificador, String marca, String modelo, String color, double precio, int stock, double almacenamiento, int nEntradas){
        super(identificador, marca, modelo, color, precio, stock); 
        this.almacenamiento = almacenamiento; 
        this.nEntradas = nEntradas; 
    }
        public double getAlmacenamiento(){
        return almacenamiento;
    }
    
    public int getnEntradas(){
        return nEntradas;
    }
    public void setAlmacenamiento (double almacenamiento){
        this.almacenamiento = almacenamiento; 
    }

    public void setEntradas( double entrada){
        this.nEntradas = nEntradas; 
    }
    /**
     * Imprime los atributos propios de la clase informatica
     */
    public String toString(){
        return super.toString() + "\n Almacenamiento(GBytes): " + almacenamiento + "\n Número de entradas: "+ nEntradas + "\n =================================================== \n"; 
    }   
}
