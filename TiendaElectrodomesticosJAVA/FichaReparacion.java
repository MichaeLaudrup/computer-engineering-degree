import java.util.Date; 
/**
 * Write a description of class FichaDeReparacion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FichaReparacion
{
    
    private String id; 
    private String estado; 
    private String descripcion; 
    private Tecnico reparador; 
    private Cliente cliente;
    private Electrodomestico producto; 
    private Date fechaRecepcion;
    private Date fechaFinalizacion;
    private double coste; 
    private boolean piezas; 
    private boolean garantia; 
    
    public FichaReparacion(String id, String descripcion, Tecnico reparador, Cliente cliente, Electrodomestico producto, Date recepcion, String estado, boolean garantia){
        this.id = id; 
        this.estado = estado; 
        this.producto = producto; 
        this.descripcion = descripcion; 
        this.reparador = reparador; 
        this.cliente = cliente; 
        this.garantia = garantia;
        //Caso de que alla garantía el precio es 0, caso de que no hay garantía se pone un coste negativo hasta que el técnico establezca precio.
        coste = (garantia) ? 0.0 : -1 ; 
        fechaRecepcion = recepcion; 

       
    }
    public String getID(){
        return id; 
    }
    public Date getfechaFinalización(){
        return fechaFinalizacion; 
    }
    public void establecerModificarFechaFinalizacion(int day, int month, int year){
        if(fechaFinalizacion == null){
            fechaFinalizacion = new Date(year,month,day); 
        }else{
            setFechaFinalizacion(day,month,year); 
        }
    }
    public void setFechaFinalizacion(int day, int month, int year){
        fechaFinalizacion.setDate(day); 
        if(month>0 && month <=12){
            fechaFinalizacion.setMonth(month-1); 
        }
        fechaFinalizacion.setYear(year + 1900);
    }
    public void setPrecio(double precio){
        if(!garantia){
            this.coste = coste; 
        }else{
            System.out.println("No se puede establecer precio dado que la garantía cubre la reparación, el coste es 0.0€");
        }
    }
    public boolean getGarantia(){
        return garantia; 
    }
    public void setEstado(String nuevoEstado){
        estado = nuevoEstado; 
    }
    public String toString(){
        String resultado = " ===================================================\n"+ "| Identificador de la reparación: " + id;
        resultado += "\n| Estado de la reparación: " + descripcion;
        resultado += "\n| Ténico responsable de la reparación: " + reparador.getNombre() + " " + reparador.getApellido() + " con dni " + reparador.getDni(); 
        resultado += "\n| Cliente solicitante de la reparación: " +cliente.getNombre() +" " + cliente.getApellido()+ " con dni " + cliente.getDni();
        resultado += "\n| Electrodoméstico a reparar: " + producto.getCategoria() + " " + producto.getMarca() + " " + producto.getModelo();
        resultado += "\n| Fecha de solicitud de la reparación: " + fechaRecepcion.getDate() + "/" + (fechaRecepcion.getMonth() + 1) + "/" + (fechaRecepcion.getYear()+ 1900);
        String garanti = (garantia) ? "Si" : "No"; 
        resultado += "\n| Garantía: " + garanti;
        resultado += "\n| Coste de reparación: " + coste; 
        resultado += "\n| Fecha de finalización de la reparación: "; 
        if(fechaFinalizacion == null){
            resultado += "EL REPARADOR AÚN NO HA ASIGNADO FECHA DE FINALIZACIÓN."; 
        }else{
            resultado += fechaFinalizacion.getDate() + "/" + (fechaFinalizacion.getMonth() -1 ) + "/"+ (fechaFinalizacion.getYear()+1900); 
        }
        
        return resultado; 
    }
    
}
