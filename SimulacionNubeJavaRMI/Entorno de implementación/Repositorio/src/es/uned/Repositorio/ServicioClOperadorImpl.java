/**
 * 
 */
package es.uned.Repositorio;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.uned.common.Fichero;
import es.uned.common.ServicioClOperadorInterface;
import es.uned.common.manejadorExcepciones;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class ServicioClOperadorImpl extends UnicastRemoteObject implements ServicioClOperadorInterface {
	
	private static final long serialVersionUID = -7478353802670983787L;
	private String id_repositorio; 
	private String url_servicioClOperador; 
	protected ServicioClOperadorImpl(String id_repositorio, String url_servicioClOperador) throws RemoteException {
		super();
		this.url_servicioClOperador = url_servicioClOperador; 
		this.id_repositorio = id_repositorio; 
	}
	
	public boolean subirFichero(Fichero fichero_recibido, String id_cliente) throws RemoteException{
		try {
			String rutaNuevoFichero = System.getProperty("user.dir")+"/"+id_repositorio+"/"+id_cliente+"/"+fichero_recibido.obtenerNombre(); //Se obtiene la ruta de repositorio.jar se le inserta su propio id y el del cliente
			File nuevoFichero = new File(rutaNuevoFichero);
			if(nuevoFichero.exists()) {
				nuevoFichero.delete(); //Si existia un fichero previamente se elimina con su contenido
			}
			FileOutputStream x = new FileOutputStream(nuevoFichero); 
			fichero_recibido.escribirEn(x); 
			return true; 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			return false; 
		}
	}

	@Override
	public String borrarArchivo(String nombre_archivo, String id_cliente) throws RemoteException {
		try {
			String rutaFicheroBorrar = System.getProperty("user.dir")+"/"+id_repositorio+"/"+id_cliente+"/"+nombre_archivo; 
			File ficheroBorrar = new File(rutaFicheroBorrar); 
			if(ficheroBorrar.exists()) {
				ficheroBorrar.delete(); //Si existia un fichero previamente se elimina con su contenido
				return "BORRADO_EXITOSO"; 
			}else {
				return "FICHERO_NO_ENCONTRADO"; 
			}
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			return "FALLO_OPERACION"; 
		}
		
	}
	
	public String getURL()  throws RemoteException {
		return this.url_servicioClOperador; 
	}
	
	

	
}
