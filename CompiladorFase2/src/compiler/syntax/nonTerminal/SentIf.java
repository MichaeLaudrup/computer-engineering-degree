package compiler.syntax.nonTerminal;

public class SentIf extends NonTerminal{
	private boolean existeRetorno; 
	
	public SentIf() {
		existeRetorno = false; 
	}
	
	public void marcarExistenciaRetorno() {
		existeRetorno = true; 
	}
	
	public boolean contieneRetorno() {
		return existeRetorno; 
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
