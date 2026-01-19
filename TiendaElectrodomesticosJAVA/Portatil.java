
/**
 * Write a description of class Portatil here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Portatil extends Informatica
{
    // instance variables - replace the example below with your own
    private String procesador;
    private String grafica; 

    public Portatil(String[] elementosBasicos, String[] elementosInformatica, String procesador, String grafica)
    {
        super(elementosBasicos, elementosInformatica); 
        setCategoria("PORTATIL");
        this.procesador = procesador; 
        this.grafica = grafica; 
    }
    public Portatil(String identificador, String marca, String modelo, String color, double precio, int stock, double almacenamiento, int nEntradas, String procesador, String grafica ){
        super(identificador, marca, modelo, color, precio, stock, almacenamiento, nEntradas);
        setCategoria("PORTATIL");
        this.procesador = procesador; 
        this.grafica = grafica;  
    }
    public String getProcesador(){
        return procesador;
    }
    
    public String getGrafica(){
        return grafica;
    }
    public void setProcesador (String procesador){
        this.procesador = procesador; 
    }

    public void setGrafica( String grafica){
        this.grafica = grafica; 
    }
    public String toString(){
        return super.toString() + "\n Procesador: "+ procesador + "\n Tarjeta grafica: "+ grafica + "\n ==================================================="; 
    }
}
