import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;   
/**
 * Write a description of class GestorElectrodomesticos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GestorElectro extends Gestor<Electrodomestico>
{
    Scanner lectorEnteros = new Scanner(System.in);
    Scanner lector = new Scanner(System.in);  
    Scanner lectorDoubles = new Scanner(System.in); 
    GestorElectro(){
        super();
        datosPorDefecto(); 
    } 
    private void datosPorDefecto(){
        getMapa().put("TV1", new SmartTv("TV1", "Samsung", "S9L7", "Gris", 1090.99, 10, 45, "FHD", 123, 4, true)); 
        getMapa().put("TV2", new SmartTv("TV2", "LG", "S945", "Negro", 910.99, 20, 34, "HQ", 12, 2, false  ));
        getMapa().put("MONITOR1", new PantallaPC("MONITOR1", "Seix", "S7", "GRojo", 879.99, 20, 12, "FHD", 19, "HDMI", true)); 
        getMapa().put("MONITOR2", new PantallaPC("MONITOR2", "Packar Bell", "S45", "Violeta", 1090.99, 10, 45, "FHD", 13,"VGA", false )); 
        getMapa().put("ALTAVOZ1", new Altavoces("ALTAVOZ1", "Panasonic", "SKL345", "Negro", 123.55, 20, "Iones de litio", "JACK", true, 120.5  ));
        getMapa().put("ALTAVOZ2", new Altavoces("ALTAVOZ2", "Sony", "JR4875", "Gris", 350.76, 30, "Litio: Autonomía de 8 horas", "RCA", false, 345.5  ));
    }
    public void listarElectros(){
        for(Electrodomestico electro: getMapa().values()){
            System.out.println(electro); 
        }  
    }
    
    public void visualizarProducto(){
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte identificador de producto: ");
        String key = lector.nextLine(); 
        System.out.println(" ------------------------------------------");
        imprimeElectro(key); 
    }
    public void imprimeElectro(String identificador){
        if(getElemento(identificador) != null){
            System.out.println(getElemento(identificador)); 
        }else{
            System.out.println("Elemento no encontrado en el sistem, repita");   
        }
    }
      
    public void crearElectro(){ 
       System.out.println(" ------------------------------------------------");  
       System.out.println("|            Tipo de electrodoméstico            |");   
       System.out.println("--------------------------------------------------");  
       System.out.println("|  1. Televisión inteligente.                    |");
       System.out.println("|  2. Pantalla PC.                               |");
       System.out.println("|  3. Altavoces                                  |");
       System.out.println("|  4. Mp4                                        |");
       System.out.println("|  5. Nevera                                     |");
       System.out.println("|  6. Horno                                      |");
       System.out.println("|  7. PC Pórtatil                                |");
       System.out.println("|  8. Periféricos                                |");
       System.out.println("|  9. Teléfono                                   |");
       System.out.println("| 10. Atras                                      |");
       System.out.println(" -----------------------------------------------");
       System.out.print("Elija tipo de electrodoméstico: ");
       int selector = recogeEntero(); 
       switch(selector){
           case 1:
           crearSmartTV();
           break; 
           case 2:
           crearPantallaPC(); 
           break; 
           case 3:
           crearAltavoces(); 
           break;
           case 4:
           crearMP4(); 
           break; 
           case 5:
           crearNevera(); 
           break; 
           case 6:
           crearHorno();
           break; 
           case 7:
           crearPortatil(); 
           break; 
           case 8:
           crearPeriferico(); 
           break;
           case 9:
           crearTelefono(); 
           break; 
           case 10:
           break; 
           default:
           System.out.println("No se ha seleccionado ninguna de las opciones. Repita");
           crearElectro();
       }
    }
    public boolean adquiereProducto(String identificador, int cantidadDeseada){ 
        int stockTienda = getElemento(identificador).getStock();
        boolean resultado = false; 
        if(stockTienda >= cantidadDeseada && cantidadDeseada > 0){
            getElemento(identificador).setStock(stockTienda - cantidadDeseada); 
            System.out.println("===Producto adquirido con exito==="); 
            resultado = true; 
        }else{ 
            System.out.println("Se dispone de " + getElemento(identificador).getStock() + " artículos"+ " y se desea adquirir "+ cantidadDeseada);
            System.out.println("La cantidad que se desea adquirir de " + getElemento(identificador).getCategoria().toLowerCase() + " no es correcta."); 
            System.out.println(" ===================================================");
            resultado = false;        
        }
        return resultado; 
    }
       public void crearSmartTV(){
       String[] elementosBasicos = recogerDatosBasicos();
       String[] elementosImagen = recogerDatosImagen();
       System.out.println(" ================ Datos de Smart Tv ================");
       System.out.print("Inserte número de puertos: ");
       int puertosUsb = recogeEntero();        
       boolean camara = false;
       boolean datosCorrectos = false; 
       while(!datosCorrectos){
           System.out.print("¿Dispone de cámara está SmartTv(si/no)?");
           String respuesta =  lector.nextLine();
           if(respuesta.equalsIgnoreCase("si")){
               camara = true;
               datosCorrectos = true; 
               }else if(respuesta.equalsIgnoreCase("no")){
                   camara = false;
                   datosCorrectos = true; 
                   }else{
                       System.out.println("No le he entendido, vuelva a insertar:"); 
           }     
       }
       getMapa().put(elementosBasicos[0], new SmartTv(elementosBasicos, elementosImagen, puertosUsb, camara));
       System.out.println(" ==================================================="); 
    }
    public void crearPantallaPC(){
         String[] elementosBasicos = recogerDatosBasicos();
         String[] elementosImagen = recogerDatosImagen();      
         System.out.println(" =============Datos de Pantalla de PC===============");
         System.out.print("¿Qué tipo de conector tiene la pantalla del PC?: ");
         String tipoConector = lector.nextLine();
         boolean datosCorrectos = false;
         boolean tactil = false; 
         while(!datosCorrectos){
             System.out.print("¿Se trata de una pantalla táctil(Si/No)?: ");
             String respuesta =  lector.nextLine();
               if(respuesta.equalsIgnoreCase("si")){
                   tactil = true;
                   datosCorrectos = true;          
               }else if(respuesta.equalsIgnoreCase("no")){
                   tactil = false;
                   datosCorrectos = true;                    
               }else{
                   System.out.println("No le he entendido, vuelva a insertar: "); 
               }     
         }  
         System.out.println(" ==================================================="); 
         getMapa().put(elementosBasicos[0], new PantallaPC(elementosBasicos, elementosImagen, tipoConector, tactil));     
    }
    public void crearMP4(){
        String[] elementosBasicos = recogerDatosBasicos();
        String[] elementosSonido = recogerDatosSonido(); 
        System.out.println("----------Inserte datos de MP4-------------");
        System.out.print("Inserte Capacidad de almacenamiento(GB): ");
        double capacidad = recogeDouble();
        System.out.print("Inserte descripción breve del panel: ");
        String tipoPanel = lector.nextLine(); 
        System.out.println("-------------------------------------------");
        getMapa().put(elementosBasicos[0], new Mp4( elementosBasicos, elementosSonido, capacidad, tipoPanel));    
    }
      public void crearAltavoces(){
        System.out.println("---------Inserte datos Altavoces-----------");         
        String[] elementosBasicos = recogerDatosBasicos();
        String[] elementosSonido = recogerDatosSonido();
        System.out.print("Inserte frecuencia de los altavoces: ");
        double frecuencia = recogeDouble(); 
        boolean portable = false; 
        boolean datosCorrectos = false; 
        while(!datosCorrectos){
           System.out.print("¿Son portables los altavoces (si/no)? ");
           String respuesta =  lector.nextLine();
           if(respuesta.equalsIgnoreCase("si")){
               portable = true;
               datosCorrectos = true; 
               }else if(respuesta.equalsIgnoreCase("no")){
                   portable = false;
                   datosCorrectos = true; 
                   }else{
                       System.out.println("No le he entendido, vuelva a responder: "); 
           }     
        }
        System.out.println("-------------------------------------------");
        getMapa().put(elementosBasicos[0], new Altavoces(elementosBasicos, elementosSonido, portable, frecuencia)); 
        
    }
    public void crearNevera(){
        String[] elementosBasicos = recogerDatosBasicos();
        System.out.println("---------Inserte datos de nevera-----------");
        System.out.print("Inserte eficiencia del electrodoméstico: ");
        String eficiencia = lector.nextLine(); 
        System.out.print("Inserte temperatura mínima en la nevera: ");
        double t_min = recogeDouble();
        System.out.print("Inserte número de cajones en la nevera: ");
        int nCajones = recogeEntero();
        getMapa().put(elementosBasicos[0], new Nevera(elementosBasicos, eficiencia, t_min, nCajones));
        System.out.println("-------------------------------------------");
    }
    public void crearHorno(){
        String[] elementosBasicos = recogerDatosBasicos();
        System.out.println("---------Inserte datos de Horno------------");
        System.out.print("Inserte eficiencia del electrodoméstico: ");
        String eficiencia = lector.nextLine(); 
        System.out.print("Inserte temperatura máxima del horno: ");
        double t_max = Double.parseDouble(lector.nextLine());
        System.out.print("Inserte breve descripción del tipo de horno: ");
        String tipoHorno = lector.nextLine();
        getMapa().put(elementosBasicos[0], new Horno(elementosBasicos, eficiencia, tipoHorno, t_max));
        System.out.println("-------------------------------------------");
    }
    public void crearPortatil(){
        
        String[] elementosBasicos = recogerDatosBasicos();
        String[] elementosInformatica = recogerDatosInformatica();
        System.out.println("-------Inserte datos de PC pórtatil--------");
        System.out.print("Descripción breve del procesador: ");
        String procesador = lector.nextLine();
        System.out.print("Descripción breve de la tarjeta gráfica: ");
        String grafica = lector.nextLine();
        System.out.println("-------------------------------------------");
        getMapa().put(elementosBasicos[0], new Portatil(elementosBasicos, elementosInformatica, procesador, grafica ));
        
    }
     public void crearPeriferico(){  
        String[] elementosBasicos = recogerDatosBasicos();
        String[] elementosInformatica = recogerDatosInformatica();
        System.out.println("-------Inserte datos de Periférico---------");
        System.out.print("Descripción breve del tipo de periférico: ");
        String tipoPeriferico = lector.nextLine();
        System.out.print("Velocidad de transferencia de datos: ");
        double velocidad = recogeDouble();
        System.out.println("-------------------------------------------");
        getMapa().put(elementosBasicos[0], new Periferico(elementosBasicos, elementosInformatica, tipoPeriferico, velocidad ));
    } 
    public void crearTelefono(){
       String[] elementosBasicos = recogerDatosBasicos();  
       System.out.println("--------Inserte datos de Teléfono----------");
       System.out.print("Inserte tipo de sistema operativo en el teléfono: ");
       String sistemaOper = lector.nextLine(); 
       System.out.print("Inserte tipo de cargador del teléfono: ");
       String tipoCargador = lector.nextLine(); 
       System.out.println("-------------------------------------------");
       //El elemento basico cero corresponde con el identificador de electrodoméstico(key en el hashmap)
       getMapa().put(elementosBasicos[0], new Telefonia(elementosBasicos, sistemaOper, tipoCargador)); 
    }
    /**
     * Este método recoge todos los datos que tienen en común todos 
     * los electrodomésticos, los mete en un vector de Strings y 
     * devuelve este vector
     * @return VectorStrings(identificador, marca, modelo, color, precio, Stock)
     */
    public String[] recogerDatosBasicos(){
        String[] elementosBasicos = new String[6]; 
        System.out.println("---------------Datos básicos----------------");
        System.out.print("Inserte identificador de electrodoméstico: ");
        elementosBasicos[0] = lector.nextLine();
        System.out.print("Inserte marca del nuevo electrodoméstico: ");
        elementosBasicos[1] = lector.nextLine();
         System.out.print("Inserte modelo del nuevo electrodoméstico: ");
        elementosBasicos[2] = lector.nextLine();
        System.out.print("Inserte color del nuevo electrodoméstico: ");
        elementosBasicos[3] = lector.nextLine();
        System.out.print("Inserte precio del nuevo electrodoméstico: ");
        elementosBasicos[4] = String.valueOf(recogeDouble()); 
        System.out.print("Inserte número de articulos iniciales en tienda: ");
        elementosBasicos[5] = String.valueOf(recogeEntero());
        System.out.println("-------------------------------------------");
        return elementosBasicos; 
    }
    private Double recogeDouble(){
       double precio = 0.0; 
       try{
           precio = Double.parseDouble(lectorDoubles.nextLine());
       }catch(NumberFormatException e){
           System.out.println("Debe insertar un número racional. Formato incorrecto. repita...");
           System.out.print("Introduzca número racional (0.0): ");
           precio = recogeDouble(); 
       }
       return precio;   
    }
    
    private String[] recogerDatosImagen(){
        String[] elementosImagen = new String[3]; 
        System.out.println("---------------Datos Imagen----------------");
        System.out.print("Inserte pulgadas de pantalla(número entero): ");
        elementosImagen[0] = String.valueOf(recogeEntero());;
        System.out.print("Inserte resolución de pantalla: ");
        elementosImagen[1] = lector.nextLine();
        System.out.print("Inserte frecuencia de pantalla: ");
        elementosImagen[2] = lector.nextLine();
        System.out.println("-------------------------------------------");
        return elementosImagen; 
    }
    private String[] recogerDatosSonido(){
        String[] elementosSonido = new String[3];
        System.out.println("---------------Datos Sonido----------------");
        System.out.print("Inserte tipo de batería: ");
        elementosSonido[0] = lector.nextLine();
        System.out.print("Inserte tipo de conector del disposivo de Sonido: ");
        elementosSonido[1] = lector.nextLine();
        System.out.println("-------------------------------------------");
        return elementosSonido;  
    }   
    public String[] recogerDatosInformatica(){
        System.out.println("-------Insertar datos de informática-------"); 
        String[] elementosInformatica = new String[2]; 
        System.out.print("Inserte capacidad de almacenamiento del producto (GBytes):");
        elementosInformatica[0] = String.valueOf(recogeDouble()); 
        System.out.print("Inserte número de puertos: ");
        elementosInformatica[1] = lector.nextLine();
        System.out.println("-------------------------------------------");
        return elementosInformatica; 
    }

     public void modificaDatos() {
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte identificador de producto: ");
        String identificador = lector.nextLine();
        System.out.println(" ------------------------------------------");
        if(getElemento(identificador) != null){
            switch(getElemento(identificador).getCategoria()){
                case "SMARTTV":
                modificaSmartTV(identificador); 
                break; 
                case "PANTALLAPC":
                modificaPantallaPC(identificador); 
                break; 
                case "MP4":
                modificaMP4(identificador);
                break; 
                case "ALTAVOCES":
                modificaAltavoces(identificador);
                break; 
                case "NEVERA":
                modificaNevera(identificador);
                break; 
                case "HORNO": 
                modificaHorno(identificador);
                break; 
                case "PORTATIL": 
                modificaPortatil(identificador);
                break;
                case "PERIFERICO": 
                modificaPeriferico(identificador);
                break;
                case "TELEFONIA": 
                modificaTelefonia(identificador);
                break; 
                default:
            }
        }else{
            System.out.print("Elemento no encontrado en el sistema, repita"); 
            modificaDatos(); 
        }
    }

    /**
     * método que dada un identificador recibido modifica
     * una determinada Smart TV. 
     */
    private void modificaSmartTV(String key){
        System.out.println(" -------------Modificar SmarTV-------------");
        printModificacionBasica();
        printModificacionesImagen(); 
        System.out.println("| 9.Modificar número puertos USB           |");
        System.out.println("|10.Modificar disponibilidad de cámara     |");
        System.out.println("|11.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero(); 
        SmartTv mitv = (SmartTv)getElemento(key);
        boolean finalizado = false;   
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key);
            modificaSmartTV(key);
            break;
            case 6:
            case 7:
            case 8:
            modificaImagen(selector,key); 
            modificaSmartTV(key);
            break; 
            case 9: 
            System.out.print("Inserte nuevo número de puertos USB: ");
            mitv.setPuertosUsb(recogeEntero());
            modificaSmartTV(key);
            break; 
            case 10:
            System.out.print("¿tiene cámara(Si/No)?: ");
            mitv.setCamara(lector.nextLine().equalsIgnoreCase("si")); 
            modificaSmartTV(key);
            break; 
            case 11: 
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaSmartTV(key);
        }
    }

    /**
     * método que dado un identificador modifica una 
     * pantalla de PC
     * @param key del producto en el hashMap
     */
    public void modificaPantallaPC(String key){
        System.out.println(" ----------Modificar Pantalla PC-----------");
        printModificacionBasica();
        printModificacionesImagen(); 
        System.out.println("| 9.Modificar tipo conector                |");
        System.out.println("|10.Modificar pantalla táctil              |");
        System.out.println("|11.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero(); 
        PantallaPC monitor = (PantallaPC)getElemento(key);
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key);
            modificaPantallaPC(key);
            break;
            case 6:
            case 7:
            case 8:
            modificaImagen(selector,key);
            modificaPantallaPC(key);
            break; 
            case 9: 
            System.out.print("Inserte nuevo tipo de conector: ");
            monitor.setConector(lector.nextLine()); 
            modificaPantallaPC(key);
            break; 
            case 10:
            System.out.print("¿La pantalla es tactil(Si/No)?: ");
            monitor.setTactil(lector.nextLine().equalsIgnoreCase("si"));
            modificaPantallaPC(key);
            break; 
            case 11: 
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaPantallaPC(key);
        }
    }

    /**
     * método que dado un identificador modifica una 
     * dispositivo MP4
     * @param key del producto en el hashMap
     */
    public void modificaMP4(String key){
        System.out.println(" --------------Modificar MP4---------------");
        printModificacionBasica();
        printModificacionesSonido(); 
        System.out.println("| 8.Modificar capacidad de almacenamiento  |");
        System.out.println("| 9.Modificar tipo de panel                |");
        System.out.println("|10.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero(); 
        Mp4 mp = (Mp4)getElemento(key);
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key); 
            modificaMP4(key);
            break;
            case 6:
            case 7:
            modificaSonido(selector,key); 
            modificaMP4(key);
            break; 
            case 8: 
            System.out.print("Inserte nueva capacidad de almacenamiento: ");
            mp.setCapacidad(recogeDouble()); 
            modificaMP4(key);
            break; 
            case 9:
            System.out.print("Inserte nuevo tipo de panel: ");
            mp.setTipoPanel(lector.nextLine()); 
            modificaMP4(key);
            break;
            case 10: 
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaMP4(key);
        }
    }

    /**
     * método que dado un identificador modifica  
     * atributos de un altavoz
     * @param key del producto en el hashMap
     */
    public void modificaAltavoces(String key){
        System.out.println(" ------------Modificar Altavoz-------------");
        printModificacionBasica();
        printModificacionesSonido(); 
        System.out.println("| 8.Modificar portabilidad del altavoz     |");
        System.out.println("| 9.Modificar frecuencia                   |");
        System.out.println("|10.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero(); 
        Altavoces alt = (Altavoces)getElemento(key);
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key); 
            modificaAltavoces(key); 
            break;
            case 6:
            case 7:
            modificaSonido(selector,key);
            modificaAltavoces(key); 
            break; 
            case 8: 
            System.out.print("¿los altavoces son portables (si/no)?: ");
            boolean answer = lector.nextLine().equalsIgnoreCase("si");  
            alt.setPortable(answer);
            modificaAltavoces(key); 
            break; 
            case 9:
            System.out.print("Inserte nueva frecuencia: ");
            alt.setFrecuencia(recogeDouble());
            modificaAltavoces(key); 
            break;
            case 10:
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaAltavoces(key);
        }
    }

    /**
     * método que dado un identificador modifica  
     * atributos de un Portatil
     * @param key del producto en el hashMap
     */
    public void modificaPortatil(String key){
        System.out.println(" ------------Modificar portatil------------");
        printModificacionBasica();
        printModificacionesInform(); 
        System.out.println("| 8.Modificar procesador                   |");
        System.out.println("| 9.Modificar tarjeta gráfica              |");
        System.out.println("|10.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero(); 
        Portatil lapto = (Portatil)getElemento(key);
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key);
            modificaPortatil(key);
            break;
            case 6:
            case 7:
            modificaInformatica(selector,key); 
            modificaPortatil(key);
            break; 
            case 8: 
            System.out.print("Inserte nuevo procesador: ");
            lapto.setProcesador(lector.nextLine());
            modificaPortatil(key);
            break; 
            case 9:
            System.out.print("Inserte nueva tarjeta gráfica: ");
            lapto.setGrafica(lector.nextLine());
            modificaPortatil(key);
            break; 
            case 10:
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaPortatil(key);
        }
    }

    /**
     * método que dado un identificador modifica  
     * atributos de una nevera
     * @param key del producto en el hashMap
     */
    public void modificaNevera(String key){
        System.out.println(" ------------Modificar Nevera--------------");
        printModificacionBasica();
        System.out.println("| 6.Modificar eficiencia                   |");
        System.out.println("| 7.Modificar temperatura mínima           |");
        System.out.println("| 8.Modificar número de cajones            |");
        System.out.println("| 9.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero();  
        Nevera nev = (Nevera)getElemento(key);
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key); 
            modificaNevera(key); 
            break;
            case 6:
            System.out.print("Inserte nueva eficiencia: ");
            nev.setEficiencia(lector.nextLine());
            modificaNevera(key); 
            break; 
            case 7:
            System.out.print("Inserte nueva temperatura mínima: ");
            nev.setT_min(recogeDouble());
            modificaNevera(key); 
            break; 
            case 8: 
            System.out.print("Inserte nuevo número de cajones: ");
            nev.setnCajones(recogeEntero());
            modificaNevera(key); 
            break; 
            case 9:
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaNevera(key);
        }
    } 
     public void modificaHorno(String key){
        System.out.println(" -------------Modificar Horno--------------");
        printModificacionBasica();
        System.out.println("| 6.Modificar eficiencia                   |");
        System.out.println("| 7.Modificar temperatura máxima           |");
        System.out.println("| 8.Modificar tipo de horno                |");
        System.out.println("| 9.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero();  
        Horno hor = (Horno)getElemento(key);
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key); 
            modificaHorno(key);
            break;
            case 6:
            System.out.print("Inserte nueva eficiencia: ");
            hor.setEficiencia(lector.nextLine());
            modificaHorno(key);
            break; 
            case 7:
            System.out.print("Inserte nueva temperatura máxima: ");
            hor.setT_max(recogeDouble());
            modificaHorno(key);
            break; 
            case 8: 
            System.out.print("Inserte nuevo tipo de horno: ");
            hor.setTipoHorno(lector.nextLine());
            modificaHorno(key);
            break;
            case 9:
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaHorno(key);
        }
    }
    public void modificaTelefonia(String key){
        System.out.println(" ------------Modificar Telefono------------");
        printModificacionBasica();
        System.out.println("| 6.Modificar Sistema Operativo.           |");
        System.out.println("| 7.Modificar tipo de cargador.            |");
        System.out.println("| 8.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero();  
        Telefonia tel = (Telefonia)getElemento(key);
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key);
            modificaTelefonia(key);
            break;
            case 6:
            System.out.print("Inserte nuevo sistema operativo: ");
            tel.setSistemaOper(lector.nextLine());
            modificaTelefonia(key);
            break; 
            case 7:
            System.out.print("Inserte nueva tipo de cargador: ");
            tel.setTipoCargador(lector.nextLine());
            modificaTelefonia(key);
            break; 
            case 8:
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaTelefonia(key); 
        }
    
    }
    /**
     * método que dado un identificador modifica  
     * atributos de un periférico
     * @param key del producto en el hashMap
     */
    public void modificaPeriferico(String key){
        System.out.println(" ----------Modificar Periférico------------");
        printModificacionBasica();
        printModificacionesInform(); 
        System.out.println("| 8.Modificar tipo periferico              |");
        System.out.println("| 9.Modificar velocidad de transferencia   |");
        System.out.println("|10.Atras                                  |");
        System.out.println(" ------------------------------------------");
        System.out.print("Inserte opción deseada: ");
        int selector = recogeEntero(); 
        Periferico per = (Periferico)getElemento(key);
        switch(selector){
            case 1:         
            case 2:     
            case 3:
            case 4:
            case 5: 
            modificaBasico(selector, key); 
            modificaPeriferico(key); 
            break;
            case 6:
            case 7:
            modificaInformatica(selector,key); 
            modificaPeriferico(key); 
            break; 
            case 8: 
            System.out.print("Inserte nuevo tipo de periférico: ");
            per.setPeriferico(lector.nextLine());
            modificaPeriferico(key); 
            break; 
            case 9:
            System.out.print("Inserte nueva velocidad de transferencia de datos: ");
            per.setVelocidad(recogeDouble()); 
            modificaPeriferico(key); 
            break;
            case 10: 
            break; 
            default:
            System.out.println("Opción insertada incorrecta. Repita");
            modificaPeriferico(key); 
        }
    }

    /**
     * Este método imprime menú para seleccionar
     * que elemento básico modificar.
     */
    public void printModificacionBasica(){
        System.out.println("| 1.Modificar marca                        |");
        System.out.println("| 2.Modificar modelo                       |");
        System.out.println("| 3.Modificar color                        |");
        System.out.println("| 4.Modificar precio                       |");
        System.out.println("| 5.Modificar Stock en tienda              |");
    }

    /**
     * Este método según un número entero recibido modifica
     * un determinado parámetro básico de electrodoméstico 
     * @param selector de atributo básico a modificar
     * @param identificador del electro dentro del hashMap
     */
    public void modificaBasico(int selector, String key){
        switch(selector){
            case 1:
            System.out.print("Inserte nueva marca: ");
            getElemento(key).setMarca(lector.nextLine()); 
            break; 
            case 2: 
            System.out.print("Inserte nuevo modelo: ");
            getElemento(key).setModelo(lector.nextLine()); 
            break; 
            case 3: 
            System.out.print("Inserte nuevo color: ");
            getElemento(key).setColor(lector.nextLine()); 
            break;
            case 4:
            System.out.print("Inserte nuevo precio: ");
            double precio = recogeDouble(); 
            getElemento(key).setPrecio(precio); 
            break;
            case 5: 
            System.out.print("Inserte nueva cantidad de productos en tienda: ");
            int stock = recogeEntero(); 
            getElemento(key).setStock(stock); 
            break;
            default: 
        }

    }

    /**
     * Este método imprime un menú para
     * seleccionar que atributo de imagen
     * modificar.
     */
    public void printModificacionesImagen(){
        System.out.println("| 6.Modificar pulgadas                     |");
        System.out.println("| 7.Modificar resolución                   |");
        System.out.println("| 8.Modificar frecuencia                   |");
    }

    /**
     * Método que dado un entero selecciona un atributo 
     * de tipo Imagen y lo modifica en el producto que esta
     * en la posición que indica su key en el hashMap
     * @param selector de tipo de atributo a modificar
     * @param identificador de producto a modificar
     */
    public void modificaImagen(int selector, String key){
        Imagen miIma = (Imagen)getElemento(key); 
        switch(selector){
            case 6:
            System.out.print("Inserte nuevo valor de pulgadas(entero): ");
            miIma.setPulgadas(recogeEntero());
            break; 
            case 7:
            System.out.print("Inserte nueva resolución de pantalla: ");
            miIma.setResolucion(lector.nextLine());
            break; 
            case 8: 
            System.out.print("Inserte nueva frecuencia de pantalla: ");
            miIma.setFrecuencia(recogeEntero()); 
            break; 
            default:
        }
    }

    /**
     * Imprime atributos disponibles a modificar en caso de que
     * el electordomestico sea de tipo sonido
     */
    public void printModificacionesSonido(){
        System.out.println("| 6.Modificar tipo de batería              |");
        System.out.println("| 7.Modificar tipo de conector.            |");
    }

    /**
     * Método que dado un entero selecciona un atributo 
     * de tipo Sonido y lo modifica en el producto que esta
     * en la posición que indica su key.
     * @param selector de tipo de atributo a modificar
     * @param identificador de producto a modificar
     */
    public void modificaSonido(int selector, String key){
        Sonido miSo = (Sonido)getElemento(key); 
        switch (selector){
            case 6:
            System.out.print("Inserte nuevo tipo de batería: ");
            miSo.setTipoBateria(lector.nextLine());
            break; 
            case 7:
            System.out.print("Inserte nuevo tipo de conector: ");
            miSo.setTipoConector(lector.nextLine());
            break;
            default: 
        }

    }

    /**
     * Este método imprime un menú para
     * seleccionar que atributo de imagen
     * modificar.
     */
    public void printModificacionesInform(){
        System.out.println("| 6.Modificar capacidad de almacenamiento  |");
        System.out.println("| 7.Modificar número de entradas           |");
    }

    /**
     * Método que dado un entero selecciona un atributo 
     * de tipo Informática y lo modifica en el producto que esta
     * en la posición que indica su key.
     * @param selector de tipo de atributo a modificar
     * @param identificador de producto a modificar
     */
    public void modificaInformatica(int selector, String key){
        Informatica infor = (Informatica)getElemento(key); 
        switch (selector){
            case 6:
            System.out.print("Inserte nueva capacidad de almacenamiento: ");
            infor.setAlmacenamiento(recogeEntero());
            break; 
            case 7:
            System.out.print("Inserte nueva cantidad de entradas : ");
            infor.setEntradas(recogeEntero());
            break;
            default: 
        }
    }
    /**
     * Método que lee un número entero y comprueba que 
     * se ha introducido un dato correcto. 
     */
    private int recogeEntero(){
        int resultado; 
        if(lectorEnteros.hasNextInt()){
           resultado =  lectorEnteros.nextInt();   
        }else{
           lectorEnteros.next(); 
           System.out.print("Formato introducico incorrecto. Introduzca de nuevo: ");
           resultado = recogeEntero(); 
        }
        return resultado; 
    }
}

