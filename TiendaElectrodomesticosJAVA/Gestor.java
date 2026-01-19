import java.util.HashMap;
import java.util.Scanner;  
/**
 * Write a description of class Gestor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gestor<T>
{   
    private HashMap<String, T> miMapa; 
    
    public Gestor()
    {
       miMapa = new HashMap<String,T>();   
    }
    public HashMap<String,T> getMapa(){
        return miMapa; 
    }
    /**
     * Método que devuelve un tipo T según la llave
     * de formato String recibida
     * @param llave del mapa(String) 
     */
    public T getElemento(String key){       
         return miMapa.get(key);
    }
} 

