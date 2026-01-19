
/**
 * Write a description of class Nevera here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nevera extends Hogar
{
    //temperatura minima de la nevera
    double t_min;
    //número de cajones de la nevera
    int nCajones;  

    public Nevera(String[] elementosBasicos, String eficiencia, double t_min, int nCajones )
    {
       super(elementosBasicos, eficiencia);
       setCategoria("NEVERA");
       this.t_min = t_min;
       this.nCajones = nCajones; 
    }
    public Nevera(String identificador, String marca, String modelo, String color, double precio, int cantidad, String eficiencia, double t_min, int nCajones){
        super(identificador, marca, modelo, color, precio, cantidad, eficiencia);
        setCategoria("NEVERA");
        this.t_min = t_min; 
        this.nCajones = nCajones; 
    }
      public double getT_min(){
        return t_min;
    }
    public void setT_min(double t_min){
        this.t_min = t_min; 
    }
     public int getnCajones(){
        return nCajones;
    }
     public void setnCajones(int  nCajones){
        this.nCajones = nCajones; 
    }
    public String toString(){
        return super.toString() + "\n Temperatura Mínima: " + t_min +"º"+ "\n Número de cajones: " + nCajones + "\n ===================================================\n";
    }
}
