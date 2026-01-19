/**
 * 
 */
package es.uned.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Michael Laudrup Luis Gonzalez 
 *
 */
public interface ServicioAutenticacionInterface extends Remote {
	public String autenticarCliente(String nombre_cliente, String password) throws RemoteException; 
	public String registrarCliente(String nombre_cliente, String password) throws RemoteException;
	public String autenticarRepositorio(String nombreRepo, String password) throws RemoteException;
	public String registrarRepositorio(String nombre_repositorio, String password) throws RemoteException;
	public void modoOffLine(String id_usuarioActual, String tipo_usuario) throws RemoteException;
	public boolean subirURLsrepo(String id_repo, String url_servidorOperador, String url_clienteOperador ) throws RemoteException; 
	public boolean subirURLcliente(String id_unico_cliente, String url_disco_cliente) throws RemoteException; 
	public boolean existeRepositorioOnline() throws RemoteException; 
}
