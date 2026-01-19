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
public interface ServicioGestorInterface extends Remote {
	

	public String dameURLRepositorioCliente(String id_cliente) throws RemoteException; 
	public void listaClientesSistema() throws RemoteException; 
	public String ficherosClienteEnTexto(String id_cliente) throws RemoteException; 
	public void registrarSubidaFichero(String id_cliente, String nombreArchivoSubido) throws RemoteException; 
	public void registrarBorrado(String id_cliente, String nombreArchivoBorrado) throws RemoteException;
	public String ficherosEnPropiedad(String id_cliente) throws RemoteException;
	public String dameListaClienteOnline(String id_solicitante) throws RemoteException;
	public String compartirFichero(String nombreArchivoCompartir, String id_propiertario,String nombreCliente) throws RemoteException; 
	public String listarClientesRepo(String id_repo) throws RemoteException; 
	public String dameURLRepositorioServidor(String id_cliente) throws RemoteException;
	public boolean clienteTieneArchivo(String id_cliente, String nombreArchivo) throws RemoteException;
	public String dameIdRepoCliente(String id_cliente) throws RemoteException;
	public boolean esArchivoCompartido(String id_cliente, String nombreArchivo) throws RemoteException; ;
	public String obtenerIdRepoArchivoCompartido(String id_cliente, String nombreArchivo) throws RemoteException; ;
	public boolean repositorioEstaOnline(String id_repo) throws RemoteException; ;
	public String dameURLsegunRepoID(String id_repo) throws RemoteException;
	public String obtenerPropietarioArchivo(String nombreArchivo) throws RemoteException ;
	public boolean duplicadoNombreArchivo(String nombreArchivoSubido) throws RemoteException; 
}
