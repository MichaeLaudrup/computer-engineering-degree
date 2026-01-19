package compiler.syntax.nonTerminal;

public class Variables extends NonTerminal{
	public String tipo_referencia; 
	private String identificador;
	private String tipo_dato; 
	private int valor; //EN CASO DE SER REFERENCIA VECTOR O REFERENCIA FUNCION
	private int control_linea_error; //Variable para controlar en que linea se esta cometiendo un error
	private boolean esReferenciaConstante; 
	private String valorReferenciaConstante; 
	
	public Variables(String tipo_referencia) {
		this.tipo_referencia = tipo_referencia; 
		esReferenciaConstante = false;
		valorReferenciaConstante = "-1"; 
	}
	
	public String getTipo() {
		return this.tipo_dato; 
	}
	
	public void setTipo(String tipo_dato) {
		this.tipo_dato = tipo_dato;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador; 
	}
	public String getIdentificador() {
		return this.identificador; 
	}
	public void setValor(int valor) {
		this.valor = valor; 
		
	}
	public int getLineaError() {
		return this.control_linea_error; 
	}
	
	public String getTipoReferencia() {
		return this.tipo_referencia; 
	}
	public void setTipoReferencia(String nuevo_tipo) {
		this.tipo_referencia = nuevo_tipo; 
	}
	
	
	public void setLineaError(int linea) {
		this.control_linea_error = linea; 
	}
	
	public boolean esReferenciaConstante() {
		return this.esReferenciaConstante; 	
	}
	
	public void setEsReferenciaConstante(boolean esReferencia) {
		this.esReferenciaConstante = esReferencia; 
	}
	
	public void setValorReferenciaConstante(String valor_cte) {
		this.valorReferenciaConstante = valor_cte; 
	}
	
	public String getValorReferenciaConstante() {
		return valorReferenciaConstante; 
	}
	
	public boolean esConstanteNumerica() {
		return !(valorReferenciaConstante.equalsIgnoreCase("TRUE") || valorReferenciaConstante.equalsIgnoreCase("FALSE")); 
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
