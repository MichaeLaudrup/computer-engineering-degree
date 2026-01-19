/**
 * 
 */
package es.uned.Servidor;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import es.uned.common.ServicioAutenticacionInterface;
import es.uned.common.ServicioDatosInterface;
import es.uned.common.Utilidades;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class ServicioAutenticacionImpl extends UnicastRemoteObject implements ServicioAutenticacionInterface {
	 
	private ServicioDatosInterface servicio_datos;
	private static final long serialVersionUID = 3611289542058032035L;
	protected ServicioAutenticacionImpl() throws RemoteException {
		super();
		try {
			servicio_datos = (ServicioDatosInterface)Naming.lookup(Utilidades.generarURLgenerica()+"baseDeDatos"); 
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Metodo que autentica a un usuario y devuelve un numero indicativo de si la autenticacion es correcta o no
	 */
	public String autenticarCliente(String nombre_cliente, String password) throws RemoteException {
		return servicio_datos.existeClienteConPassword(nombre_cliente, password); 
	}
	public String registrarCliente(String nombre_cliente, String password) throws RemoteException{ 
		return servicio_datos.insertarCliente(nombre_cliente, password); 
		 
	}
	@Override
	public String autenticarRepositorio(String nombreRepo, String password) throws RemoteException{
		return servicio_datos.existeRepoConPassword(nombreRepo, password); 
	}
	
	public boolean subirURLsrepo(String id_repo, String url_servidorOperador, String url_clienteOperador ) throws RemoteException {
		return servicio_datos.actualizarURLs(id_repo, url_servidorOperador, url_clienteOperador); 
	}
	@Override
	public String registrarRepositorio(String nombre_repositorio, String password) throws RemoteException{
		return servicio_datos.nuevoRepositorio(nombre_repositorio, password); 
	}
	@Override
	public void modoOffLine(String id_usuarioActual, String tipo_usuario) throws RemoteException {
		servicio_datos.usuarioFueraLinea(id_usuarioActual, tipo_usuario); 	
	}
	@Override
	public boolean subirURLcliente(String id_unico_cliente, String url_disco_cliente) throws RemoteException {
		return servicio_datos.actualizarURLcliente(id_unico_cliente, url_disco_cliente); 
	}
	@Override
	public boolean existeRepositorioOnline() throws RemoteException {
		return servicio_datos.existeRepoOnline(); 
	}
	
	
	
	
	
	
	
}