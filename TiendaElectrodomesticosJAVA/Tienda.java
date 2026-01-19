import java.util.HashMap; 
/**
 * Write a description of class Tienda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tienda
{
   
    /**
     * Ejecución del Programa principal
     */
    public static void main (String[] args){
       //Creacion de gestor de usuarios y electrodomesticos
       GestorClientes miGestorClientes = new GestorClientes(); 
       GestorEmpleados miGestorEmpleados = new GestorEmpleados(); 
       GestorElectro gestorElectro = new GestorElectro();    
       //Creacion de Interfaz textual
       InterfazTextual miInterfazTextual = new InterfazTextual(miGestorClientes, miGestorEmpleados, gestorElectro);
       boolean finalizado = false; 
       
            while(!finalizado){
                miInterfazTextual.empezar(); 
            }
      
    }
}
