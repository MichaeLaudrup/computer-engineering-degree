import java.util.Scanner; 
/**
 * Write a description of class GestorEmpleados here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GestorEmpleados extends Gestor<Empleado>
{
    private Scanner lector; 
    /**
     * Constructor for objects of class GestorEmpleados
     */
    public GestorEmpleados()
    {
       super();
       lector = new Scanner(System.in);
       datosPorDefecto();
    }

    /**
     * Método que inserta empleado con datos recibidos
     * como parámetros.
     * @param dni empleado
     * @param nombre empleado
     * @param apellido empleado
     * @param domicilio empleado
     * @param telefono empleado
     */
    public void addEmpleado(String dni, String nombre, String apellido, String domicilio, String telefono){     
        System.out.print("Tipo de empleado (cajero, postventa, tecnico, comercial, financiero, administrador): ");
        String tipoEmpleado = lector.nextLine().toLowerCase();
        switch (tipoEmpleado){
                case "cajero": 
                getMapa().put(dni, new Cajero(dni, nombre, apellido, domicilio, telefono)); 
                System.out.println("------>Empleado añadido al sistema con exito<--------");
                break;
                case "postventa":
                getMapa().put(dni, new PostVenta(dni, nombre, apellido, domicilio, telefono));
                System.out.println("------>Empleado añadido al sistema con exito<--------");
                break; 
                case "comercial":
                getMapa().put(dni, new Comercial(dni, nombre, apellido, domicilio, telefono));
                System.out.println("------>Empleado añadido al sistema con exito<--------");
                break;
                case "tecnico":
                getMapa().put(dni, new Tecnico(dni, nombre, apellido, domicilio, telefono));
                System.out.println("------>Empleado añadido al sistema con exito<--------");
                break;
                case "financiero":
                getMapa().put(dni, new Financiero(dni, nombre, apellido, domicilio, telefono));
                System.out.println("------>Empleado añadido al sistema con exito<--------");
                break;
                case "administrador":
                getMapa().put(dni, new Administrador(dni, nombre, apellido, domicilio, telefono)); 
                System.out.println("------>Empleado añadido al sistema con exito<--------");
                break;
                default:
                System.out.println("Ha insertado incorrectamente el tipo de empleado. Repita ");
                addEmpleado(dni, nombre, apellido, domicilio, telefono); 
        }
        
    }
     /**
     * Este método esta especilizado en la modificación de datos de un 
     * determinado usuario.
     */
    public void modificarEmpleado(String dni){
        int selector; 
        boolean finalizado = false; 
        while(!finalizado){
            System.out.println(" _____________________________________");
            System.out.println("| 1.Modificar nombre                  |");
            System.out.println("| 2.Modificar apellidos               |");
            System.out.println("| 3.Modificar domicilio               |");
            System.out.println("| 4.Modificar telefono                |");
            System.out.println("| 5.Dar de baja                       |");
            System.out.println("| 6.Reactivar cliente                 |");
            System.out.println("| 7.Atras                             |");
            System.out.println(" _____________________________________");
            System.out.println(" Seleccione opción deseada:");
            selector = Integer.parseInt(lector.nextLine());
            switch(selector){
                case 1: 
                System.out.print("Inserte nombre actualizado: ");
                getElemento(dni).setNombre(lector.nextLine());               
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
                System.out.println("La opción insertada no corresponde a ninguna operación.... Repita");
                break;     
            }
        }  
    }
    public boolean esTecnico(String dni){
        if(getElemento(dni).getTipoEmpleado().equals("tecnico")){
            return true; 
        }else{
            return false; 
        } 
    }
    public void imprimeEmpleado(String dni){
        Empleado trabajador = (Empleado)getElemento(dni);  
        trabajador.imprimeEmpleado(); 
    }
    public void listarEmpleados(){
        for(Empleado x: getMapa().values()){
            x.imprimeEmpleado();  
        }
    }
    public void listarTecnicos(){
        for(Empleado x: getMapa().values()){
            if(x.getTipoEmpleado().equals("tecnico")){
                x.imprimeEmpleado(); 
            }
        }
    }
    public void darDeBaja(String key){
        getElemento(key).setAlta(false);  
    }
        public void datosPorDefecto() 
    {   
        getMapa().put("E1", new Cajero("E1", "Rafael", "Luis", "calle mal paraiso", "984923"));
        getMapa().put("12345678Y", new PostVenta("12345678Y", "Angelo", "mapf", "calle x", "483294"));
        getMapa().put("1234567T", new Tecnico("1234567T","Victoria", "Brito Perez", "C/Kapitel n5", "+34 675847584"));
        getMapa().put("2345678T", new Tecnico("2345678T","Julian", "Hernandez meas", "C/Asulei n20", "+34 87483"));
        getMapa().put("22222222W", new Financiero("22222222W","Marcelino","dasdsadh", "dadls", "878423"));
    }
    
    public void miPrueba(Electrodomestico x ){
    
    }
}

