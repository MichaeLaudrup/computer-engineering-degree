/**
 * 
 */
package es.uned.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import es.uned.common.InterfazTextual;
import es.uned.common.ServicioAutenticacionInterface;
import es.uned.common.ServicioDiscoClienteInterface;
import es.uned.common.ServicioGestorInterface;
import es.uned.common.Utilidades;
import es.uned.common.manejadorExcepciones;
/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class Cliente {
	private static boolean terminarPrograma; 
	public static Registry registro_servicioCliente; 
	public static int puerto_ServicioCliente = 2013; 
    public static ServicioAutenticacionInterface servicio_autenticacion;  
    
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
	   String id_cliente_actual; 
	   terminarPrograma = false; 
		do {
		   id_cliente_actual = autenticarCliente(); 
	   }while((id_cliente_actual).equalsIgnoreCase("NO_AUTENTICADO") && !terminarPrograma); 
	   
		if(!terminarPrograma && id_cliente_actual.startsWith("CL_")) {
			Cliente clienteAutenticado = new Cliente(id_cliente_actual); 
			clienteAutenticado.iniciarMenuCliente(); 
		}else {
			System.out.println("MUCHAS GRACIAS POR USAR NUESTRO SERVICIO. VUELVA PRONTO");
			Utilidades.esperarY_limpiarPantalla();
		}
	} 
	
	public static String autenticarCliente() {
		try {
		   servicio_autenticacion = (ServicioAutenticacionInterface) Naming.lookup(Utilidades.generarURLgenerica()+"autenticador" ); //se necesitara servicio de autenticacion si o si
		   InterfazTextual.bienvenida("cliente"); //Se imprime menu de bienvenida con opciones (1) Autenticar; (2) registrarse; (3) salir
	       int seleccion = InterfazTextual.recogeNumero(); 
	       String nombre_cliente= "", password ="";
	       switch(seleccion) {
	       case 1: 
	    	   nombre_cliente = InterfazTextual.imprime_y_pide("Introduzca nombre deseado: "); 
	    	   password = InterfazTextual.imprime_y_pide("Introduzca password deseado: "); 
	    	   String resultado_registro = servicio_autenticacion.registrarCliente(nombre_cliente, password);
	    	   
	    	   if(resultado_registro.equalsIgnoreCase("REGISTRO_EXITOSO")) {
	    		   System.out.println("El usuario se ha registro con exito en el sistema. Bienvenido. ");
	    	   }else {
	    		   manejadorExcepciones.tratarError("REGISTRO", resultado_registro, "cliente");  
	    	   }
	    	   break;
	       case 2: 
	    	   nombre_cliente = InterfazTextual.imprime_y_pide("Introduzca nombre usuario: "); 
	    	   password = InterfazTextual.imprime_y_pide("Introduzca password usuario: "); 
	    	   String resultado_autenticacion = servicio_autenticacion.autenticarCliente(nombre_cliente, password);
	    	   if(resultado_autenticacion.startsWith("CL_")) {
	    		   return resultado_autenticacion; 
	    	   }else {
	    		   manejadorExcepciones.tratarError("AUTENTICACION", resultado_autenticacion, "cliente");
	    		   break; 
	    	   }
	       case 3: 
	    	   terminarPrograma = true;
	    	   break; 
	       default: 
	    	   System.out.println("Opcion introducida no concuerda con ninguna de las proporcionadas. Repita");
	       }
	       Utilidades.esperarY_limpiarPantalla();
	       return "NO_AUTENTICADO"; 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			return "NO_AUTENTICADO"; 
		}
	}
	
	//===================================================
	// PARTE DE INSTANCIA DE LA CLASE
	//====================================================
	//Campos de instancia de clase
	private String id_cliente;
	private ServicioDiscoClienteInterface discoCliente; 
	private ServicioGestorInterface servicio_gestor; 
	//Constructor de instancia de clase
	public Cliente (String id_unico_cliente) {
		this.id_cliente = id_unico_cliente;
		this.discoCliente = null; 
	}
	
	//METODOS DE INSTANCIA DE CLASE
	public void iniciarMenuCliente() throws RemoteException, MalformedURLException, NotBoundException {
		boolean salir = false; 
		enlazarServicioDisco();
	    establecerConexionServicioGestor();
	    String url_repo_asociado = servicio_gestor.dameURLRepositorioCliente(id_cliente); 
	    while(!salir) {
	    	InterfazTextual.imprimeMenuCliente(id_cliente);
	    	switch(InterfazTextual.recogeNumero()) {
			case 1: //este caso es que cuando el cliente quiere subir un fichero
				String nombreArchivoSubido = discoCliente.escogerFicheroParaSubir();
				if(!servicio_gestor.duplicadoNombreArchivo(nombreArchivoSubido)) {
					String resultado_envio = discoCliente.enviarArchivo_Repositorio(url_repo_asociado, nombreArchivoSubido); 
					if(resultado_envio.equalsIgnoreCase("EXITO")) {
						servicio_gestor.registrarSubidaFichero(id_cliente, nombreArchivoSubido); 
						System.out.println("el archivo " + nombreArchivoSubido +" subido con extio");
					}else {
						System.out.println("Ha habido un error en el envio del archivo");
					}
				}else {
					System.err.println("El nombre del archivo que se desea subir ya existe en el sistema");
				}
				break;
			case 2: //El cliente deja bajar un fichero
				//REPO BUSCADO NO ESTE ONLINE; SE SOLICITE UN FICHERO COMPARTIDO
				System.out.println(servicio_gestor.ficherosClienteEnTexto(id_cliente));
				String nombreArchivo = InterfazTextual.imprime_y_pide("Introduzca nombre del fichero que desea bajar: "); 
				String id_repo; 
				if(servicio_gestor.clienteTieneArchivo(id_cliente, nombreArchivo)) {
					id_repo = servicio_gestor.dameIdRepoCliente(id_cliente);
					String urlRepositorioServidor = servicio_gestor.dameURLRepositorioServidor(id_cliente); 
					discoCliente.bajarArchivo(urlRepositorioServidor, nombreArchivo,id_repo); 
					System.out.println("el archivo " + nombreArchivo +" ha sido bajado con exito");
				}else if(servicio_gestor.esArchivoCompartido(id_cliente, nombreArchivo)){
					id_repo = servicio_gestor.obtenerIdRepoArchivoCompartido(id_cliente, nombreArchivo); 
					if(servicio_gestor.repositorioEstaOnline(id_repo)) {
						String urlRepositorioServidor = servicio_gestor.dameURLsegunRepoID(id_repo);
						String clienteACompartir =servicio_gestor.obtenerPropietarioArchivo(nombreArchivo);
						discoCliente.bajarArchivoCompartido(urlRepositorioServidor, nombreArchivo, id_repo, clienteACompartir);
						System.out.println("el archivo compartido " + nombreArchivo +" ha sido bajado con exito");
					}else {
						manejadorExcepciones.tratarError("BAJADA", "REPO_COMPARTIDO_OFFLINE", id_repo);
					}
				}else {
					manejadorExcepciones.tratarError("BAJADA", "NOMBRE_NO_EXISTENTE", nombreArchivo);
				}
				break; 
			case 3: //El cliente desea borrar un fichero
				System.out.println(servicio_gestor.ficherosEnPropiedad(id_cliente));
				String nombre_Archivo = InterfazTextual.imprime_y_pide("Introduzca nombre del archivo que desea borrar (nombre SENSIBLE a mayusculas y debe terminar en .txt): "); 
				if(discoCliente.borrarArchivo(nombre_Archivo,url_repo_asociado).equalsIgnoreCase("BORRADO_EXITOSO")){
					servicio_gestor.registrarBorrado(id_cliente,nombre_Archivo); 
					System.out.println("el archivo " + nombre_Archivo +" ha sido borrado con exito");
				}else {
					System.out.println("Hubo un error en el borrado de archivo. Repita");
				}
				break; 
			case 4: //El cliente desea compartir un fichero suyo propio con otro cliente
				System.out.println(servicio_gestor.ficherosEnPropiedad(id_cliente));
				String nombreArchivoCompartir = InterfazTextual.imprime_y_pide("Introduzca nombre del archivo que desea compartir (nombre SENSIBLE a mayusculas y debe terminar en .txt): ");
				System.out.println(servicio_gestor.dameListaClienteOnline(id_cliente)); 
				String nombreCliente = InterfazTextual.imprime_y_pide("Introduzca nombre del cliente al que desea compartir: ");
				String resultadoOperacionCompartir = servicio_gestor.compartirFichero(nombreArchivoCompartir,id_cliente, nombreCliente); 
				
				if(!resultadoOperacionCompartir.startsWith("CL_")) {
					manejadorExcepciones.tratarError("COMPARTIR", resultadoOperacionCompartir, "");
				}else {
					System.out.println("El archivo "+ nombreArchivoCompartir + " ha sido compartido con exito");
				}
				break; 
			case 5: //Desea ver sus ficheros tanto suyos propios como compartidos
				System.out.println(servicio_gestor.ficherosClienteEnTexto(id_cliente));
				break; 
			case 6: //Desea ver los clientes en linea
				System.out.println(servicio_gestor.dameListaClienteOnline(id_cliente));
				break; 
			case 7: //Salir del sistema
				servicio_autenticacion.modoOffLine(id_cliente, "cliente");
				desvincularServicios(); 
				salir = true; 
				break; 
			default: 
				System.out.println("Opcion introducida no corresponde con ninguna de las ofrecidas. Seleccione opcion correcta");
				Utilidades.esperarY_limpiarPantalla();
				iniciarMenuCliente(); 		
	    	}
	    }
	    System.exit(0);
	}
	public void enlazarServicioDisco() {
		try {
			System.out.println("=========================================================================================================");
			Utilidades.setCodeBase(ServicioDiscoClienteInterface.class);
			registro_servicioCliente = Utilidades.cargarRegistro(puerto_ServicioCliente); 
			String url_disco_cliente = "rmi://" + Utilidades.getIPlocalHost() + ":" + puerto_ServicioCliente + "/DiscoCliente/" + id_cliente;
			discoCliente = (ServicioDiscoClienteInterface) new ServicioDiscoClienteImpl(url_disco_cliente, id_cliente);
			Naming.rebind(url_disco_cliente, discoCliente);
			servicio_autenticacion.subirURLcliente(id_cliente, url_disco_cliente);
			System.out.println("INFO: Se ha levantado el servicio del cliente con ID: "+ id_cliente + " en la siguiente URL...");
			System.out.println("INFO: URL disco de cliente " + id_cliente + " : " + url_disco_cliente);
			System.out.println("==========================================================================================================");
			//Utilidades.listarServicios(url_disco);
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
		}	
	}
	public void establecerConexionServicioGestor() {
		try {
			servicio_gestor = (ServicioGestorInterface)Naming.lookup(Utilidades.generarURLgenerica()+"gestor"); 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
		}
	}
	public void desvincularServicios() {
		try {
			Naming.unbind(discoCliente.dameURLservicio()); 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
		}	
	}

}
