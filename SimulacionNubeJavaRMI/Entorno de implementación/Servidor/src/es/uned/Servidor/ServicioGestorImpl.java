/**
 * 
 */
package es.uned.Servidor;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.uned.common.ServicioDatosInterface;
import es.uned.common.ServicioGestorInterface;
import es.uned.common.Utilidades;
import es.uned.common.manejadorExcepciones;


/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class ServicioGestorImpl extends UnicastRemoteObject implements ServicioGestorInterface {
	private static final long serialVersionUID = -6505381068223747282L;
	private ServicioDatosInterface servicio_datos;
	
	protected ServicioGestorImpl() throws RemoteException {
		super();
		try {
			servicio_datos = (ServicioDatosInterface)Naming.lookup(Utilidades.generarURLgenerica()+"baseDeDatos"); 
		}catch(Exception e) {
			manejadorExcepciones.tratarExcepcion(e);
		}	
	}

	@Override
	public String dameURLRepositorioCliente(String id_cliente) throws RemoteException {
		return servicio_datos.dameURLservicioClOperador(id_cliente); 
	}

	@Override
	public void listaClientesSistema() throws RemoteException {
		servicio_datos.listarClientes();	
	}

	@Override
	public void registrarSubidaFichero(String id_cliente, String nombreArchivoSubido) throws RemoteException {
		servicio_datos.registrarMetaDatoSubida(id_cliente, nombreArchivoSubido); 	
	}

	@Override
	public String ficherosClienteEnTexto(String id_cliente) throws RemoteException {
		return servicio_datos.listaFicherosClienteTO_STRING(id_cliente);
	}

	@Override
	public void registrarBorrado(String id_cliente, String nombreArchivoBorrado) throws RemoteException {
		servicio_datos.registrarMetaDatoBorrado(id_cliente, nombreArchivoBorrado); 
	}
	public String ficherosEnPropiedad(String id_cliente) throws RemoteException {
		return servicio_datos.ficherosPropios(id_cliente); 
	}

	@Override
	public String dameListaClienteOnline(String id_solicitante) throws RemoteException {
		return servicio_datos.getListaClienteOnline(id_solicitante); 
	}

	@Override
	public String compartirFichero(String nombreArchivoCompartir,String id_propietario, String nombreCliente) throws RemoteException {
		return servicio_datos.compartirFicheroMetaDatos(nombreArchivoCompartir, id_propietario ,nombreCliente);	
	}

	@Override
	public String listarClientesRepo(String id_repo) throws RemoteException {
		return servicio_datos.listarClientesRepositorio(id_repo); 
	}

	@Override
	public String dameURLRepositorioServidor(String id_cliente) throws RemoteException {
		return servicio_datos.urlSrOperador(id_cliente);
	}

	@Override
	public boolean clienteTieneArchivo(String id_cliente, String nombreArchivo) throws RemoteException {
		return servicio_datos.clientePoseeArchivo(id_cliente, nombreArchivo); 
	}

	@Override
	public String dameIdRepoCliente(String id_cliente) throws RemoteException {
		return servicio_datos.idRepoCliente(id_cliente);
	}

	@Override
	public boolean esArchivoCompartido(String id_cliente, String nombreArchivo) throws RemoteException {
		return servicio_datos.archivoCompartido(id_cliente, nombreArchivo); 
	}

	@Override
	public String obtenerIdRepoArchivoCompartido(String id_cliente, String nombreArchivo) throws RemoteException {
		return servicio_datos.idRepoArchivoCompartido(id_cliente, nombreArchivo); 
	}

	@Override
	public boolean repositorioEstaOnline(String id_repo) throws RemoteException {
		return servicio_datos.repositorioOnline(id_repo); 
	}

	@Override
	public String dameURLsegunRepoID(String id_repo) throws RemoteException {
		return servicio_datos.dameURLSrOperadorporRepoID(id_repo); 
	}

	@Override
	public String obtenerPropietarioArchivo(String nombreArchivo)  throws RemoteException{
		return servicio_datos.damePropietarioSegunArchivo(nombreArchivo);
	}

	@Override
	public boolean duplicadoNombreArchivo(String nombreArchivoSubido) throws RemoteException {
		return servicio_datos.existeArchivoDuplicado(nombreArchivoSubido); 
	}


	
	


}
