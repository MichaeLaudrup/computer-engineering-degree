
/**
 * Write a description of class Lavadora here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Horno extends Hogar
{
    //Aquí ponemos si el horno es de gas o electrico
    String tipoHorno;
    //Temperatura maxima
    double t_max; 
    public Horno(String[] elementosBasicos, String eficiencia, String tipoHorno, double t_max)
    {
           super(elementosBasicos, eficiencia); 
           setCategoria("HORNO");
           this.tipoHorno = tipoHorno;
           this.t_max = t_max; 
    }
    public Horno(String identificador, String marca, String modelo, String color, double precio, int cantidad, String eficiencia, String tipoHorno, double t_max){
        super(identificador, marca, modelo, color, precio, cantidad, eficiencia);
        setCategoria("HORNO");
        this.tipoHorno = tipoHorno; 
        this.t_max = t_max;  
    }
    public String getTipoHorno(){
        return tipoHorno;
    }
    
    public double getVelocidad(){
        return t_max;
    }
    public void setTipoHorno (String tipoHorno){
        this.tipoHorno = tipoHorno; 
    }

    public void setT_max( double t_max){
        this.t_max = t_max; 
    }
    public String toString(){
        return super.toString() + "\n Tipo de horno: "+ tipoHorno + "\n Temperatura máxima del horno: " + t_max + "º" + "\n ===================================================\n" ; 
    }
}
