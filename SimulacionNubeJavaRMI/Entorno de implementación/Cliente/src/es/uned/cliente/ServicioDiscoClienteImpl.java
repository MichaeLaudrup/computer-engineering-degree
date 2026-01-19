/**
 * 
 */
package es.uned.cliente;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import es.uned.common.Fichero;
import es.uned.common.InterfazTextual;
import es.uned.common.ServicioClOperadorInterface;
import es.uned.common.ServicioDiscoClienteInterface;
import es.uned.common.ServicioSrOperadorInterface;
import es.uned.common.manejadorExcepciones;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class ServicioDiscoClienteImpl extends UnicastRemoteObject implements ServicioDiscoClienteInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6013997254401275395L;
	private String url_discoCiente;
	private String id_cliente; 
	private ArrayList<String> listadoNombresArchivos; 
	
	protected ServicioDiscoClienteImpl(String url_disco_cliente, String id_cliente) throws RemoteException {
		super();
		this.url_discoCiente = url_disco_cliente; 
		this.id_cliente = id_cliente; 
		listadoNombresArchivos = new ArrayList<>();
		
	}
	
	
	public String dameURLservicio()  throws RemoteException{
		return this.url_discoCiente; 
	}
	
	private void obtenerDatosEntornoCliente() throws RemoteException {
		this.listadoNombresArchivos = new ArrayList<>(); 
		String rutaCliente = System.getProperty("user.dir"); 
		File carpetaCliente = new File(rutaCliente); 
		String [] nombreArchivos = carpetaCliente.list(); 
		for(int i = 0; i < nombreArchivos.length; i++) {
			if(nombreArchivos[i].endsWith(".txt")) {
				listadoNombresArchivos.add(nombreArchivos[i]); 
			}
			
		}
	}
	
	
	public void imprimeListadoArchivos() throws RemoteException {
		System.out.println(" _______________________________________________________________________ ");
		for(int i = 0; i < listadoNombresArchivos.size(); i++) {
			System.out.println("| " +i + ". " + listadoNombresArchivos.get(i));
		}
		System.out.println(" _______________________________________________________________________ ");
	}


	public String escogerFicheroParaSubir() throws RemoteException {
		obtenerDatosEntornoCliente();
		imprimeListadoArchivos(); 
		System.out.print("Introduzca numero del fichero que desea subir: ");
		int seleccion = InterfazTextual.recogeNumero(); 
		return listadoNombresArchivos.get(seleccion); 
	}
	public String enviarArchivo_Repositorio(String URL_repo, String nombre_archivo) throws RemoteException { 
		Fichero fichero_subir = encapsularFicheroEnClase(nombre_archivo);
		System.out.println("La url es: " + URL_repo);
		try {
			ServicioClOperadorInterface servicioCliente_Operador = (ServicioClOperadorInterface)Naming.lookup(URL_repo); 
			if(servicioCliente_Operador.subirFichero(fichero_subir, id_cliente)) {
				System.out.println("El fichero " + fichero_subir.obtenerNombre() + " ha sido subido con exito al repositorio asociado del cliente con id: " + id_cliente);
				return "EXITO"; 
			}else {
				return "ERROR"; 
			}
			
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			//CONTINUAR MANDADO FICHERO A REPOSITORIO
			return "ERROR"; 
		}
	}
	
	private Fichero encapsularFicheroEnClase(String nombreFichero) throws RemoteException {
		Fichero encapsulacion = new Fichero(nombreFichero, id_cliente); 
		return encapsulacion; 
	}
	
	public String borrarArchivo(String nombre_archivo, String URL_repo)  throws RemoteException {
		try {
			ServicioClOperadorInterface servicioCliente_Operador = (ServicioClOperadorInterface)Naming.lookup(URL_repo); 
			return servicioCliente_Operador.borrarArchivo(nombre_archivo, id_cliente);
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			return "NO_BORRADO"; 
		}
	}
	


	@Override
	public void bajarArchivo(String url_servicioSrOperador, String nombreArchivo, String id_repo) throws RemoteException {
		try {
			ServicioSrOperadorInterface servicioSrOperador = (ServicioSrOperadorInterface)Naming.lookup(url_servicioSrOperador);
			Fichero ficheroBajada = servicioSrOperador.bajarArchivo(nombreArchivo, id_repo, id_cliente); 
			String rutaNuevoFichero = System.getProperty("user.dir")+"\\"+nombreArchivo; 
			File nuevoFichero = new File(rutaNuevoFichero);
			if(nuevoFichero.exists()) {
				nuevoFichero.delete(); //Si existia un fichero previamente se elimina con su contenido
			}
			FileOutputStream x = new FileOutputStream(nuevoFichero); 
			ficheroBajada.escribirEn(x); 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
		}	
	}

	@Override
	public void bajarArchivoCompartido(String url_servicioSrOperador, String nombreArchivo, String id_repo, String id_clienteCompartido) throws RemoteException {
		try {
			ServicioSrOperadorInterface servicioSrOperador = (ServicioSrOperadorInterface)Naming.lookup(url_servicioSrOperador);
			Fichero ficheroBajada = servicioSrOperador.bajarArchivo(nombreArchivo, id_repo, id_clienteCompartido); 
			String rutaNuevoFichero = System.getProperty("user.dir")+"\\"+nombreArchivo; 
			File nuevoFichero = new File(rutaNuevoFichero);
			if(nuevoFichero.exists()) {
				nuevoFichero.delete(); //Si existia un fichero previamente se elimina con su contenido
			}
			FileOutputStream x = new FileOutputStream(nuevoFichero); 
			ficheroBajada.escribirEn(x); 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
		}	
	}


}
