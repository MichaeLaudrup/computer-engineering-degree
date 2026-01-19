/**
 * 
 */
package es.uned.Repositorio;

import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.uned.common.Fichero;
import es.uned.common.ServicioGestorInterface;
import es.uned.common.ServicioSrOperadorInterface;
import es.uned.common.Utilidades;
import es.uned.common.manejadorExcepciones;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class ServicioSrOperadorImpl extends UnicastRemoteObject implements ServicioSrOperadorInterface {

	private static final long serialVersionUID = -4888707184612645117L;
    private String rutaAbsoluta_repositorio; 
    private String id_repositorio; 
	private String url_servicioSrOperador; 
	protected ServicioSrOperadorImpl(String id_repositorio, String url_servicioSrOperador ) throws RemoteException {
		super();
		this.id_repositorio = id_repositorio; 
		this.url_servicioSrOperador = url_servicioSrOperador; 
		
	}
	
	@Override
	public String crearCarpetaNuevoCliente(String id_nuevo_cliente) throws RemoteException {
		File directorio = new File(this.rutaAbsoluta_repositorio+"/"+id_nuevo_cliente); 
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
            } else {
                System.out.println("Error al crear directorio del repositorio");
                return "NO_CREADO"; 
            }
        }
        return directorio.getAbsolutePath(); 
	}
	public void actualizarRutaRepositorio(String ruta_nueva) throws RemoteException {
		this.rutaAbsoluta_repositorio = ruta_nueva; 
		
	}
	public String getURLServicioSrOperador() {
		return this.url_servicioSrOperador; 
	}

	@Override
	public String obtenerListadoDeClientes() throws RemoteException {
		try {
			ServicioGestorInterface servicio_gestor = (ServicioGestorInterface)Naming.lookup(Utilidades.generarURLgenerica()+"gestor");
			return servicio_gestor.listarClientesRepo(id_repositorio); 
		} catch (Exception e) {
			return e.getMessage();	
		}	
	}

	@Override
	public String imprimirFicherosCliente(String id_cliente) throws RemoteException {
		try {
			ServicioGestorInterface servicio_gestor = (ServicioGestorInterface)Naming.lookup(Utilidades.generarURLgenerica()+"gestor");
			return servicio_gestor.ficherosEnPropiedad(id_cliente); 
		} catch (Exception e) {
			return e.getMessage();
			
		}
	}

	public Fichero bajarArchivo(String nombreArchivo, String id_repo, String id_cliente) throws RemoteException {
		try {
			String ruta = System.getProperty("user.dir")+ "\\"+ id_repo + "\\" + id_cliente ; 
			Fichero fichero_bajar = new Fichero(ruta, nombreArchivo, id_cliente);
			return fichero_bajar; 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
			return null; 
		}
		
	}
	
}
