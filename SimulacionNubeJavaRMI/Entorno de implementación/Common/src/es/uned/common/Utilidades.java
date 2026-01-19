/**
 * 
 */
package es.uned.common;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Esta clase contiene metodos que podran ser utilizados por todos los actores del sistema
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class Utilidades {
	  
	  public static final String CODEBASE = "java.rmi.server.codebase";
	  public static final int PUERTO = 1099 ; 
	  public static String localHostIP ="NO_DETERMINADA"; 
	  
	  public static void setCodeBase(Class<?> claseInvocante) {
		  String ruta = System.getProperty(CODEBASE);                   
		  String localizacion_clase = obtenerRutaClase(claseInvocante); //Obtenemos la ruta donde se encuentra la clase
		  if(ruta != null && !ruta.isEmpty()) {
			  System.setProperty(CODEBASE, (ruta + " " + localizacion_clase) );
		  }else {
			  System.setProperty(CODEBASE, localizacion_clase); 
		  }
	  }   
	  private static String obtenerRutaClase(Class<?> claseActual) {
		  return claseActual.getProtectionDomain().getCodeSource().getLocation().toString(); //Obtenemos en formato String la ruta (el path) donde se encuentra la clase		  
	  }


	public static String generarURLgenerica() {
		try {
			String localHostIP = getIPlocalHost();  
			String url_generica = "rmi://"+localHostIP+":"+ PUERTO + "/"; 
			return url_generica; 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			return "HOST_DESCONOCIDO"; 
		}
	}
	 
	public static String getIPlocalHost()  {
		try {
			localHostIP = (InetAddress.getLocalHost()).toString(); //direccion IP del local host
			localHostIP = localHostIP.substring(localHostIP.length()-12,localHostIP.length()); //Obtenemos la direccion IP del local host
			return localHostIP; 
		}catch(Exception e) {
			System.err.print("Ha habido un error al intentar encontrar la direccion IP del localhost");
			return "localhost"; 
		}
		
	}
	
	public static void esperarY_limpiarPantalla() {
		try
	    {
			Thread.sleep (500); //esperamos dos segundos  
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	
	
	
	/*
	 * Este metodo crea un nuevo registro donde almacenar objetos remotos en el servidor RMI o carga un registro previamente creado
	 * @param numero de puerto asociado al registro bien a crear o a cargar; 
	 */
	public static Registry cargarRegistro(int num_puerto) throws RemoteException  {
		String accion = "creado"; 
		try {			
			return LocateRegistry.createRegistry(num_puerto);//Primero intenta retornar un nuevo registro con el puerto 2012
		}catch(Exception x) {   //se captura excepcion en el caso de que en el puerto donde se desea crear el registro ya exista uno previo
			accion = "cargado";
			return LocateRegistry.getRegistry(num_puerto); //se retorna un registro que ya habia sido creado previamente
		}finally {
			System.out.println("INFO: Se ha "+ accion +" el registro situado en el puerto numero "+ num_puerto + " exitosamente.");
		}
	}
	/*
	 * Metodo para comprobar que los servicios estan cargados en orden 
	 */
	public static void listarServicios(String url_generica) throws RemoteException, MalformedURLException, NotBoundException{
		String[] listado = Naming.list(url_generica); 
		for(String objetoRemoto: listado) {
			System.out.println(Naming.lookup(objetoRemoto));
		}	
	}
	  
}
