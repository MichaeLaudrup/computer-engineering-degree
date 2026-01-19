package compiler.syntax.nonTerminal;

public class SentElse extends NonTerminal{

	private boolean contieneReturn; 
	private boolean existeElse; 
	public SentElse() {
		contieneReturn = false; 
		existeElse = true; 
	}
	
	public boolean existeElse() {
		return this.existeElse; 
	}
	
	public void marcarComoVacia() {
		this.existeElse = false; 
	}
	
	
	public void retornoEncontrado() {
		this.contieneReturn = true; 
		
	}
	
	public boolean existeRetorno() {
		return contieneReturn; 
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
