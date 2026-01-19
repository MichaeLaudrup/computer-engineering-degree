package compiler.syntax.nonTerminal;

public class Sentencia extends NonTerminal{
	private String tipo_sentencia;
	private String tipo_retorno; 
	private boolean contieneRetorno;   //ESTA VARIABLE ES PARA CONTROLAR SI EXISTE RETORNO EN UNA SENTENCIA FOR O EN UNA SENTENCIA IF-ELSE
	
	public Sentencia( String tipo_sentencia) {
			this.tipo_sentencia = tipo_sentencia; 
			contieneRetorno = false; 
	}
	
	public String getTipoSentencia() {
		return this.tipo_sentencia; 
	}
	
	public boolean contieneRetorno() {
		return this.contieneRetorno; 
	}
	
	public void marcarRetornoInterno() {
		this.contieneRetorno = true; 
	}
	
	public void setTipoRetorno(String tipo) {
		this.tipo_retorno = tipo; 
	}
	
	public String getTipoRetorno() {
		return this.tipo_retorno; 
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
