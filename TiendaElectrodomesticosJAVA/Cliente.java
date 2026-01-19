import java.util.ArrayList;
import java.util.Date;  
/**
 * Write a description of class Cliente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cliente extends Persona
{
    // instance variables - replace the example below with your own
    private ArrayList<Factura> historialFacturas; 
    private ArrayList<FichaCompra> historialCompras; 
    private ArrayList<Operacion> historialOperaciones; 
    private ArrayList<Promocion> buzonPromociones; 
    private String Promociones;
    private boolean garantia; 
    private float NominaMensual; 
    int nCompras; 
    int contadorOperacion; 
    /**
     * Creacion de cliente
     * @param dni cliente
     * @param nombre cliente
     * @param apellido cliente
     * @param domicilio cliente
     * @param telefono cliente 
     */
    public Cliente(String dni, String nombre, String apellido, String domicilio, String telefono)
    {
        super(dni, nombre, apellido, domicilio,telefono);
        historialOperaciones = new ArrayList<Operacion>(); 
        historialCompras= new ArrayList<FichaCompra>(); 
        historialFacturas = new ArrayList<Factura>();
        nCompras  = 1; 
        contadorOperacion = 1; 
    }
    /**
     * método  que devuelve el historial de facturas de un cliente
     * @return lista facturas
     */
    public ArrayList<Factura> getHistorialFacturas(){
        return historialFacturas;   
    }
    public boolean existeFichaCompra(int idCompra){
        boolean respuesta; 
        //Se parte de la idea de que el número de identificador de una ficha compra no debe exceder el tamaño del historial de fichas de compras.
        if(nCompras >= idCompra){
            respuesta = true; 
        }else{
            respuesta = false; 
        }
        return respuesta; 
    }
    
    /**
     * método  que cambia historial de facturas de un cliente
     * @param  historial facturas actualizado
     */
    public void setHistorialFacturas(ArrayList<Factura> historialFacturas){
        this.historialFacturas = historialFacturas;    
    }
    /**
     * método que devuelve historial de productos adquiridos
     * @return  historial de productor
     */
    public ArrayList<FichaCompra> getHistorialCompras(){
        return historialCompras;     
    }
        /**
     * método  que cambia historial de operaciones de un cliente
     * @param  historial facturas actualizado
     */
    public void setHistorialoperaciones(ArrayList<Operacion> historialCompra){
        this.historialOperaciones = historialOperaciones;    
    }
    /**
     * método que devuelve historial de operaciones recibidas
     * @return  historial de productor
     */
    public ArrayList<Operacion> getHistorialOperaciones(){
        return historialOperaciones;     
    }
       /**
     * método  que cambia historial de facturas de un cliente
     * @param  historial productos actualizado
     */
    public void setHistorialCompras(ArrayList<FichaCompra> historialCompras){
        this.historialCompras = historialCompras;    
    } 
    public void addFichaCompra(Date fecha, Electrodomestico producto, int cantidadAdquirida){
        historialCompras.add(new FichaCompra(nCompras,fecha, producto, cantidadAdquirida));
        nCompras++; 
    }
    public void addOperacion(Empleado ejecutor, Cliente afectado, String tipoOperacion, Electrodomestico electro, Date fecha){
        historialOperaciones.add(new Operacion(ejecutor,afectado,tipoOperacion,electro,fecha)); 
        contadorOperacion++; 
    }
    public FichaCompra getFichaCompra(int indice){
        return historialCompras.get(indice - 1); 
    }
    public void imprimeAdquisiciones(){
        if(historialCompras.size() > 0){
            for(FichaCompra x : historialCompras){
                System.out.println(x); 
            }
        }else{
            System.out.println("Este cliente no ha realizado ninguna compra...");
        }
    }
    
}
