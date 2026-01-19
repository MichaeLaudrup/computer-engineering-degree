package compiler.semantic.symbol;

import java.util.ArrayList;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolFunction.
 */

// TODO: Student work
//       Include properties to characterize function calls

public class SymbolFunction
    extends SymbolProcedure
{
    private String tipo_retorno; 
    /**
     * Constructor for SymbolFunction.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolFunction (ScopeIF scope,  String name,  TypeIF type, String tipo_retorno, ArrayList<String> vector_param_formales)
    {
    	super (scope, name, type, vector_param_formales);
    	this.tipo_retorno = tipo_retorno; 
    	
    } 
    
    public String getTipoRetorno() {
    	return this.tipo_retorno; 
    	
    }
    
}
