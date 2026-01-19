import java.util.HashMap; 
import java.util.Date;
import java.util.Random; 
/**
 * Write a description of class Tecnico here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tecnico extends Empleado
{
    //Generador de identificadores para las fichas de recuperacion; 
    private Random generadorIdentificadores; 
    private HashMap<String, FichaReparacion> reparacionesPendientes; 
    private HashMap<String,FichaReparacion> historialReparaciones; 
    
    public Tecnico(String dni, String nombre,String apellido, String domicilio, String telefono)
    {
         super(dni, nombre,apellido, domicilio,  telefono, "tecnico"); 
         reparacionesPendientes = new HashMap<String, FichaReparacion>(); 
         generadorIdentificadores = new Random(); 
    }
    public void eliminarReparacion(String identificador){
        reparacionesPendientes.remove(identificador); 
    }
    public boolean existeReparacion(String identificador){
        if(reparacionesPendientes.containsKey(identificador)){
            return true; 
        }else{
            return false; 
        }
    }
    public void addFichaReparacion( String descripcion, Cliente cliente, Tecnico tecni, Electrodomestico producto, Date recepcion, boolean garantia ){
        String identificador = String.valueOf((generadorIdentificadores.nextInt(3000))); 
        String estado = "PENDIENTE DE REVISIÓN DE TÉCNICO"; 
        reparacionesPendientes.put(identificador, new FichaReparacion(identificador,descripcion, tecni , cliente, producto, recepcion, estado, garantia)); 
    }
    
    public void printReparaciones(){
        for(FichaReparacion x : reparacionesPendientes.values()){
            System.out.println(x); 
        }
    } 
    
    public FichaReparacion getFicha(String key){
     return reparacionesPendientes.get(key); 
    }
}
