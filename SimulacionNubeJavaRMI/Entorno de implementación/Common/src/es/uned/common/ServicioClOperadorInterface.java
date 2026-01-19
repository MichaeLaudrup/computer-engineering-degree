/**
 * 
 */
package es.uned.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public interface ServicioClOperadorInterface extends Remote {
	public boolean subirFichero(Fichero fichero_recibido, String id_cliente) throws RemoteException; 
	public String borrarArchivo(String nombre_archivo, String id_cliente) throws RemoteException;
	public String getURL()  throws RemoteException; 
}
