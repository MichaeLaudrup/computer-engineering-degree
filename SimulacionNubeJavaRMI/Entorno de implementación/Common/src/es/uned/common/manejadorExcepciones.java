/**
 * 
 */
package es.uned.common;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;

/**
 * @author : Michael Laudrup Luis Gonzalez
 * @email : mluis105@alumno.uned.es
 */
public class manejadorExcepciones{
      public static void tratarExcepcion(Exception e) {
    	  if(e instanceof NotBoundException ) {
    		  System.err.print("ERROR: Se ha intentando acceder a un servicio que no esta enlazado. Si se ha cerrado un servicio de manera ilegitima (pulsar 'x' en el terminal en lugar de salir) hay que reiniciar el programa");
    	  }else if(e instanceof MalformedURLException){
    		  System.err.print("ERROR: Se ha proporcionado una URL que no cumple con el estandar de URLs");
    	  }else if(e instanceof MalformedURLException){	  
    		  
    	  }else{
    		  e.printStackTrace();
    	  }
      }
      public static void tratarError(String contexto, String especificacion, String info_adicional) {
    	  switch(contexto) {
    	  case "AUTENTICACION": 
    		  if(especificacion.equalsIgnoreCase("NO_EXISTE")) {
    			  System.err.println("El "+ info_adicional + " introducido no existe en el sistema. Registrelo. ");
    		  }else if(especificacion.equalsIgnoreCase("PASSWORD_MALO")){
    			  System.err.println("El "+ info_adicional + " se ha encontrado en el sistema, pero el password proporcionado no coincide. ");
    		  }else if(especificacion.equalsIgnoreCase("DOBLE_CONEXION")){
    			  System.err.println("Un mismo "+ info_adicional + " no puede conectarse simultaneamente a traves de dos consolas. ");
    		  }else if(especificacion.equalsIgnoreCase("NO_REPO_ONLINE")) {
    			  System.err.println("No se encuentra ningun repositorio en linea para serle asignado por primera vez. "); 
    		  }else if(especificacion.equalsIgnoreCase("REPO_CLIENTE_OFFLINE")){
    			  System.err.println("El repositorio que suele utilizar este cliente ahora mismo no esta operativo. "); 
    		  }else {
    			  System.err.println("error desconocido en la autenticacion de "+ info_adicional); 
    		  }
    		  break; 
    	  case "REGISTRO": 
    		  if(especificacion.equalsIgnoreCase("NOMBRE_YA_EXISTE")) {
    			  System.err.println("El nombre de" + info_adicional + " que se pretende utilizar ya existe");
    		  }else if(especificacion.equalsIgnoreCase("NO_REPO_ONLINE")){
    			  System.err.println("No existe ningun repositorio en linea que pueda ser asociado al cliente. Intente autenticar algun repositorio antes de registrar a algun cliente.");
    		  }else {
    			  System.err.println("Error desconocido en el registro del usuario");
    		  }
    		  break; 
    		 
    	  case "COMPARTIR": 
    		  if(especificacion.equalsIgnoreCase("CLIENTE_COMPARTIR_NO_ENCONTRADO")) {
    			  System.err.println("No se ha encontrado el nombre del cliente especificado. Intentelo de nuevo.");
    		  }else if (especificacion.equalsIgnoreCase("ARCHIVO_NO_ENCONTRADO")){
    			  System.err.println("No se ha encontrado el archivo que desea compartir. Intentelo de nuevo. ");
    		  }else {
    			  System.err.println("No se ha encontrado el propietario del archivo a compartir . Intentelo de nuevo. ");
    		  }
    		  break; 
    	  case "BAJADA": 
    		  if(especificacion.equalsIgnoreCase("NOMBRE_NO_EXISTENTE")) {
    			  System.err.println("El archivo con nombre " + info_adicional + " no se ha encontrado. Esta seguro de que lo ha escrito bien?");
    		  }else if(especificacion.equalsIgnoreCase("REPO_COMPARTIDO_OFFLINE")) {
    			  System.err.println("El repositorio con id " +info_adicional +" que aloja el archivo compartido esta fuera de linea. Intentelo mas tarde. ");
    		  }
    		  break; 
    	  default: 	  
    	  }
    	  
      }
}
