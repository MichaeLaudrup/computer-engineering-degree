import java.util.Scanner; 
/**
 * Write a description of class GestorClientes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GestorClientes extends Gestor<Cliente>
{
    Scanner lector;
    /**
     * Método que comprueba que un cliente existe en el registro de la tienda
     * @return true si cliente existe; false si cliente no existe
     */
    public boolean clienteExiste(String dni){
        if(getMapa().containsKey(dni)){
            return true; 
        }else{
            return false; 
        }
    }
    public GestorClientes()
    {
        super(); 
        lector = new Scanner(System.in);
        datosPorDefecto(); 
    }
    /**
     * Método que inserta cliente con datos recibidos
     * como parámetros.
     * @param dni cliente
     * @param nombre cliente
     * @param apellido cliente
     * @param domicilio cliente
     * @param telefono cliente
     */
    public void addCliente(String dni, String nombre, String apellido, String domicilio, String telefono){ 
        if(getElemento(dni) == null ){
            getMapa().put(dni, new Cliente (dni,nombre,apellido,domicilio,telefono));
        }
    }
     /**
     * Este método imprime los datos de un único usuario
     * @param Dni del usuario a imprimir
     */
    public void imprimeCliente(String dni){     
        if(getMapa().containsKey(dni)){
            System.out.println(getMapa().get(dni));        
        }else{
            System.out.println("----------Este cliente no está en el sistema---------"); 
        }
    }
    public void datosPorDefecto() 
    {
        getMapa().put("1234567A", new Cliente("1234567A","maria","luis Cabrera","c/ isotopos","678764784")); 
        getMapa().put("4576849X", new Cliente("4576849X","marcos","luis Cabra","c/ isot","63948484"));   
        getMapa().put("2343234A", new Cliente("2343234A","Victor","luateneo","c/ ilondreos","6423464784"));   
        getMapa().put("2323237A", new Cliente("2323237A","Joseca","Gutierzza","c/ icalios","623434784")); 
        
    }
    public void listarClientes(){
        
        for(Cliente cliente : getMapa().values()){
            System.out.println(cliente); 
        }
    }
    /**
     * Este método esta especilizado en la modificación de datos de un 
     * determinado usuario.
     */
    public void modificarCliente(String dni){
        int selector;
        String aux, dniActual; 
        boolean finalizado = false; 
        while(!finalizado){
            System.out.println(" ===================================================");
            System.out.println("| 1.Modificar nombre                                |");
            System.out.println("| 2.Modificar apellidos                             |");
            System.out.println("| 3.Modificar domicilio                             |");
            System.out.println("| 4.Modificar telefono                              |");
            System.out.println("| 5.Dar de baja                                     |");
            System.out.println("| 6.Reactivar cliente                               |");
            System.out.println("| 7.Atras                                           |");
            System.out.println(" ===================================================");
            System.out.print(" Seleccione opción deseada:");
            selector = Integer.parseInt(lector.nextLine());
            switch(selector){
                case 1: 
                System.out.print("Inserte nombre actualizado: ");
                String newName = lector.nextLine(); 
                getElemento(dni).setNombre(newName);               
                System.out.println("---------Nombre actualizado con éxito------------");
                break;
                case 2: 
                System.out.print("Inserte apellidos actualizados: ");
                getElemento(dni).setApellido(lector.nextLine());
                System.out.println("---------Apellidos actualizado con éxito------------");
                break;
                case 3:
                System.out.print("Inserte domicilio actualizado: ");
                getElemento(dni).setDomicilio(lector.nextLine());
                System.out.println("---------Domicilio actualizado con éxito------------");
                break;
                case 4:
                System.out.print("Inserte telefono actualizado: ");
                getElemento(dni).setTelefono(lector.nextLine());
                System.out.println("---------telefono actualizado con éxito------------");
                break;
                case 5:
                getElemento(dni).setAlta(false); 
                System.out.println("---------Cliente Dado de baja-----------"); 
                break;      
                case 6:
                getElemento(dni).setAlta(true);
                System.out.println("---------Cliente reactivado------------");
                break;
                case 7:
                finalizado = true; 
                break; 
                default: 
                System.out.println("La opción insertada no corresponde a ninguna operación....");
                break;     
            }
        }  
    }
}
