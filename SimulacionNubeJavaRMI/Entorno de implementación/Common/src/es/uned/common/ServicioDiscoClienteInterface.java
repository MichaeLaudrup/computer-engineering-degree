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
public interface ServicioDiscoClienteInterface extends Remote{
	public String dameURLservicio()  throws RemoteException;
	public void imprimeListadoArchivos() throws RemoteException; 
	public String enviarArchivo_Repositorio(String URL_repo, String nombre_archivo) throws RemoteException; 
	public String borrarArchivo(String nombre_archivo, String URL_repo)  throws RemoteException; 
	void bajarArchivo(String url_servicioSrOperador, String nombreArchivo, String id_repo) throws RemoteException; 
	public void bajarArchivoCompartido(String url_servicioSrOperador, String nombreArchivo, String id_repo, String id_cliente) throws RemoteException;
	public String escogerFicheroParaSubir() throws RemoteException;
}
