  
 import java.util.Scanner;     
 import java.util.Date;      
 import java.util.Calendar;  
/**
 * Write a description of class InterfazTexto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */public class InterfazTextual
{
    //Empleado que esta gestionando el sistema en un momento dado...
    private Empleado empleadoActual; 
    //Se declará dos escaneres espeacializados cada uno en la recogia de un tipo de dato concreto..
    private Scanner lectorEnteros; 
    private Scanner lectorStrings;
    //Se declaran tres gestores: uno para clientes, otro para empleados y otro para electrodomésticos
    private GestorClientes miGestorClientes; 
    private GestorEmpleados miGestorEmpleados;  
    private GestorElectro miGestorElectro;
    //Esta variable guardará la fecha actual del sistema
    private Date fecha;
    /**
     * Crea una interfaz textual, la cual, se dedicará a unir todos las clases y gestionarlas. Además,
     * facilita la interaccioón Usuario-progama
     * @param Gestor de clientes.
     * @param Gestor de empleados.
     * @param Gestor de electrodomésticos
     */
    public InterfazTextual(GestorClientes miGestorClientes, GestorEmpleados miGestorEmpleados , GestorElectro miGestorElectro)
    {
        // Inicialización de campos esta clase
        lectorEnteros = new Scanner(System.in); 
        lectorStrings = new Scanner(System.in);
        fecha = new Date(); 
        this.miGestorClientes = miGestorClientes; 
        this.miGestorEmpleados = miGestorEmpleados; 
        this.miGestorElectro = miGestorElectro; 
    }

    public void empezar(){ 
        //Se inicia sesión
        ejecutaLogin();
        //Se inicia menú según login
        iniciaMenu();
    }

    /**
     * Este método se encarga de verificar los credenciales del empleado
     */
    public void ejecutaLogin()
    {
        String dni;                         
        boolean acreditado = false;
        System.out.println(" ===================================================");
        System.out.println("|                  INICIO DE SESIÓN                 |");
        System.out.println(" ===================================================");
        while(!acreditado){
            System.out.print("Introduzca Dni:");
            dni = lectorStrings.nextLine();
            if(miGestorEmpleados.getElemento(dni) != null){
                empleadoActual = miGestorEmpleados.getElemento(dni);
                acreditado = true; 
            }else{
                System.out.println("Este Dni no esta registrado en el sistema");
                System.out.println("Vuelva a intentarlo"); 
            } 
        } 
    }

    /**
     * Este método imprime menú según el cliente que
     * ha entrado en el sistema
     */
    public void iniciaMenu()
    {
        switch(empleadoActual.getTipoEmpleado()){
            case "cajero":
            menuCajero();
            break;
            case "postventa":
            menuPostVenta();
            break;
            case "tecnico":
            menuTecnico();
            break;
            case "financiero":
            menuFinanciero();
            break;
            case "comercial":
            menuComercial(); 
            break;
            case "administrador":
            menuAdministrador(); 
            break;
            default: 
        }
    }

    /**
     * Este método imprime menu para Administrador e interacciona
     * con empleado
     */
    private void menuAdministrador(){
        int selector; 
        boolean finalizado = false; 
        imprimeBienvenida();
        //No se sale hasta que se de a cerrar sesión
        while(!finalizado){
            System.out.println(" ===================================================");
            System.out.println("|                 Menu administrador                |"); 
            System.out.println(" ===================================================");
            System.out.println("|  1. Gestionar Clientes.                           |");
            System.out.println("|  2. Gestionar Electrodomésticos.                  |");
            System.out.println("|  3. Gestionar Empleados.                          |");
            System.out.println("|  4. Ver reparaciones procesadas.                  ");
            System.out.println("|  5. Cerrar sesion.                                |");
            System.out.println(" ===================================================");
            System.out.print("Introduzca opción deseada: ");
            selector = recogeEntero(); 
            switch(selector){
                case 1:
                manejarClientes(); 
                break;
                case 2: 
                manejarProductos(); 
                break; 
                case 3:
                manejarEmpleados(); 
                break;
                case 4:
                GestorTienda.imprimeHistorialRepProcesadas();
                break; 
                case 5:
                System.out.println(" ===================================================");
                System.out.println("|             SESION CERRADA CON ÉXITO              |");
                System.out.println(" ===================================================");
                finalizado = true;
                break; 
                default:
                System.out.println("Opcion insertada incorrecta. Repita.");
                break; 
            }
        }
    }

    /**
     * Es metodo imprime menu para cajero
     */
    private void menuCajero(){
        String dni; 
        boolean finalizado = false; 
        imprimeBienvenida();
        while(!finalizado){
            System.out.println(" ===================================================");
            System.out.println("|                     Menu Cajero                   |"); 
            System.out.println(" ===================================================");
            System.out.println("|  1. Visualizar datos de un cliente.               |");
            System.out.println("|  2. Insertar cliente.                               |");
            System.out.println("|  3. Insertar una compra.                            |");
            System.out.println("|  4. Listar fichas de compra de un cliente.        |");
            System.out.println("|  5. Listar a todos los clientes.                  |");
            System.out.println("|  6. Cerrar sesion.                                |");
            System.out.println(" ===================================================");
            System.out.print("Introduzca numero de opcion deseada: ");
            int selector = recogeEntero(); 
            switch(selector){
                case 1:
                dni = recogerDniCliente(); 
                miGestorClientes.imprimeCliente(dni); 
                break;
                case 2: 
                System.out.print("Inserte Dni de cliente a insertar: "); 
                String dniInsertado = lectorStrings.nextLine(); 
                if(miGestorClientes.clienteExiste(dniInsertado)){
                   System.out.println("Este cliente ya esta en el sistema. Si quiere modificar sus datos seleccione opcion adecuada."); 
                }else{
                   insertarCliente(dniInsertado);
                }
                break; 
                case 3:
                addCompra(); 
                break; 
                case 4:
                dni = recogerDniCliente(); 
                miGestorClientes.getElemento(dni).imprimeAdquisiciones(); 
                break; 
                case 5:
                miGestorClientes.listarClientes(); 
                break; 
                case 6:
                System.out.println(" ===================================================");
                System.out.println("|             SESION CERRADA CON EXITO              |");
                System.out.println(" ===================================================");
                finalizado = true; 
                break; 
                default:
                System.out.println("Opcion insertada incorrecta. Repita.");
                break; 
            }
        }
    }

    private void addCompra(){
        Cliente client = cargarCliente(); 
        System.out.println("Gestion de adquisiciones del cliente: " + client.getNombre() + " " +client.getApellido() + " con dni " + client.getDni()  );
        boolean finalizado = false; 
        while(!finalizado){
            System.out.print("Inserte identificador del producto a adquirir: ");
            String identificador = lectorStrings.nextLine(); 
            if(miGestorElectro.getElemento(identificador) != null){
                System.out.println("Se dispone de " + miGestorElectro.getElemento(identificador).getStock() +" " +miGestorElectro.getElemento(identificador).getCategoria().toLowerCase()  ); 
                System.out.print("¿Cantidad de articulos a adquirir?: ");
                //número de adquisiciones
                int cantidadDeseada = recogeEntero();
                if(miGestorElectro.adquiereProducto(identificador, cantidadDeseada)){
                    client.addFichaCompra(fecha, miGestorElectro.getElemento(identificador), cantidadDeseada);  
                    client.getHistorialOperaciones().add(new Operacion(empleadoActual, client, "ADQUISICION", miGestorElectro.getElemento(identificador), fecha)); 
                    boolean terminado = false; 
                    while(!terminado){
                        System.out.print("�Desea insertar mas productos? (si/no): ");
                        String answer = lectorStrings.nextLine(); 
                        if(answer.equalsIgnoreCase("si")){
                            terminado = true;  
                        }else if(answer.equalsIgnoreCase("no")){
                            terminado =true; 
                            finalizado = true;  
                        }else{
                            System.out.println("Respuesta insertada incorrecta... Repita"); 
                        }                    
                    } 
                }else{
                    System.out.println("Se repite el proceso. Inserte una cantidad correcta."); 
                    System.out.println(" ===================================================");
                } 
            }else{
                System.out.println("Identificador de producto no encontrado. �Que desea hacer?");
                boolean acabado = false; 
                while(!acabado){
                    System.out.println(" ===================================================");
                    System.out.println("|  1. Volver a intentar.                            |");
                    System.out.println("|  2. Salir.                                        |");
                    System.out.println(" ===================================================");
                    System.out.print("Selecciones opcion: ");      
                    int selector = recogeEntero();                       
                    if(selector == 1){     
                        acabado = true; 
                    } else if(selector == 2) { 
                        acabado = true; 
                        finalizado = true; 
                    }else{   
                    }
                }
            }
        }
    }
    
     private Cliente cargarCliente(){
        Cliente miCliente = null; 
        System.out.print("Inserte dni del cliente: ");
        String dniCliente = lectorStrings.nextLine();
        System.out.println(" ===================================================");
        if(miGestorClientes.clienteExiste(dniCliente)){
            miCliente = miGestorClientes.getElemento(dniCliente); 
        }else{
            boolean finalizado = false; 
            while(!finalizado){
                System.out.println("El cliente no esta en el sistema. �Que desea hacer? "); 
                System.out.println(" ===================================================");
                System.out.println("|  1. Volver a intentar.                            |");
                System.out.println("|  2. Insertar cliente.                               |");
                System.out.println(" ===================================================");
                System.out.print("Selecciones opcion: ");
                int selector = recogeEntero(); 
                if(selector == 2){
                    insertarCliente(dniCliente); 
                    miCliente = miGestorClientes.getElemento(dniCliente);
                    finalizado = true; 
                }else if(selector == 1){
                    miCliente = cargarCliente();
                    finalizado = true; 
                }else{
                    System.out.print("Opcion introducida incorrecta... Repita");
                }
            }     
        }
        return miCliente; 
    }
    /**
     * Es metodo imprime menu para un técnico e interacciona con el técnico
     */
    private void menuTecnico(){
        String dni; 
        //Tenemos que convertir la variable EmpleadoActual en un tipo tecnico, para poder acceder a los métodos del técnico
        Tecnico tecnicoActual = (Tecnico)miGestorEmpleados.getElemento(empleadoActual.getDni());  
        boolean finalizado = false; 
        imprimeBienvenida();
        while(!finalizado){
            System.out.println(" ===================================================");
            System.out.println("|                    Menu Tecnico                   |"); 
            System.out.println(" ===================================================");
            System.out.println("|  1. Visualizar datos de un cliente.               |");
            System.out.println("|  2. Ver mis reparaciones no atendidas.            |");
            System.out.println("|  3. Listar fichas de compras de un cliente.       |");
            System.out.println("|  4. Gestionar una reparacion.                     |");
            System.out.println("|  5. Cerrar sesion.                                |");
            System.out.println(" ===================================================");
            System.out.print("Introduzca numero de opcion deseada: ");
            int selector = recogeEntero(); 
            switch(selector){
                case 1:
                dni = recogerDniCliente(); 
                miGestorClientes.imprimeCliente(dni); 
                break;
                case 2: 
                tecnicoActual.printReparaciones(); 
                break; 
                case 3:
                dni = recogerDniCliente(); 
                miGestorClientes.getElemento(dni).imprimeAdquisiciones(); 
                break;
                case 4:
                dni = recogerDniCliente(); 
                gestionarReparacion(dni, tecnicoActual); 
                break; 
                case 5:
                finalizado = true; 
                break; 
                default:
                System.out.println("Opcion insertada incorrecta. Repita.");
                break; 
            }
        }
    }
    public void gestionarReparacion(String dniCliente, Tecnico tec){
        System.out.println(" ===================================================");
        System.out.println("|              REPARACIONES PENDIENTES              |");
        tec.printReparaciones(); 
        System.out.println("Inserte identificador de la reparacion que desea efectuar(inserte una de las mostradas anteriormente) : ");
        String identificador = lectorStrings.nextLine(); 
        
        if(tec.existeReparacion(identificador)){ 
            boolean finalizado = false; 
            //Se crea una ficha de reparacion para asociarla a las piezas solicitadas
            FichaReparacion miReparacion = tec.getFicha(identificador);
            while(!finalizado){
                System.out.println(" =================================================== ");
                System.out.println("| ID ficha reparacion: " + miReparacion.getID());
                System.out.println(" ===================================================");
                System.out.println("|                Gestion de reparacion              |"); 
                System.out.println(" ===================================================");
                System.out.println("|  1. Visualizar reparacion actual.                 |");
                System.out.println("|  2. Modificar estado de una reparacion.           |");
                System.out.println("|  3. Establecer/Modificar fecha de finalizacion.   |");
                System.out.println("|  4. Establecer/Modificar coste de una reparacion  |");
                System.out.println("|  5. Solicitar piezas.                             |");
                System.out.println("|  6. Reparar y terminar.                           |");
                System.out.println("|  7. Cerrar sesion.                                |");
                System.out.println(" ==================================================="); 
                System.out.print(" Inserte opcion deseada: ");
                int selector = recogeEntero(); 
                switch(selector){
                    case 1:
                    System.out.println(miReparacion); 
                    break;
                    case 2:
                    modificaEstado(identificador, tec); 
                    break;
                    case 3:
                    modificaFinReparacion(identificador); 
                    break; 
                    case 4:
                    modificarCosteReparacion(identificador); 
                    break;
                    case 5:
                    solicitarPiezas(miReparacion); 
                    break; 
                    case 6:
                    tec.getFicha(identificador).setEstado("TERMINADO");
                    GestorTienda.addReparacionProcesada(tec.getFicha(identificador));
                    tec.eliminarReparacion(identificador); 
                    break;
                    case 7:
                    finalizado = true; 
                    break;
                }
            }
        }else{
            System.out.println("No tiene ninguna reparación asignada con este identifciador. ");
        }
        
    }
    public void solicitarPiezas(FichaReparacion ficha){
        System.out.print("Introduzca el nombre de la pieza que desea solicitar: ");
        String nombrePieza = lectorStrings.nextLine(); 
        System.out.print("Introduzca el número de piezas que desea solicitar(número entero): ");
        int cantidad = recogeEntero(); 
        //unicamente tendrá un ID de ficha de reparación cada pieza.
        
    
    }
    /**
     * 
     * Método que modifica el coste de una reparación.
     * @param identidicador(String)  de la ficha de reparación del técnico a tratar
     */
    public void modificarCosteReparacion(String identificador){
        //A este método unicamente puede acceder un empleado de tipo técnico. 
        Tecnico tec = (Tecnico)empleadoActual; 
        //el método getGarantia devuelve un booleano true o false (nunca null) 
        System.out.print("Inserte precio de la reparacion: ");
        //el método setPrecio controla que en caso de que haya garantía el precio no se pueda modificar y se mantenga 0.0€
        tec.getFicha(identificador).setPrecio(recogeDouble());
    }
    public void modificaFinReparacion(String identificador){
        //A este método unicamente puede acceder un empleado de tipo tecnico.
        Tecnico tec = (Tecnico)empleadoActual; 
       int dia, mes, anio; 
       System.out.print("Inserte dia: ");
       dia = recogeEntero(); 
       System.out.print("Inserte mes: ");
       mes = recogeEntero();
       System.out.print("Inserte a�o: ");
       anio = recogeEntero();
       tec.getFicha(identificador).establecerModificarFechaFinalizacion(dia, mes, anio); 
    }
    /**
     * 
     */
    public void modificaEstado(String identificador, Tecnico tec){ 
        System.out.println(" ===================================================");
        System.out.println("|             Seleccion de nuevo Estado             |"); 
        System.out.println(" ===================================================");
        System.out.println("|  1. PENDIENTE.                                    |");
        System.out.println("|  2. EN PROCESO.                                   |");
        System.out.println("|  3. ESPERA DE CONFIRMACION DE CLIENTE.            |");
        System.out.println("|  4. FASE DE PRUEBA.                               |");
        System.out.println("|  5. FALTAN PIEZAS.                                |");
        System.out.println("|  6. ATRAS.                                        |");
        System.out.println(" ==================================================="); 
        System.out.print("Inserte nuevo estado de la reparacion: ");
        int selector = recogeEntero(); 
        switch(selector){
            case 1:               
            tec.getFicha(identificador).setEstado("PENDIENTE" );    
            break; 
            case 2:
            tec.getFicha(identificador).setEstado("EN PROCESO" );
            break;
            case 3:
            tec.getFicha(identificador).setEstado("ESPERA DE CONFIRMACION DE CLIENTE");
            break; 
            case 4:
            tec.getFicha(identificador).setEstado("FASE DE PRUEBA");
            break; 
            case 5:
            tec.getFicha(identificador).setEstado("FALTAN PIEZAS");
            break; 
            case 6:
            break; 
            default: 
            System.out.println("Opcion incorrecta. repita"); 
        }
    }
    
    /**
     * Es metodo imprime menu para PostVenta
     */
    private void menuPostVenta(){
        String dni; 
        boolean finalizado = false; 
        imprimeBienvenida();
        while(!finalizado){
            System.out.println(" ===================================================");
            System.out.println("|                  Menu PostVenta                   |"); 
            System.out.println(" ===================================================");
            System.out.println("|  1. Visualizar datos de un cliente.               |");
            System.out.println("|  2. Gestionar una devolucion.                     |");
            System.out.println("|  3. Listar fichas de compras de un cliente.       |");
            System.out.println("|  4. Crear ficha de reparacion.                    |");
            System.out.println("|  5. Cerrar sesion.                                |");
            System.out.println(" ===================================================");
            System.out.print("Introduzca numero de opcion deseada: ");
            int selector = recogeEntero(); 
            switch(selector){
                case 1:
                dni = recogerDniCliente(); 
                miGestorClientes.imprimeCliente(dni); 
                break;
                case 2: 
                dni =  recogerDniCliente();
                gestionarDevolucion(dni); 
                break; 
                case 3:
                dni = recogerDniCliente(); 
                miGestorClientes.getElemento(dni).imprimeAdquisiciones(); 
                break;
                case 4:
                dni = recogerDniCliente(); 
                creaFichaReparacion(dni); 
                break; 
                case 5:
                finalizado = true; 
                break; 
                default:
                System.out.println("Opcion insertada incorrecta. Repita.");
                break; 
            }
        }
    }
    
    /**
     * Es metodo imprime menu para Financiero e interacciona 
     * con empleado
     */
    private void menuFinanciero(){
        System.out.println("Esta en el menu del financiero");
    }

    /**
     * Es metodo imprime menu para el comercial e interacciona 
     * con empleado
     */
    private void menuComercial(){
        System.out.println("Esta en el menu del Comercial");
    }
    
    public boolean calculaGarantia( FichaCompra compra) {
        boolean garantia; 
        Date finGarantia = sumarDias(compra.getFecha(), (365*2)); 
            if(fecha.before(finGarantia)){
                return true; 
            }else{
                return false;   
            }
    }
    private void creaFichaReparacion( String dni ){
        Cliente receptor = miGestorClientes.getElemento(dni); 
        FichaCompra compra = cargaFichaCompra(receptor);
        if(compra.getCantidad() > 0){ 
            boolean garantia = calculaGarantia(compra); 
            System.out.println(" ===================================================");
            System.out.println("            CREACION DE FICHA DE REPARACION         ");
            System.out.println(" ===================================================");
            System.out.println("El cliente adquirio " + compra.getCantidad() + " de " + compra.getProducto().getCategoria() ); 
            System.out.println("Fecha de compra: " + compra.getFecha().getDate() + "/" + (compra.getFecha().getMonth() + 1) + "/" + (compra.getFecha().getYear()+ 1900) ); 
            String gar = (garantia) ? "Si" : "No"; 
            System.out.println("Garantia: " + gar ); 
            System.out.println(" ===================================================");
            System.out.println("                  TECNICOS DISPONIBLES"); 
            System.out.println(" ===================================================");
            miGestorEmpleados.listarTecnicos(); 
            System.out.println(" ===================================================");
            String dniTecnico = recogeDniTecnico();
            Tecnico miTecnico = (Tecnico)miGestorEmpleados.getElemento(dniTecnico); 
            System.out.print("Inserte una breve descripcion del tipo de reparacion: ");
            String descripcion = lectorStrings.nextLine();
            miTecnico.addFichaReparacion(descripcion, miGestorClientes.getElemento(dni), miTecnico, compra.getElectro(), fecha, garantia); 
            compra.setCantidad(compra.getCantidad() -1 ); 
            //cabe destacar que el empleadoActual en este caso es un postVenta que está añadiendo la reparacion, no un técnico
            receptor.addOperacion(empleadoActual, miGestorClientes.getElemento(dni), "REPARACION", compra.getElectro(), fecha); 
            System.out.println(" ====SE HA REPORTADO LA REPARACION CORRECTAMENTE====");
        }else{
            System.out.println("Se han devuelto todas los productos a la tienda. ");
        }
    }
    public String recogeDniTecnico(){
       System.out.print("Inserte Dni del tecnico: ");
       String dniRecibido = lectorStrings.nextLine(); 
       if(miGestorEmpleados.esTecnico(dniRecibido)){
           return dniRecibido;  
       }else{
           System.out.println("Este dni no corresponde a ningun tecnico registrado en el sistema. Repita. "); 
           return recogeDniTecnico(); 
       }
    }
    /**
     * Este método gestiona las devoluciones de un producto que se ha 
     * adquirido con anterioridad por un cliente determinado.
     * @param dni del cliente. 
     */
    private void gestionarDevolucion(String dni){ 
        //Se declara e inicializa un variable de tipo cliente con el Dni recibido, el método recogerDniCliente comprueba que el cliente existe también.
        Cliente client = miGestorClientes.getElemento(dni);
        FichaCompra compra = cargaFichaCompra(client); 
        if(compra != null){
            Date fechaLimite = sumarDias(compra.getFecha(), (31*3));
            //se informa de la cantidad que adquirío el cliente en esa determinada adquisición para evitar errores
            System.out.println(" ===================================================");
            System.out.println("El cliente adquirio " + compra.getCantidad() + " de " + compra.getProducto().getCategoria() ); 
            System.out.println("Fecha de compra: " + compra.getFecha().getDate() + "/" + (compra.getFecha().getMonth() + 1) + "/" + (compra.getFecha().getYear()+ 1900) ); 
            System.out.println("Fecha limite de devolucion: " + fechaLimite.getDate() + "/" + (fechaLimite.getMonth() + 1) + "/" + (fechaLimite.getYear()+ 1900));
            System.out.println(" ===================================================");
            System.out.print("Inserte que cantidad que desea devolver(numero entero): "); 
            int nDevoluciones = recogeEntero();
            //Se comprueba que cantidad que se desea devolver no supere la cantidad que se adquirío
            if(nDevoluciones <= compra.getCantidad() && nDevoluciones > 0){ 
                //Se parte de la base de que 3 meses son (31 días x 3) para no complicar el códigom, además se divide entre 86400000 para convertir de milisegundos a días la diferencia de tiempo entre dos fechas.
                if(((fecha.getTime() - (compra.getFecha().getTime()))/86400000) < (31*3)){
                     compra.setCantidad(compra.getCantidad() - nDevoluciones );
                     compra.setDevoluciones(nDevoluciones); 
                     compra.getProducto().setStock(compra.getProducto().getStock() + nDevoluciones); 
                     client.getHistorialOperaciones().add(new Operacion(empleadoActual, client, "DEVOLUCION", compra.getProducto(), fecha )); 
                     System.out.println(" ============Producto devuelto con éxito============");
                     System.out.print("¿Desea devolver mas productos (si/no)? ");
                     if(diceSI()){
                            gestionarDevolucion(dni); 
                     }
                }else{
                    System.out.println("Se ha excedido la fecha para poder devolver este producto ");
                    System.out.println("La fecha limite era: " + fechaLimite.getDate() + "/" + (fechaLimite.getMonth() + 1) + "/" + (fechaLimite.getYear()+ 1900));
                } 
            }else{
                System.out.println(" ==================================================="); 
                System.out.println("La cantidad insertada es incorrecta."); 
                System.out.println("Se repite el proceso. Inserte una cantidad correcta."); 
                gestionarDevolucion(dni); 
            }
        }
    }
    private FichaCompra cargaFichaCompra(Cliente client){
         FichaCompra resultado = null; 
         if(client.getHistorialCompras().size() > 0){
             System.out.println(" ===================================================");
             System.out.print("Inserte identificador de ficha de compra: ");
             int idCompra = recogeEntero(); 
             if(client.existeFichaCompra(idCompra)){
                resultado = client.getFichaCompra(idCompra); 
             }else{
                System.out.println("El identificador de ficha compra insertado no corresponde a ninguna ficha compra de este cliente. Repita");
                boolean acabado = false; 
                while(!acabado){
                    System.out.println(" ===================================================");
                    System.out.println("|  1. Volver a intentar.                            |");
                    System.out.println("|  2. Cancelar.                                     |");
                    System.out.println(" ===================================================");
                    System.out.println("Selecciones opcion: ");      
                    int selector = recogeEntero();                       
                    if(selector == 1){ 
                        resultado = cargaFichaCompra(client); 
                        acabado = true; 
                    }else if(selector == 2) { 
                        acabado = true; 
                        resultado = null; 
                    }else{  
                        System.out.println("Opcion insertada incorrecta. Vuelva a intentar. ");
                    }
                }
             }
         }else{
            System.out.println("Este cliente no ha realizado ninguna compra"); 
         } 
         return resultado; 
    }     
    
   private boolean diceSI(){
       boolean resultado; 
       String respuesta =  lectorStrings.nextLine();
       if(respuesta.equalsIgnoreCase("Si")){
           resultado = true; 
       }else if(respuesta.equalsIgnoreCase("No")){
           resultado = false; 
       }else{
           System.out.println("Se ha introducido mal la respuesta. Repita...."); 
           resultado = diceSI(); 
       }
       return resultado;
    }     
    
   public Date sumarDias(Date fecha, int dias){
      Calendar calendar = Calendar.getInstance();   
      calendar.setTime(fecha); // Configuramos la fecha que se recibe   
      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir
      return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
   }
    /**
     * Este método imprime mensaje de Bienvenida con algunos datos del
     * usuario actual
     */
    private void imprimeBienvenida(){
        System.out.println(" ===================================================");
        System.out.println("|                     BIENVENIDO                    |");
        System.out.println(" ===================================================");
        System.out.println("FECHA DEL SISTEMA: " + fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + (fecha.getYear()+ 1900)); 
        System.out.println("DNI: "+ empleadoActual.getDni());
        System.out.println("Usuario: " + empleadoActual.getNombre());
        System.out.println("Apellidos: " + empleadoActual.getApellido());
        System.out.println("Domicilio: " + empleadoActual.getDomicilio());
        System.out.println("Telefono: " + empleadoActual.getNumeroTelefono()); 
    }
    // SECCIÓN DE GESTIÓN DE EMPLEADOS
        /**
     * Este método es para manejar un menú especializado en la gestión 
     * de usuarios
     */
    private void manejarEmpleados(){
        int selector; 
        String dni; 
        boolean finalizado = false; 
        while(!finalizado){
            System.out.println(" ===================================================");
            System.out.println("|                 Gestor Empleados                  |");
            System.out.println(" ===================================================");
            System.out.println("| 1. Insertar Empleado                                |");   
            System.out.println("| 2. Dar de baja  a un empleado                     |");
            System.out.println("| 3. Modificar datos de un empleado                 |");
            System.out.println("| 4. Visualizar datos de un empleado                |");
            System.out.println("| 5. Listar a todos los empleados                   |");
            System.out.println("| 6. Atras                                          |");
            System.out.println(" ===================================================");
            System.out.print("Introduzca opcion deseada: "); 
            selector = recogeEntero();
            switch(selector){
                case 1:
                insertarEmpleado();
                break;
                case 2:
                dni = recogerDniEmpleado();  
                miGestorEmpleados.darDeBaja(dni);    
                break; 
                case 3:
                dni = recogerDniEmpleado(); 
                miGestorEmpleados.modificarEmpleado(dni); 
                break; 
                case 4: 
                dni = recogerDniEmpleado(); 
                miGestorEmpleados.imprimeEmpleado(dni); 
                break; 
                case 5:
                miGestorEmpleados.listarEmpleados(); 
                break;
                case 6:
                finalizado = true; 
                break; 
                default:
                System.out.println("La opcion insertada no corresponde a ninguna operacion....");
                break;
            }
        }
    }
    public String recogerDniEmpleado(){
        System.out.println(" ===================================================");
        System.out.print  ("| Introduzca dni del empleado: ");
        String dni = lectorStrings.nextLine();
        System.out.println(" ===================================================");
        if(miGestorEmpleados.getElemento(dni) != null){
            return dni; 
        }else{
            System.out.println("Este dni no corresponde a ningun empleado. Vuelva a insertar....");
            return recogerDniCliente(); 
        }
    }
        /**
     * Método que inserta empleado sin recibir datos como parámetros
     *  y pidiendo datos a través de un scanner.
     */
    public void insertarEmpleado(){ 
        String dni, nombre, apellido, domicilio, telefono; ;
        System.out.print("Introduzca dni: ");
        dni = lectorStrings.nextLine();
        System.out.print("Introduzca nombre: ");
        nombre = lectorStrings.nextLine();
        System.out.print("Introduzca apellidos: ");
        apellido = lectorStrings.nextLine();
        System.out.print("Introduzca domicilio: ");
        domicilio = lectorStrings.nextLine();
        System.out.print("Introduzca telefono: ");
        telefono = lectorStrings.nextLine();  
        miGestorEmpleados.addEmpleado(dni, nombre, apellido, apellido, telefono); 
    }
    // SECCIÓN DE GESTIÓN DE CLIENTES
    /**
     * Este método es para manejar un menú especializado en la gestión 
     * de usuarios
     */
    private void manejarClientes(){
        int selector; 
        String dni; 
        boolean finalizado = false; 
        while(!finalizado){
            System.out.println(" ===================================================");
            System.out.println("|                   Gestor Clientes                 |");
            System.out.println(" ===================================================");
            System.out.println("| 1. Insertar cliente.                                |");   
            System.out.println("| 2. Dar de baja  a un cliente.                     |");
            System.out.println("| 3. Modificar datos de un cliente.                 |");
            System.out.println("| 4. Visualizar datos de un cliente.                |");
            System.out.println("| 5. Listar a todos los clientes.                   |");
            System.out.println("| 6. Listar fichas de compras de un cliente.        |");
            System.out.println("| 7. Atras.                                         |");
            System.out.println(" ===================================================");
            System.out.print("Introduzca opcion deseada:"); 
            selector = recogeEntero(); 
            switch(selector){
                case 1:
                System.out.print("Inserte Dni de cliente a insertar: "); 
                String dniInsertado = lectorStrings.nextLine(); 
                if(miGestorClientes.getElemento(dniInsertado) == null){
                    insertarCliente(dniInsertado); 
                }else{
                    System.out.println("Este cliente ya esta en el sistema. Si quiere modificar sus datos seleccione opcion adecuada.");
                }
                break;
                case 2:
                dni = recogerDniCliente();
                miGestorClientes.getElemento(dni).setAlta(false);    
                break; 
                case 3:
                dni = recogerDniCliente();
                miGestorClientes.modificarCliente(dni); 
                break; 
                case 4: 
                dni = recogerDniCliente(); 
                miGestorClientes.imprimeCliente(dni); 
                break; 
                case 5:
                miGestorClientes.listarClientes(); 
                break;
                case 6:
                dni = recogerDniCliente(); 
                miGestorClientes.getElemento(dni).imprimeAdquisiciones();
                break;
                case 7: 
                finalizado = true; 
                break;
                default:
                System.out.println("La opcion insertada no corresponde a ninguna operacion....");
                break;
            }
        }
    }

    /**
     * Este método recoge el Dni de un cliente
     * y comprueba que está en el sistema. En caso
     * negativo, vuelve a pedir dni.
     */
    private String recogerDniCliente(){
        System.out.println(" ===================================================");
        System.out.print  ("| Inserte dni del Cliente: ");
        String dni = lectorStrings.nextLine();
        System.out.println(" ===================================================");
        if(miGestorClientes.getElemento(dni) != null){
            return dni; 
        }else{
            System.out.println("Este dni no corresponde a ningun cliente");
            return recogerDniCliente(); 
        }
    }

    /**
     * Método que inserta cliente sin recibir datos como parámetros
     *  y pidiendo datos a través de un scanner.
     */
    public void insertarCliente(String dni){ 
        String nombre, apellido, domicilio, telefono; 
        System.out.print("Introduzca nombre: ");
        nombre = lectorStrings.nextLine();
        System.out.print("Introduzca appellidos: ");
        apellido = lectorStrings.nextLine();
        System.out.print("Introduzca domicilio: ");
        domicilio = lectorStrings.nextLine();
        System.out.print("Introduzca telefono: ");
        telefono = lectorStrings.nextLine(); 
        miGestorClientes.addCliente(dni,nombre,apellido,domicilio,telefono);
        miGestorClientes.getElemento(dni).getHistorialOperaciones().add(new Operacion(empleadoActual, miGestorClientes.getElemento(dni), "ALTA EN SISTEMA DE CLIENTE", null, fecha)); 
        System.out.println("------>Cliente insertado al sistema con exito<--------"); 
    }
    //SECCIÓN DE GESTIÓN DE ELECTRODOMÉSTICOS
    private void manejarProductos(){
        int selector; 
        String dni; 
        boolean finalizado = false; 
        while(!finalizado){
            System.out.println(" ===================================================");
            System.out.println("|              Gestor Electrodomésticos             |");
            System.out.println(" ===================================================");
            System.out.println("| 1. Añadir Electrodoméstico                        |");   
            System.out.println("| 2. Modificar datos de un producto                 |");
            System.out.println("| 3. Visualizar datos de un producto                |");
            System.out.println("| 4. Listar todos los productos en Tienda           |");
            System.out.println("| 5. Atras.                                         |");
            System.out.println(" ===================================================");
            System.out.print("Introduzca opcion deseada: "); 
            selector = recogeEntero();  
            switch(selector){
                case 1:
                miGestorElectro.crearElectro();
                System.out.println(" => Electrodomestico insertado al sistema con exito<="); 
                System.out.println(" ===================================================");
                break;
                case 2:
                miGestorElectro.modificaDatos(); 
                break; 
                case 3:
                miGestorElectro.visualizarProducto();  
                break; 
                case 4:
                miGestorElectro.listarElectros(); 
                break; 
                case 5:
                finalizado = true; 
                break; 
                default:
                System.out.println("La opcion insertada no corresponde a ninguna operacion. Repita");
                manejarProductos(); 
                break;
            }
        }
    }
    /**
     * Método que lee un número entero y comprueba que 
     * se ha introducido un dato correcto. 
     */
    private int recogeEntero(){
        int resultado; 
        if(lectorEnteros.hasNextInt()){
           resultado =  lectorEnteros.nextInt();   
        }else{
           lectorEnteros.next(); 
           System.out.print("Formato introducico incorrecto. Introduzca de nuevo: ");
           resultado = recogeEntero(); 
        }
        return resultado; 
    }
    private Double recogeDouble(){
       double precio = 0.0; 
       try{
           precio = Double.parseDouble(lectorStrings.nextLine());
       }catch(NumberFormatException e){
           System.out.println("Debe insertar un numero racional. Formato incorrecto. repita...");
           System.out.print("Introduzca numero racional (0.0): ");
           precio = recogeDouble(); 
       }
       return precio;   
    }
}


   


