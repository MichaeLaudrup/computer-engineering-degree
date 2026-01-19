
/**
 * Write a description of class Electrodomestico here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Electrodomestico
{
    private String categoria;
    private String identificador; 
    private String marca;
    private String modelo;
    private String color; 
    private double precio;
    private int stock; 
    /**
      * 
      */
    public Electrodomestico(String[] elementosBasicos){
        identificador = elementosBasicos[0]; 
        marca = elementosBasicos[1]; 
        modelo = elementosBasicos[2];
        color = elementosBasicos[3];    
        precio = Double.parseDouble(elementosBasicos[4]);
        stock = Integer.parseInt(elementosBasicos[5]); 
    }
    public Electrodomestico(String identificador, String marca, String modelo, String color, double precio, int stock){
        this.identificador = identificador; 
        this.marca = marca;
        this.modelo = modelo; 
        this.color = color; 
        this.precio = precio; 
        this.stock = stock;   
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;  
    }
    public String getCategoria(){
        return categoria;  
    }
    /**
     * Método que devuelve identificador del electrodoméstico
     * @return identifciador de electrodoméstico
     */
    public String getIdentificador()
    {
        return identificador; 
    }
    /**
     * Método que modifica identificador del electrodoméstico
     * @param identificador del electrodoméstico actualizado
     */
    public void setIdentificador(String identificador)
    {
        this.identificador = identificador; 
    }
    /**
     * Método que devuelve marca de electrodoméstico
     * @return marca de electrodoméstico
     */
    public String getMarca()
    {
        return marca; 
    }
    /**
     * Método que modifica marca de electrodomẃstico
     * @param marca de electrodoméstico actualizado
     */
    public void setMarca(String marca)
    {
        this.marca = marca; 
    }
       /**
     * Método que devuelve modelo de electrodomestico
     * @return modelo de electrodoméstico
     */
    public String getModelo()
    {
        return modelo; 
    }
    /**
     * Método que modifica modelo de electrodomestico
     * @param modelo de electrodoméstico actualizado
     */
    public void setModelo(String modelo)
    {
        this.modelo = modelo; 
    }
       /**
     * Método que devuelve color de electrodomestico
     * @return color de electrodoméstico
     */
    public String getColor()
    {
        return color; 
    }
    /**
     * Método que modifica color de electrodomestico
     * @param color de electrodoméstico actualizado
     */
    public void setColor(String color)
    {
        this.color= color; 
    }
        /**
     * Método que devuelve precio de electrodomestico
     * @return precio de electrodoméstico
     */
    public double getPrecio()
    {
        return precio; 
    }
    /**
     * Método que modifica precio de electrodomestico
     * @param precio de electrodoméstico actualizado
     */
    public void setPrecio(double precio)
    {
        this.precio = precio; 
    }
        /**
     * Método que devuelve stock de electrodomestico
     * @return stock de electrodoméstico
     */
    public int getStock()
    {
        return stock; 
    }
    /**
     * Método que modifica stock de electrodomestico
     * @param stock de electrodoméstico actualizado
     */
    public void setStock(int stock)
    {
        this.stock = stock; 
    }
    public String toString(){
        return " ID: " + identificador +"\n Categoría: " + categoria + "\n Marca: " + marca + "\n Modelo: " + modelo + "\n Color: " + color + "\n Precio: " + precio + "€" + "\n Cantidad en tienda: " + stock ;
    }
}
