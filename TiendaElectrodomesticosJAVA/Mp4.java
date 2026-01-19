
/**
 * Write a description of class Auriculares here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mp4 extends Sonido
{
    //Nos dice si el Mp4 dispone de pantalla o no
    double capacidad; 
    //No dice si el Mp4 tiene altavoces integrados o no
    String tipoPanel; 
    public Mp4(String[] elementosBasicos, String[] elementosSonido, double capacidad, String tipoPanel)
    {
       super(elementosBasicos, elementosSonido); 
       setCategoria("MP4");
       this.capacidad = capacidad;
       this.tipoPanel = tipoPanel; 
    }
    public Mp4(String identificador, String marca, String modelo, String color, double precio, int stock, String tipoBateria, String tipoConector, double Capacidad, String tipoPanel){
       super(identificador, marca, modelo, color, precio, stock, tipoBateria, tipoConector);
       setCategoria("MP4");
       this.capacidad = capacidad;
       this.tipoPanel = tipoPanel; 
    }
    public double getCapacidad(){
        return capacidad;
    }
    public void setCapacidad(double capacidad){
        this.capacidad = capacidad; 
    }
    public String getTipoPanel(){
        return tipoPanel; 
    }
    public void setTipoPanel (String tipoPanel){
        this.tipoPanel = tipoPanel;
    } 
    public String toString(){
        return super.toString() + "\n Capacidad de almacenamiento(GB): " + capacidad + "\n Tipo de panel: " + tipoPanel + "\n ===================================================\n"; 
    }
}
