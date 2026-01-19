package es.uned.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz para los datos de la apliacion (clientes, repositorios, metadatos y ficheros)
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public interface ServicioDatosInterface extends Remote {
	 /*funcionalidades genericas*/
     public void listarClientes() throws RemoteException;
     public void listarRepositorios() throws RemoteException; 
     public void listarParejaRepositorio_cliente() throws RemoteException; 
     
     /*Funcionalidades que necesitara el autenticador*/
    public String existeClienteConPassword(String id_unico, String password)  throws RemoteException;  //se busca cliente por identificador de texto
	public String existeRepoConPassword(String nombre_repo, String password) throws RemoteException;
    public String insertarCliente(String nombre_cliente, String password) throws RemoteException;
	public String nuevoRepositorio(String nombreRepositorio, String password) throws RemoteException;
	public void usuarioFueraLinea(String id_usuarioActual, String tipo_usuario) throws RemoteException;
	public boolean actualizarURLs(String id_repo, String url_servidorOperador, String url_clienteOperador) throws RemoteException;
	public boolean actualizarURLcliente(String id_unico_cliente, String url_disco_cliente)  throws RemoteException;
	public String dameURLservicioClOperador(String id_cliente) throws RemoteException;
	public void registrarMetaDatoSubida(String id_cliente, String nombreArchivoSubido) throws RemoteException; 
	public String listaFicherosClienteTO_STRING(String id_cliente) throws RemoteException; 
	public void registrarMetaDatoBorrado(String id_cliente, String nombreArchivoBorrado)  throws RemoteException; 
    public String ficherosPropios(String id_cliente) throws RemoteException; 
    public String getListaClienteOnline(String id_solicitante) throws RemoteException;
    public String compartirFicheroMetaDatos(String nombreArchivoCompartir,String id_propiertario, String nombreCliente) throws RemoteException; 
    public boolean existeRepoOnline() throws RemoteException;  
    public String listarClientesRepositorio(String id_repo) throws RemoteException; 
     /*Funcionalidades que necesita el gestor*/
	public boolean clientePoseeArchivo(String id_cliente, String nombreArchivo) throws RemoteException;
	String urlSrOperador(String id_cliente) throws RemoteException;
	public String idRepoCliente(String id_cliente) throws RemoteException;
	public boolean archivoCompartido(String id_cliente, String nombreArchivo) throws RemoteException; ;
	public String idRepoArchivoCompartido(String id_cliente, String nombreArchivo) throws RemoteException; ;
	public boolean repositorioOnline(String id_repo) throws RemoteException; ;
	public String dameURLSrOperadorporRepoID(String id_repo) throws RemoteException;
	public String damePropietarioSegunArchivo(String nombreArchivo)  throws RemoteException;
	public boolean existeArchivoDuplicado(String nombreArchivoSubido) throws RemoteException; 
}
