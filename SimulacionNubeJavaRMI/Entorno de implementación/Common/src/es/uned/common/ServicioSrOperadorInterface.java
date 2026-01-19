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
public interface ServicioSrOperadorInterface extends Remote {
	//Devuelve la ruta de donde esta el cliente
	public String crearCarpetaNuevoCliente(String id_nuevo_cliente) throws RemoteException; 
	public void actualizarRutaRepositorio(String ruta_nueva) throws RemoteException; 
	public String getURLServicioSrOperador()  throws RemoteException;
	public String obtenerListadoDeClientes() throws RemoteException; 
	public String imprimirFicherosCliente(String id_cliente) throws RemoteException;
	public Fichero bajarArchivo(String nombreArchivo, String id_repo, String id_cliente) throws RemoteException; 
}
