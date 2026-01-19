/**
 * 
 */
package es.uned.common;

import java.util.Scanner;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class InterfazTextual {
       public static void imprimeMenuServidor() {
		    System.out.println("_________________________________________");
			System.out.println("|            MENU DEL SERVIDOR           |");
			System.out.println("|________________________________________|");
			System.out.println("| 1. Listar clientes.                    |");
			System.out.println("| 2. Listar repositorios.                |");
			System.out.println("| 3. Listar parejas repositorio cliente. |");
			System.out.println("| 4. Salir.                              |");
			System.out.println("|________________________________________|");
			System.out.print("| Introduzca opcion deseada: ");
       }
       public static void imprimeMenuCliente(String id_unico) {
    	    System.out.println("| Id Usuario actual: " + id_unico);
		    System.out.println("|________________________________________");
			System.out.println("|          BIENVENIDO A LA NUBE          |");
			System.out.println("|________________________________________|");
			System.out.println("| 1. Subir fichero.                      |");
			System.out.println("| 2. Bajar fichero.                      |");
			System.out.println("| 3. Borrar fichero.                     |");
			System.out.println("| 4. Compartir fichero.                  |");
			System.out.println("| 5. Listar ficheros.                    |");
			System.out.println("| 6. Listar clientes en linea.           |");
			System.out.println("| 7. Salir.                              |");
			System.out.println("|________________________________________|");
			System.out.print("| Introduzca opcion deseada: ");
      }
       public static void bienvenida(String tipo_usuario) {
    	   
		    System.out.println("_________________________________________");
			System.out.println("|         BIENVENIDO A LA NUBE           |");
			System.out.println("|________________________________________|");
			if(tipo_usuario.equalsIgnoreCase("cliente")){
				System.out.println("| 1. Registrarse.                        |");
				System.out.println("| 2. Log-in.                             |");
			}else {
				System.out.println("| 1. Registrar repositorio.              |");
				System.out.println("| 2. Log-in repositorio.                 |");
			}
			System.out.println("| 3. Salir                               |");
			System.out.println("|________________________________________|");
			System.out.print("| Introduzca opcion deseada: ");
      }
       public static int recogeNumero() {
    	   Scanner lector = new Scanner(System.in);
    	   int retorno = lector.nextInt(); 
    	   lector.nextLine();
    	   return retorno; 
       }
       
       public static String imprime_y_pide(String x ) {
    	   try {
	    	   System.out.print(x);
	    	   Scanner lectorLineas = new Scanner(System.in);
	    	   String retorno = lectorLineas.nextLine(); 
	    	   return retorno; 
    	   }catch(Exception e) {
    		   System.out.println(e.getMessage().toString()); 
    		   return "NUL"; 
    	   }
    	   
       }
	public static void imprimirMenuRepositorio(String id_repositorioActual) {
		System.out.println("_________________________________________");
		System.out.println("|            MENU DEL REPOSITORIO        |");
		 System.out.printf("| ID.Repositorio: %-10s             |\n", id_repositorioActual);                
		System.out.println("|________________________________________|");
		System.out.println("| 1. Listar clientes.                    |");
		System.out.println("| 2. Listar ficheros del cliente.        |");
		System.out.println("| 3. Salir.                              |");
		System.out.println("|________________________________________|");
		System.out.print("| Introduzca opcion deseada: ");
		
	}
}
