import java.util.Scanner; 
/**
 * Write a description of class SmartTv here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SmartTv extends Imagen
{
    int puertoUsb;  
    boolean camara; 
    
    public SmartTv(String[] elementosBasicos, String[] elementosImagen, int puertosUsb, boolean camara)
    {   
        super(elementosBasicos, elementosImagen);  
        setCategoria("SMARTTV");
        this.puertoUsb = puertosUsb; 
        this.camara = camara;  
    }
    public SmartTv(String identificador, String marca, String modelo, String color,  double precio, int stock, int pulgadas, String resolucion, int frecuencia, int puertoUsb, boolean camara){
        super(identificador, marca, modelo, color, precio, stock, pulgadas, resolucion, frecuencia);
        setCategoria("SMARTTV");
        this.puertoUsb = puertoUsb; 
        this.camara = camara; 
    }
    public int getPuertos(){
        return puertoUsb;
    }
    
    public boolean getCamara(){
        return camara;
    }

    public void setPuertosUsb(int puertoUsb){
        this.puertoUsb = puertoUsb; 
    }

    public void setCamara( boolean camara){
        this.camara = camara; 
    }
    public String toString(){
        String answer = (camara == true) ? "Si" : "No";
        String tvToString =  "\n Número de puertos Usb: " + puertoUsb + "\n Disposición de cámara: " + answer + "\n ===================================================\n";  
        return super.toString() + tvToString;     
    }
}
