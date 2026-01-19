package compiler.syntax.nonTerminal;

public class SentReturn extends NonTerminal{
	private String tipo_retorno; 
	
	public SentReturn() {
	}
	//CONTINUAR AQUI: HAY QUE CONTROLAR TIPO RETURN Y TIPO RETORNO FORMAL
	
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
