/**
 * 
 */
package es.uned.Servidor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import es.uned.common.*; 
/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class Servidor {
    public final static int PUERTO = 1099;
    public static Registry registroServidor; 
    public static String url_generica; 
	public static void main(String[] args)  {
		try {
			Utilidades.setCodeBase(ServicioDatosInterface.class);
			//PUESTA EN MARCHA DEL SERVIDOR CON SUS INTERFACES ASOCIADAS
			System.out.println("=========================================================================================================");
		    registroServidor = Utilidades.cargarRegistro(PUERTO);    //se crea un nuevo registro o se carga registro previamente creado 
			url_generica = Utilidades.generarURLgenerica(); 
			System.out.println("INFO: El servidor esta escuchando en la direccion IP " + Utilidades.localHostIP + " y el puerto "+ Utilidades.PUERTO);		
			enlazarServicios();
			//Utilidades.listarServicios(url_generica);   //Descomentar solo si se quiere comprobar que se han iniciado los servicios correctamente.
			
			//MENU DE INTERACCION CON EL USUARIO
			ejecutarMenuServidor(); //Solo saltamos a la siguiente instrucccion cuando le damos a salir en el menu 
			
			//DESCONEXION DEL SERVIDOR
			desenlazarServicios();
			System.exit(0);
		}catch(Exception x) {
			//PENDIENTE
		}
	}
	public static void ejecutarMenuServidor() {
		int seleccion; 
		do {
			InterfazTextual.imprimeMenuServidor();
			seleccion = InterfazTextual.recogeNumero();
			try {
				switch(seleccion){
				case 1: 
					((ServicioDatosInterface)Naming.lookup(url_generica+"baseDeDatos")).listarClientes();
					break; 
				case 2: 
					((ServicioDatosInterface)Naming.lookup(url_generica+"baseDeDatos")).listarRepositorios();
					break; 
				case 3: 
					((ServicioDatosInterface)Naming.lookup(url_generica+"baseDeDatos")).listarParejaRepositorio_cliente();
					break;
				case 4: 
					break; 
				default: 
					System.out.println("El numero introducido no corresponde con ninguna opcion del menu, repita. ");	
				}
			}catch(Exception e) {
				manejadorExcepciones.tratarExcepcion(e); 
			}
		}while(seleccion != 4); 	
	}
	
	/*
	 * Este metodo da de alta en un registro los objetos remotos del servidor (autenticacion, gestion y base de datos) 
	 */
	public static void enlazarServicios() throws RemoteException, MalformedURLException {
		try {
			Naming.rebind(url_generica+"baseDeDatos", new ServicioDatosImpl());
			Naming.rebind(url_generica+"autenticador", new ServicioAutenticacionImpl());
			Naming.rebind(url_generica+"gestor", new ServicioGestorImpl());
		}catch(Exception x) {
			x.printStackTrace();
		}
		System.out.println("INFO: se han inicializado correctamente los servicios de autenticacion, de gestion y de datos del servidor en las siguientes URLs");
		System.out.println("INFO: URL Servicio de datos: "+ url_generica+"baseDeDatos" );
		System.out.println("INFO: URL Servicio Autenticador: "+ url_generica+"autenticador");
		System.out.println("INFO: URL Servicio Gestor: "+ url_generica+"gestor"); 
		System.out.println("=========================================================================================================");
	}	
	public static void desenlazarServicios() throws RemoteException, MalformedURLException, NotBoundException{
		Naming.unbind(url_generica+"autenticador");
		Naming.unbind(url_generica+"baseDeDatos");
		Naming.unbind(url_generica+"gestor");	
		UnicastRemoteObject.unexportObject(registroServidor,true); 
	}
	
	

	

}
