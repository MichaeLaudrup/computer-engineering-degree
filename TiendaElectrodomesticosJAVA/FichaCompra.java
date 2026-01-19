import java.util.Date; 
import java.util.Calendar; 
/**
 * Write a description of class Adquisicion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FichaCompra
{
    // instance variables - replace the example below with your own
    private int iDAdquisicion; 
    private Electrodomestico producto; 
    private Date fecha;
    private int devoluciones; 
    private int cantidadAdquirida; 
    private int enReparacion; 

    /**
     * Constructor for objects of class Adquisicion
     */
    public FichaCompra(int iDAdquisicion, Date fecha, Electrodomestico producto, int cantidadAdquirida)
    {
       this.iDAdquisicion = iDAdquisicion; 
       this.fecha = fecha; 
       this.producto = producto; 
       this.cantidadAdquirida = cantidadAdquirida;
       devoluciones = 0; 
       enReparacion = 0; 
    }
    public void setDevoluciones(int nuevaCantidad){
       devoluciones += nuevaCantidad; 
    }
    
    public Electrodomestico getElectro(){
        return producto; 
    }
    public int getNumReparaciones(){
        return enReparacion; 
    }
    public void setNumReparaciones(int enReparacion){
        this.enReparacion = enReparacion; 
    }
    public int getIDAdquisicion(){
        return iDAdquisicion; 
    }
    public Electrodomestico getProducto(){
        return producto; 
    }
    public Date getFecha(){
        return fecha; 
    }
    public int getCantidad(){
        return cantidadAdquirida; 
    }
    public void setCantidad( int cantidad){
        cantidadAdquirida = cantidad; 
    }
    public int getDevoluciones(){
        return devoluciones; 
    }
    public String toString(){
        String result =" =================================================== "; 
        result += "\nIdentificador de ficha compra: " + iDAdquisicion;
        result += "\nTipo de electrodoméstico adquirido: " + producto.getCategoria().toLowerCase() +" "+  producto.getMarca() +" "+ producto.getModelo();
        result += "\nFecha de adquisición: "+ fecha.getDate() + "/" + (fecha.getMonth()+1) + "/" + (fecha.getYear()+1900);
        result += "\nCantidad adquirida (se descuentan las devoluciones): " + cantidadAdquirida;
        result += "\nCantidad de devoluciones: " + devoluciones;
        result += "\nCantidad en reparación: " + enReparacion; 
        return result; 
    }
    
}
