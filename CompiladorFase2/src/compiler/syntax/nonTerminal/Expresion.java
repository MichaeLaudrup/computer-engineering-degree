package compiler.syntax.nonTerminal;

public class Expresion extends NonTerminal{
	//Esta variable es verdadera cuando la expresion es de tipo logica y falsa cuando la expresion es aritmetica
	private boolean logico;  
	private int result_op_aritmetica; 
	private boolean result_op_logica; 
	private String tipo_expresion; 
	private boolean esConstante; 
	private String valorExpresionConstante; //Este campo de clase sirve para controlar referencias a posiciones de vectores con constantes
	private String valorNumericoSimple; 
	boolean esExpresionQueReferenciaAunVector;
	
	public Expresion() {
		tipo_expresion = "POR_DETERMINAR"; 
		valorExpresionConstante = "-1"; 
		//POR DEFECTO LA EXPRESION NO ES CONSTANTE; EN CASO DE QUE LO SEA SE ESPECIFICARA DE MANERA EXPLICITA MEDIANTE EL METODO SET DE ESTE ATRIBUTO
		esConstante = false; 
		esExpresionQueReferenciaAunVector =false; 
	}

	/**
	 * Metodo que devuelve si una expresion es logica o no lo es
	 * @return true si es expresion logica, false si expresion aritmetica;
	 */
	public boolean esLogica() {
		return logico; 
	}
	public void setValorNumericoSimple(String valorNumericoSimple) {
		this.valorNumericoSimple = valorNumericoSimple; 
	}
	
	public String getValorNumericoSimple() {
		return this.valorNumericoSimple; 
	}
	
	public boolean esUnNumeroSimple() {
		return (valorNumericoSimple != null); 
	}
	
	
	
	public int obtenerValor() {
		return this.result_op_aritmetica; 
	}
	public void setEsLogica(boolean logico) {
		if(logico == true) {
			this.tipo_expresion = "LOGICO"; 
		}
		this.logico = logico; 
	}
	
	public void setTipoExpresion(String nuevo_tipo) {
		this.tipo_expresion = nuevo_tipo;	
	}
	public String getTipoExpresion() {
		return tipo_expresion;
	}
	public void setEsExpresionConstante(boolean esConstante) {
		this.esConstante = esConstante; 
	}
	
	public boolean esExpresionConstante() {
		return this.esConstante; 
	}
	
	public void setValorExpresionConstante(String valorExpresionConstante) {
		this.valorExpresionConstante = valorExpresionConstante; 
	}
	
	public String getValorExpresionConstante() {
		return valorExpresionConstante; 
	}
	
	public void marcarComoReferenciaAVector() {
		esExpresionQueReferenciaAunVector = true; 
	}
	
	public boolean esExpresionQueReferenciaDireccionVector() {
		return esExpresionQueReferenciaAunVector; 
	}
	
		
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



}
