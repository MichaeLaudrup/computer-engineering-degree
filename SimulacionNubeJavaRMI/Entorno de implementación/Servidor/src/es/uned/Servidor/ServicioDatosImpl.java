/**
 * 
 */
package es.uned.Servidor;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap; 
import es.uned.common.ServicioDatosInterface;
import es.uned.common.ServicioSrOperadorInterface;
import es.uned.common.manejadorExcepciones;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
//Cuando servicio Datos implementa la interfaz ServicioDatosIntergaces está implementando un objeto Remoto
public class ServicioDatosImpl extends UnicastRemoteObject implements ServicioDatosInterface {
    
	//Mapa con numero identificativo univoco como clave y algunos datos de cliente en forma lista de cadenas de texto
	HashMap<String, ArrayList<String>> clientes; 
	//Mapa con numero identificativo univoco como clave y nombre de repositorio como valor, tiene informacion basica
	HashMap<String, ArrayList<String>> repositorios; 
	//Listado de asociacion entre cliente (llave) y repositorio asociado, ademas, tiene la ruta absoluta de donde esta la carpeta del cliente
	HashMap<String,ArrayList<String>> asociacionClientesRepositorios;  
	
	ArrayList<MetaDato> metaDatos; //Estructura para control de comparticion de ficheros
	
	
	private int numClientes; //NUMERO DE CLIENTES Y REPOS EN EL SISTEMA
	private int numRepos; 
	
