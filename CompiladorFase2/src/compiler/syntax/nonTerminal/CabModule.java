package compiler.syntax.nonTerminal;

public class CabModule extends NonTerminal{
	private String nombre_modulo; 
	
	public CabModule() {
	}
	public CabModule( CabModule cabModule) {
	}
	
	public String getNombreModulo() {
		return nombre_modulo; 
	}
	public void setNombreModulo(String nombre_modulo) {
		this.nombre_modulo = nombre_modulo; 
		
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
