/**
 * 
 */
package es.uned.Repositorio;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import es.uned.common.InterfazTextual;
import es.uned.common.ServicioAutenticacionInterface;
import es.uned.common.ServicioClOperadorInterface;
import es.uned.common.ServicioGestorInterface;
import es.uned.common.ServicioSrOperadorInterface;
import es.uned.common.Utilidades;
import es.uned.common.manejadorExcepciones;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class Repositorio {
	//ATRIBUTOS ESTATICOS
	public static final int puertoServiciosRepositorio = 2012; 
	public static Registry registro_repositorio; 
	public static ServicioClOperadorInterface servicioRepo_Cliente; 
	public static ServicioSrOperadorInterface servicioRepo_Servidor; 
    public static ServicioAutenticacionInterface servicio_autenticacion; 
    public static ServicioGestorInterface servicio_gestion; 
	public static boolean terminarPrograma = false;

	//PARTE ESTATICA DE LA CLASE
	  
	public static void main(String[] args) {
		String id_repositorio_actual; 
		   terminarPrograma = false; 
			do {
				id_repositorio_actual = autenticarRepositorio(); 
		   }while(id_repositorio_actual.equalsIgnoreCase("NO_AUTENTICADO") && !terminarPrograma); 
	
			if(!terminarPrograma) {
				try {
					Repositorio repo = new Repositorio (id_repositorio_actual); //Una vez autenticado se crea un objeto repositorio
					servicio_gestion = (ServicioGestorInterface) Naming.lookup(Utilidades.generarURLgenerica()+"gestor"); 
					repo.iniciarMenuRepositorio();
				}catch(Exception e) {
					manejadorExcepciones.tratarExcepcion(e);
				}
				
			}else {
				System.out.println("MUCHAS GRACIAS POR USAR NUESTRO SERVICIO. VUELVA PRONTO");
				Utilidades.esperarY_limpiarPantalla();
			}

	}
	
	private static String autenticarRepositorio() {
		try {
		   servicio_autenticacion = (ServicioAutenticacionInterface) Naming.lookup(Utilidades.generarURLgenerica()+"autenticador" ); //se necesitara servicio de autenticacion si o si
		   InterfazTextual.bienvenida("repositorio"); //Se imprime menu de bienvenida con opciones (1) Autenticar; (2) registrars; (3) salir
	       int seleccion = InterfazTextual.recogeNumero();
	       if(seleccion == 1 || seleccion == 2 ) {
	    	   String nombre_repo = InterfazTextual.imprime_y_pide("Introduce nombre del repositorio: "); 
	    	   String password = InterfazTextual.imprime_y_pide("Introduce password del repositorio: ");   
	    	   if(seleccion == 2) {
	    		   String resultado_autenticacion = servicio_autenticacion.autenticarRepositorio(nombre_repo, password); 
		    	   if(resultado_autenticacion.startsWith("RP_")) {
		    		   return resultado_autenticacion; 
		    	   }else {
		    		   manejadorExcepciones.tratarError("AUTENTICACION", resultado_autenticacion, "repositorio");
		    	   } 
	    	   }else{
	    		   String resultado_registro = servicio_autenticacion.registrarRepositorio(nombre_repo, password); 
	    		   if(resultado_registro.equalsIgnoreCase("REGISTRO_EXITOSO")) {
	    			   System.out.println("El registro del repositorio ha sido realizado con exito. Bienvenido. ");
	    		   }else {
	    			   manejadorExcepciones.tratarError("REGISTRO", resultado_registro, "repositorio");
	    		   }
	    	   }
	       }else if(seleccion == 3) {
	    	   terminarPrograma = true; 
	       }else {
	    	   System.out.println("Opcion introducida no concuerda con ninguna de las proporcionadas. Repita");
	       }
	       Utilidades.esperarY_limpiarPantalla();
	       return "NO_AUTENTICADO"; 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			return "NO_AUTENTICADO"; 
		}
	}
	
	
	
	public static void crearCarpetaRepositorio(String id_nuevoRepositorio) throws RemoteException {
        File directorio = new File(id_nuevoRepositorio);
        servicioRepo_Servidor.actualizarRutaRepositorio(directorio.getAbsolutePath()); 
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
            } else {
                System.out.println("Error al crear directorio del repositorio");
            }
        }
	}
	
	//=====================================================================================
	//                    PARTE DE INSTANCIA DE LA CLASE (PARTE NO ESTATICA) 
	//=====================================================================================
	//ATRIBUTOS DE INSTANCIA
	private String id_repo_actual; 
	private String url_base_repositorio; 
	//CONSTRUCTOR DE INSTANCIA
	public Repositorio(String id_repo) {
		id_repo_actual = id_repo; 
		url_base_repositorio = "rmi://" + Utilidades.getIPlocalHost() + ":"+ puertoServiciosRepositorio +"/"+id_repo; 
	}
	
	//METODOS DE INSTANCIA
	private void iniciarMenuRepositorio() {
		try {
			iniciarServicios();
			crearCarpetaRepositorio(id_repo_actual);
			boolean salir = false; 
			while(!salir) {
				InterfazTextual.imprimirMenuRepositorio(id_repo_actual); 
				switch(InterfazTextual.recogeNumero()) {
				case 1: 
					System.out.println(servicioRepo_Servidor.obtenerListadoDeClientes()); 
					break; 
				case 2:
					System.out.println(servicioRepo_Servidor.obtenerListadoDeClientes());
					String id_cliente = InterfazTextual.imprime_y_pide("Introduzca identificador del cliente para el que desea ver sus ficheros: "); 
					System.out.println(servicioRepo_Servidor.imprimirFicherosCliente(id_cliente));
					break; 
				case 3: 
					servicio_autenticacion.modoOffLine(id_repo_actual, "repositorio");
					salir = true; 
					break; 
					default: 
						
				}				
			}
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			
		}finally {
			System.exit(0); 
		}
	}
	public void iniciarServicios() throws RemoteException, MalformedURLException, NotBoundException {
		String url_clienteOperador = url_base_repositorio+"/ClienteOperador"; 
		String url_servidorOperador = url_base_repositorio+"/ServidorOperador"; 
		System.out.println("======================================================================="); 
		System.out.println("INFO: El repositorio esta escuchando en la direccion IP " + Utilidades.getIPlocalHost() + " y el puerto " + puertoServiciosRepositorio );
		registro_repositorio = Utilidades.cargarRegistro(puertoServiciosRepositorio); 
		Utilidades.setCodeBase(ServicioClOperadorInterface.class);
		servicioRepo_Cliente = (ServicioClOperadorInterface) new ServicioClOperadorImpl(id_repo_actual, url_clienteOperador); 
		servicioRepo_Servidor = (ServicioSrOperadorInterface) new ServicioSrOperadorImpl(id_repo_actual, url_servidorOperador); 
		Naming.rebind(url_clienteOperador, servicioRepo_Cliente);
		Naming.rebind(url_servidorOperador, servicioRepo_Servidor);
		servicio_autenticacion.subirURLsrepo(id_repo_actual, url_servidorOperador, url_clienteOperador); 
		System.out.println("INFO: Los servicios hacia el cliente y el servidor por parte del servidor han sido levantados con las siguientes URLs. ");
		System.out.println("INFO: URL cliente-operador: " + url_base_repositorio+ "/ClienteOperador"); 
		System.out.println("INFO: URL servidor-operador: " + url_base_repositorio+ "/ServidorOperador"); 
		System.out.println("======================================================================="); 
		//Utilidades.listarServicios(url_base_repositorio); //Descomentar linea para depuracion
	}
	
	public void desvincularServicios() {
		try {
			Naming.unbind(servicioRepo_Cliente.getURL());
			Naming.unbind(servicioRepo_Servidor.getURLServicioSrOperador());
			if(!servicio_autenticacion.existeRepositorioOnline()) { //si NO existe repositorio online se da de baja el registro
				UnicastRemoteObject.unexportObject(registro_repositorio, true); 
			}
		} catch (Exception e) {			
			manejadorExcepciones.tratarExcepcion(e);
		}
	}
}