	public ServicioDatosImpl() throws RemoteException{
		super();
		clientes = new HashMap<>(); 
		repositorios = new HashMap<>(); 
		asociacionClientesRepositorios = new HashMap<>(); 
		metaDatos = new ArrayList<>(); 
		numClientes = 0; 
		numRepos = 0;  
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617954860160958222L;
	@Override
	public void listarClientes() throws RemoteException {
		System.out.println(" _______________________________________________________________________________________________________________________________________________"); 
		System.out.println("|                                                     LISTADO DE CLIENTES EN LA NUBE                                                            |");
		System.out.println("|_______________________________________________________________________________________________________________________________________________| ");
		System.out.println("| ID.Cliente     |Nombre              |Password            |Estado          |ID.Repositorio  |URL_DISCO_CLIENTE                                 |");
	    for(ArrayList<String> cliente: clientes.values()) {
	    	System.out.printf("| %-15s|%-20s|%-20s|%-16s|%-16s|%-50s|\n",cliente.get(0),cliente.get(1),cliente.get(2),cliente.get(3), cliente.get(4), cliente.get(5)); 
	    }
	    System.out.println(" _______________________________________________________________________________________________________________________________________________| ");
	}

	public void listarRepositorios() throws RemoteException {
		System.out.println(" _____________________________________________________________________________________________________________________________________________________________________________________________"); 
		System.out.println("|                                                                 LISTADO DE REPOSITORIOS EN LA NUBE                                                                                          |");
		System.out.println(" _____________________________________________________________________________________________________________________________________________________________________________________________|");
		System.out.println("| ID.Repositorio    |Nombre repositorio    |Password            |Estado    |N.Clientes  |URL_SERVIDOR_CLIENTE                              |URL_SERVIDOR_OPERADOR                             |");
		                                                                                             
		for(ArrayList<String> repo: repositorios.values()) {
	    	System.out.printf( "| %-18s|%-22s|%-20s|%-10s|%-12s|%-50s|%-50s|\n", repo.get(0), repo.get(1), repo.get(2), repo.get(3), repo.get(6),repo.get(4), repo.get(5)) ;
	    }
	    System.out.println("|_____________________________________________________________________________________________________________________________________________________________________________________________|");
	}
	
	

	@Override
	public void listarParejaRepositorio_cliente() throws RemoteException {
		System.out.println(" ________________________________________________________________________________________________________________________"); 
		System.out.println("|                                             RELACION CLIENTE REPOSITORIO                                               |");
		System.out.println("|________________________________________________________________________________________________________________________|");
		System.out.println("| ID.Repositorio    | ID.Cliente    | NumArchivos | Ruta carpeta cliente en repo                                         |");
		for(ArrayList<String> tuplaRelacionClienteRepo : asociacionClientesRepositorios.values() ) {
			System.out.printf("| %-18s| %-14s| %-12s| %-69s\n", tuplaRelacionClienteRepo.get(1), tuplaRelacionClienteRepo.get(0),tuplaRelacionClienteRepo.get(3),  tuplaRelacionClienteRepo.get(2));  
		}
		System.out.println(" _________________________________________________________________________________________________________________________");
		
	}


	public String existeClienteConPassword(String nombre, String password) throws RemoteException{
		String id_cliente = existeClienteConNombre(nombre); 
		if(id_cliente.equalsIgnoreCase("NO_EXISTE")){
			return "NO_EXISTE"; 
		}else {
			//EN ESTE PUNTO EL CLIENTE EXISTE
			ArrayList<String> cliente_actual = clientes.get(id_cliente); 
			if(cliente_actual.get(2).equalsIgnoreCase(password)) {
				//EN ESTE PUNTO EL PASSWORD COINCIDE CON EL DEL CLIENTE
				if(cliente_actual.get(3).equalsIgnoreCase("OFF-LINE")) {
					//EL CLIENTE QUE SE INTENTA CONECTAR NO ESTA CONECTADO SIMULTANEAMENTE EN OTRA CONSOLA
					if(cliente_actual.get(4).equalsIgnoreCase("NO_REPO_ASIGNADO")){
						//EL CLIENTE SE AUTENTICA POR PRIMERA VEZ Y HAY QUE BUSCARLE UN REPOSITORIO
						String resultado_busquedaRepo = buscarRepoOnlineConMenosClientes(); 
						if(resultado_busquedaRepo.equalsIgnoreCase("NO_REPO_ONLINE")) {
							//NO EXISTE NINGUN REPOSITORIO QUE SE PUEDA ASIGNAR
							return "NO_REPO_ONLINE"; 
						}else {
							//SE HA ENCONTRADO UN REPOSITORIO ONLINE
							try {
								cliente_actual.set(4, resultado_busquedaRepo); //se asigna repositorio online
								ServicioSrOperadorInterface srOperador = (ServicioSrOperadorInterface)Naming.lookup(repositorios.get(resultado_busquedaRepo).get(5)); 
								String ruta_absoluta_carpeta_cliente = srOperador.crearCarpetaNuevoCliente(cliente_actual.get(0)); 
								ArrayList<String> tuplaRelacionClienteRepositorio = new ArrayList<String>(); 
								tuplaRelacionClienteRepositorio.add(0, cliente_actual.get(0)); //Primer elemento es el id del cliente
								tuplaRelacionClienteRepositorio.add(1,resultado_busquedaRepo); //Segundo elemento es el id del repositorio asociado
								tuplaRelacionClienteRepositorio.add(2, ruta_absoluta_carpeta_cliente); //tercer elemento es la ruta absoluta de la carpeta del cliente en el repo
								tuplaRelacionClienteRepositorio.add(3, "0"); 
								//En esta parte se actualiza en la tabla de repositorios cuantos clientes tiene asociado incrementandolo en una unidad
								int numClientesRepoPrevios = Integer.parseInt(repositorios.get(resultado_busquedaRepo).get(6)); 
								repositorios.get(resultado_busquedaRepo).set(6, String.valueOf(numClientesRepoPrevios+1)); 
								asociacionClientesRepositorios.put(tuplaRelacionClienteRepositorio.get(0), tuplaRelacionClienteRepositorio); 
								cliente_actual.set(3, "ON-LINE"); // se pone en linea al cliente
								return cliente_actual.get(0); //se devuelve id unico de cliente
							}catch(Exception e) {
								manejadorExcepciones.tratarExcepcion(e);
								return "FALLO_SERVICIO_REPO"; 
							} 
						}
					}else {
						//EL CLIENTE TENIA YA UN REPOSITORIO PREVIAMENTE ASIGNADO
						String repo_cliente = cliente_actual.get(4); 
						if(repositorios.containsKey(repo_cliente) && estaElRepositorioOnline(repo_cliente)) {
							//EL REPOSITORIO HABITUAL DEL CLIENTE ESTA ONLINE
							cliente_actual.set(3, "ON-LINE"); // se pone en linea al cliente
							return cliente_actual.get(0); //se devuelve id unico de cliente 
						}else {
							return "REPO_CLIENTE_OFFLINE"; 
						}
					}
				}else {
					//EL CLIENTE QUE SE INTENTA CONECTAR YA ESTA PREVIAMENTE CONECTADO EN OTRA CONSOLA
					return "DOBLE_CONEXION"; 
				}
			}else {
				//EL PASSWORD INTRODUCIDO ES INCORRECTO
				return "PASSWORD_MALO"; 
			}
		}
	}

	/*
	 * Este metodo introduce un nuevo cliente al sistema recibiendo como parametros su nombre y contrasenia y comprobando previamente 
	 * que el nombre del cliente no ha sido introducido previamente
	 * @param nombre cliente
	 * @param passwrod cliente
	 * @returns resultado del registro "Registro_exitosos" o "NOmbre_ya exites". 
	 */
	public String insertarCliente(String nombre_cliente, String password) throws RemoteException {
		if(existeClienteConNombre(nombre_cliente).equalsIgnoreCase("NO_EXISTE")) {
			String id_unico = generarIDUnico("cliente"); 
			ArrayList<String> nuevoCliente = new ArrayList<>(); 
			nuevoCliente.add(0, id_unico);
			nuevoCliente.add(1, nombre_cliente); 
			nuevoCliente.add(2, password); 
			nuevoCliente.add(3, "OFF-LINE"); 
			nuevoCliente.add(4, "NO_REPO_ASIGNADO"); 
			nuevoCliente.add(5, "URL_DISCO_NO_ASIGNADA");
			numClientes++; 
			clientes.put(id_unico, nuevoCliente); 
			return "REGISTRO_EXITOSO"; 
		}else {
			return "NOMBRE_YA_EXISTE"; 
		}
	}
	/*
	 * Dado un nombre de cliente, en caso de existir se devuelve su identificador unico en caso de no existir se devuelve el string "NO_EXISTE"
	 * @param nombre cliente buscado
	 * @return resultado de busqueda (id cliente busqueda exitosas, "NO_existe" busqueda no existosa). 
	 */
	private String existeClienteConNombre(String nombre) {
		for(ArrayList<String> cliente : clientes.values()) {
			if(cliente.get(1).equalsIgnoreCase(nombre)) {
				return cliente.get(0); 
			}
		}
		return "NO_EXISTE"; 
	}
	/*
	 * Este metodo comprueba que un repositorio existe y que concuerda con su password
	 */
	public String existeRepoConPassword(String nombre_repo, String password) throws RemoteException {
		String id_repoActual = existeRepositorio(nombre_repo); 
		if(id_repoActual.equalsIgnoreCase("NO_EXISTE_REPO")) {
			//EL REPOSITORIO BUSCADO NO EXISTE
			return "NO_EXISTE"; 
		}else {
			ArrayList<String> repoActual = repositorios.get(id_repoActual); 
			if(repoActual.get(2).equalsIgnoreCase(password)) {
				if(repoActual.get(3).equalsIgnoreCase("ON-LINE")) {
					//EL REPOSITORIO ESTA USANDO OTRA CONSOLA 
					return "DOBLE_CONEXION"; 
				}else {
					repoActual.set(3, "ON-LINE"); 
					return repoActual.get(0); 
				}
			}else {
				//El password del repositorio no es correcto
				return "PASSWORD_MALO"; 
			}
			
		}
	}
	@Override
	public String nuevoRepositorio(String nombreRepositorio, String password) throws RemoteException {
		if(existeRepositorio(nombreRepositorio).equalsIgnoreCase("NO_EXISTE_REPO")) {
			ArrayList<String> nuevoRepo = new ArrayList<>(); 
			String id_unico = generarIDUnico("repositorio");			
			nuevoRepo.add(0, id_unico); 
			nuevoRepo.add(1, nombreRepositorio); 
			nuevoRepo.add(2, password); 
			nuevoRepo.add(3, "OFF-LINE"); 
			nuevoRepo.add(4, "Servicio_cliente_no_asignado"); 
			nuevoRepo.add(5, "servicio_servidor_no_asignado"); 
			nuevoRepo.add(6,"0"); 
			repositorios.put(id_unico, nuevoRepo); 
			numRepos++; 
			return "REGISTRO_EXITOSO"; 
		}else {
			return "NOMBRE_YA_EXISTENTE"; 
		}
	}

	/*
	 * Este metodo pone en modo offline a un usuario
	 */
	public void usuarioFueraLinea(String id_usuarioActual, String tipo_usuario) throws RemoteException {
		if(tipo_usuario.equalsIgnoreCase("cliente")) {
			clientes.get(id_usuarioActual).set(3, "OFF-LINE");
		}else{
			repositorios.get(id_usuarioActual).set(3, "OFF-LINE"); 
		}
	}
	private boolean estaElRepositorioOnline(String id_repo) {
		return (repositorios.get(id_repo).get(3).equalsIgnoreCase("ON-LINE")); 
	}
	/*
	 * Este metodo devuelve el id del repositorio online con menos clientes, en caso de que todos los repos tengan el mismo numero de clientes
	 * devuelve el primer repositorio que se encuentra
	 */
	private String buscarRepoOnlineConMenosClientes() {
		String idRepoConMenosClientes = "NO_REPO_ONLINE"; 
		for (ArrayList<String> repo: repositorios.values()) {
			if(repo.get(3).equalsIgnoreCase("ON-LINE")) {
				if(idRepoConMenosClientes == "NO_REPO_ONLINE") {
					idRepoConMenosClientes = repo.get(0); 
				}else {
					int numClientesRepoActual = Integer.parseInt(repo.get(6)); 
					int numClientesUltimoRepoAsignado = Integer.parseInt(repositorios.get(idRepoConMenosClientes).get(6)); 
					if(numClientesRepoActual < numClientesUltimoRepoAsignado) {
						idRepoConMenosClientes = repo.get(0); 
					}
				}
			}
		}
		return idRepoConMenosClientes; 
	}
	/*
	 * Metodo genera un identificador unico para un repositorio o un cliente teniendo el numero de estos existentes en el sistema
	 * @param tipo de usuario ("cliente" o "repositorio"), esto determinara como se generara el id. 
	 */
	private String generarIDUnico(String tipo_usuario) {
		if(tipo_usuario.equalsIgnoreCase("cliente")) {
			return "CL_"+ (numClientes+1);
		}else {
			return "RP_" + (numRepos+1); 
		}
	}
	/*
	 * Metodo que comprueba la existencia de un repositorio, en caso de existir devuelve su identificador unico
	 * @param nombre repositorio buscado
	 * @returns ID de repositorio o "NO_EXISTE", en caso de que no exista el repositorio. 
	 */
	private String existeRepositorio(String nombre) {
		for(ArrayList<String> repo : repositorios.values()) {
			if(repo.get(1).equalsIgnoreCase(nombre)) {
				return repo.get(0); 
			}
		}
		return "NO_EXISTE_REPO"; 	
	}

	/*
	 * Este metodo actualiza la informacion relacionada con un repositorio en cuanto a sus direcciones en java rmi
	 * @param identificador unico de repositorio
	 * @param url del servicio Servidor-Operador
	 * @param url del servicio Cliente-operador
	 * @return true si la operacion se realizo con exito, false en caso contrario
	 */
	public boolean actualizarURLs(String id_repo, String url_servidorOperador, String url_clienteOperador) throws RemoteException {
		if(repositorios.containsKey(id_repo)) {
			repositorios.get(id_repo).set(4, url_clienteOperador); 
			repositorios.get(id_repo).set(5, url_servidorOperador); 
			return true;
		}else {
			return false; 
		}
	}
	public boolean actualizarURLcliente(String id_unico_cliente, String url_disco_cliente) throws RemoteException{
		if(clientes.containsKey(id_unico_cliente)) {
			clientes.get(id_unico_cliente).set(5, url_disco_cliente); 
			return true; 
		}else {
			return false; 
		}
	}
	
	public String dameURLservicioClOperador(String id_cliente) throws RemoteException{
		String id_repositorio_asociado = asociacionClientesRepositorios.get(id_cliente).get(1);
	    String url_repositorio = repositorios.get(id_repositorio_asociado).get(4); // Se devuelve la URL del servicio cliente-operador del repositorio
	    return url_repositorio; 
	}

	@Override
	public void registrarMetaDatoSubida(String id_cliente, String nombreArchivoSubido) throws RemoteException {
		int numeroArchivos = Integer.parseInt(asociacionClientesRepositorios.get(id_cliente).get(3)); 
		asociacionClientesRepositorios.get(id_cliente).set(3, String.valueOf(numeroArchivos+1)); //Se incrementa en una unidad el numero de archivos subidos 
	    metaDatos.add(new MetaDato(id_cliente,nombreArchivoSubido)); 
	}
	
	public String listaFicherosClienteTO_STRING(String id_cliente) throws RemoteException{
		String impresionListado = "========/ LISTADO FICHEROS DE "+ id_cliente + " EN PROPIEDAD Y COMPARTIDOS \\================\n"; 
		for(int i = 0; i < metaDatos.size(); i++) {
			if(metaDatos.get(i).getPropietario().equalsIgnoreCase(id_cliente) || metaDatos.get(i).tienePermiso(id_cliente)){
				impresionListado += "- Nombre archivo: "+  metaDatos.get(i).getNombreArchivo() + "      id.propietario: " + metaDatos.get(i).getPropietario(); 
				if(metaDatos.get(i).getPropietario().equalsIgnoreCase(id_cliente)) {
					impresionListado += " EN PROPIEDAD \n"; 
				}else {
					impresionListado += " TE LO COMPARTEN \n"; 
				}
			}
		}
		impresionListado += "======================================================================================"; 
		return impresionListado; 
	}

	@Override
	public void registrarMetaDatoBorrado(String id_cliente, String nombreArchivoBorrado) throws RemoteException {
		int indice_metadato = -1; 
		for(int i = 0; i < metaDatos.size(); i++) {
			if(metaDatos.get(i).getPropietario().equalsIgnoreCase(id_cliente) && metaDatos.get(i).getNombreArchivo().equalsIgnoreCase(nombreArchivoBorrado)) {
				indice_metadato = i; 
			}
		}
		metaDatos.remove(indice_metadato);
		int numFicherosPrevios = Integer.parseInt(asociacionClientesRepositorios.get(id_cliente).get(3)); 
		asociacionClientesRepositorios.get(id_cliente).set(3, String.valueOf(numFicherosPrevios-1)); 
	}

	@Override
	public String ficherosPropios(String id_cliente) throws RemoteException {
		String impresionListado = "========/ LISTADO FICHEROS DE "+ id_cliente + " EN PROPIEDAD \\================\n"; 
		for(int i = 0; i < metaDatos.size(); i++) {
			if(metaDatos.get(i).getPropietario().equalsIgnoreCase(id_cliente)){
				impresionListado += "- Nombre archivo: "+  metaDatos.get(i).getNombreArchivo() +"\n"; 
			}
		}
		impresionListado += "======================================================================================"; 
		return impresionListado; 
	}

	@Override
	public String getListaClienteOnline(String id_solicitante) throws RemoteException {
		String listado = "===== LISTA CLIENTES =====\n"; 
		for(ArrayList<String> cliente : clientes.values()) {
			if(cliente.get(3).equalsIgnoreCase("ON-LINE") && !cliente.get(0).equalsIgnoreCase(id_solicitante)) {
				listado += "Nombre: " + cliente.get(1) +  "     ID.CLIENTE: "+ cliente.get(0)+ "\n"; 
			}
		}
		listado += "======================\n"; 
		return listado; 
	}
	private String localizaIDClienteConNombre(String nombreCliente) throws RemoteException {
		for(ArrayList<String> cliente : clientes.values()) {
			if(cliente.get(1).equalsIgnoreCase(nombreCliente)){
				return cliente.get(0); 
			}
		}
		return "NO_ENCONTRADO"; 
	}
	@Override
	public String compartirFicheroMetaDatos(String nombreArchivoCompartir, String id_propietario ,String nombreCliente) throws RemoteException {
		boolean archivoEncontrado = false; 
		for(int i = 0; i < metaDatos.size(); i++) {
			if((archivoEncontrado = metaDatos.get(i).getNombreArchivo().equalsIgnoreCase(nombreArchivoCompartir)) && metaDatos.get(i).getPropietario().equalsIgnoreCase(id_propietario)) {
				String id_compartir = localizaIDClienteConNombre(nombreCliente); 
				if(id_compartir.startsWith("CL_")) {
					metaDatos.get(i).compartir(id_compartir); 
					return id_compartir; 
				}else {
					return "CLIENTE_COMPARTIR_NO_ENCONTRADO"; 
				}
			}
		}
		if(!archivoEncontrado) {
			return "ARCHIVO_NO_ENCONTRADO"; 
		}else {
			return "PROPIETARIO_NO_ENCONTRADO"; 
		}
		
	}

	@Override
	public boolean existeRepoOnline() throws RemoteException {
		for(ArrayList<String> repo : repositorios.values()) {
			if(repo.get(3).equalsIgnoreCase("ON-LINE")) {
				return true; 
			}
		}
		return false; 
	}

	@Override
	public String listarClientesRepositorio(String id_repo) throws RemoteException {
		String resultado = "========= LISTADO CLIENTES DE REPOSITORIO "+ id_repo +" =========\n"; 
		for(ArrayList<String> asociacion: asociacionClientesRepositorios.values()) {
			if(asociacion.get(1).equalsIgnoreCase(id_repo)) {
				resultado += "ID.Cliente: " + asociacion.get(0) + "    Nombre cliente: " + clientes.get(asociacion.get(0)).get(1) + "    Num ficheros: "+ asociacion.get(3)+ "\n"; 
			}
		}
		return (resultado += "=============================================================\n") ; 
	}

	@Override
	public String urlSrOperador(String id_cliente) throws RemoteException {
		String repo = asociacionClientesRepositorios.get(id_cliente).get(1);
		return repositorios.get(repo).get(5);
	}

	@Override
	public boolean clientePoseeArchivo(String id_cliente, String nombreArchivo) {
		for(int i = 0; i < metaDatos.size(); i++) {
			if(metaDatos.get(i).getPropietario().equalsIgnoreCase(id_cliente) && metaDatos.get(i).getNombreArchivo().equalsIgnoreCase(nombreArchivo)) {
				return true; 
			}
		}
		return false; 
	}

	@Override
	public String idRepoCliente(String id_cliente) throws RemoteException {
		return asociacionClientesRepositorios.get(id_cliente).get(1); 
	}

	@Override
	public boolean archivoCompartido(String id_cliente, String nombreArchivo) {
		for(int i = 0; i < metaDatos.size(); i++) {
			if(metaDatos.get(i).getNombreArchivo().equalsIgnoreCase(nombreArchivo) && metaDatos.get(i).tienePermiso(id_cliente)) {
				return true; 
			}
		}
		return false; 
	}

	@Override
	public String idRepoArchivoCompartido(String id_cliente, String nombreArchivo) {
		for(int i = 0; i < metaDatos.size(); i++) {
			if(metaDatos.get(i).getNombreArchivo().equalsIgnoreCase(nombreArchivo) && metaDatos.get(i).tienePermiso(id_cliente)) {
				String repositorioAsociado = asociacionClientesRepositorios.get(metaDatos.get(i).getPropietario()).get(1); 
				return repositorioAsociado; 
			}
		}
		return "NO_ENCONTRADO"; 
		
	}

	@Override
	public boolean repositorioOnline(String id_repo) {
		return repositorios.get(id_repo).get(3).equalsIgnoreCase("ON-LINE"); 
	}

	@Override
	public String dameURLSrOperadorporRepoID(String id_repo) {
		return repositorios.get(id_repo).get(5); 
	}

	@Override
	public String damePropietarioSegunArchivo(String nombreArchivo) throws RemoteException {
		for(int i = 0; i < metaDatos.size(); i++) {
			if(metaDatos.get(i).getNombreArchivo().equalsIgnoreCase(nombreArchivo)) {
				return metaDatos.get(i).getPropietario(); 
			}
		}
		return "ARCHIVO NO ENCONTRADO"; 
	}

	@Override
	public boolean existeArchivoDuplicado(String nombreArchivoSubido) throws RemoteException {
		boolean resultado = false; 
		for(int i = 0; i < metaDatos.size(); i++) {
			if(metaDatos.get(i).getNombreArchivo().equalsIgnoreCase(nombreArchivoSubido)) {
				System.out.println("Se ha encontrado en metadatos un nombre de archivo subido el cual es: "+ nombreArchivoSubido);
				resultado = true; 
			}
		}
		return resultado; 
	}
	
	
	
}
