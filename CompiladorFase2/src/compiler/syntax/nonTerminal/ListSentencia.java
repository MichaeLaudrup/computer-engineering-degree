package compiler.syntax.nonTerminal;
import java.util.ArrayList; 
public class ListSentencia extends NonTerminal{
	private ArrayList<Sentencia> lista_sentencias; 
	private int indice_primer_retorno; 
	
	public ListSentencia(Sentencia primera_sentencia) {
		lista_sentencias = new ArrayList<Sentencia>(); 
		insertarNuevaSentencia(primera_sentencia); 
	}
	
	public void insertarNuevaSentencia(Sentencia nueva_sentencia) {
		lista_sentencias.add(nueva_sentencia); 

	}
	
	public boolean existeSentenciaReturn() {
		if(!lista_sentencias.isEmpty()) {
			for(int i = 0; i < lista_sentencias.size(); i++) {
				Sentencia sentencia_i = lista_sentencias.get(i); 
				if(sentencia_i.getTipoSentencia().equalsIgnoreCase("SENTENCIA_RETURN") || sentencia_i.contieneRetorno() ) {
					indice_primer_retorno = i; 
					return true; 
				}	
			}
		}

		return false; 
	}
	
	public boolean todosLosRetornosSonDelMismoTipo() {
		if(!lista_sentencias.isEmpty()) {
			boolean concordanciaRetornos = true; 
			String tipo_return = lista_sentencias.get(indice_primer_retorno).getTipoRetorno(); 
			
			for(int i = 0; i < lista_sentencias.size(); i++) {
				Sentencia sentencia_i = lista_sentencias.get(i); 
				if(sentencia_i.getTipoSentencia().equalsIgnoreCase("SENTENCIA_RETURN") || sentencia_i.contieneRetorno() ) {
					if(!sentencia_i.getTipoRetorno().equalsIgnoreCase(tipo_return)) {
						return false; 
					}
				}	
			}
			return true; 
		}else{
			return false; 
		}
	}
	
	public String obtenerTipoRetorno() {
		if(this.existeSentenciaReturn() && this.todosLosRetornosSonDelMismoTipo()) {
			return lista_sentencias.get(indice_primer_retorno).getTipoRetorno();
		}else {
			return "NO COINCIDENCIA"; 
		}
		
		
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
