import java.util.HashMap;
import java.util.ArrayList;  
/**
 * Write a description of class GestorTienda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GestorTienda
{
    private static HashMap<String,FichaReparacion> reparacionesProcesadas = new HashMap<>(); 
    private static HashMap<String,FichaReparacion> reparacionesPendientes = new HashMap<>();
    private static ArrayList<Pieza> piezas = new ArrayList<>(); 
    
    public GestorTienda()
    {
        
    }
    
    public static void addReparacionProcesada(FichaReparacion ficha){
        reparacionesProcesadas.put(ficha.getID(),ficha);
        reparacionesPendientes.remove(ficha.getID());
    }
    public static void addReparacionPendiente(FichaReparacion ficha){
        reparacionesPendientes.put(ficha.getID(),ficha);
    }
    public static void addPiezaPendiente(Pieza nuevaPieza){
        piezas.add(nuevaPieza);
    }
    public static void imprimeHistorialRepProcesadas(){
        for(FichaReparacion x : reparacionesProcesadas.values()){
            System.out.println(x);
        }
    }
}
