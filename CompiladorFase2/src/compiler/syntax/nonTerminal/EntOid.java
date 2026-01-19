package compiler.syntax.nonTerminal;

public class EntOid extends NonTerminal{
	
	private boolean es_identificador; 
	private String valor;  
	
	public EntOid() {
	}
	
	public void setValor(String lexema_EntOid) {
		valor = lexema_EntOid; 	
	}
	
	public String getValor() {
		return valor; 
	}
		
	public void set_esIdentificador(boolean es_identificador) {
		this.es_identificador = es_identificador; 
	}
	
	public boolean esIdentificador() {
		return this.es_identificador;  
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
