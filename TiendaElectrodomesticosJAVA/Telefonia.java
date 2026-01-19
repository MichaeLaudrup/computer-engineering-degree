
/**
 * Write a description of class Telefonia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Telefonia extends Electrodomestico
{  
    //breve descripción del sistema operativo.
    String sistemaOper; 
    //Tipo de cargador del movil
    String tipoCargador; 
    public Telefonia (String[] elementosBasicos, String sistemaOper, String tipoCargador ){
        super(elementosBasicos); 
        setCategoria("TELEFONIA");
        this.sistemaOper = sistemaOper; 
        this.tipoCargador = tipoCargador; 
    }
    public Telefonia(String identificador, String marca, String modelo, String color, double precio, int stock, String sistemaOper, String tipoCargador){
        super(identificador, marca, modelo, color, precio, stock);
        this.sistemaOper = sistemaOper;
        this.tipoCargador = tipoCargador; 
    }
    
      public String getSistemaOper(){
        return sistemaOper;
    }
    
    public void setSistemaOper(String sistemaOper ){
        this.sistemaOper = sistemaOper; 
    }
    
    public String getTipoCargador(){
        return tipoCargador;
    }
    
    public void setTipoCargador(String resolucion){
        this.tipoCargador = resolucion; 
    }
    public String toString(){
        return super.toString() + "\n Sistema operativo del móvil: " + sistemaOper + "\n Tipo de cargador del móvil: " + tipoCargador + "\n ===================================================\n"; 
    }
    
}
