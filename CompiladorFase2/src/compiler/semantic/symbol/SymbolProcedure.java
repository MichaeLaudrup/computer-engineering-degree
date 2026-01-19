package compiler.semantic.symbol;
import java.util.ArrayList;
import java.util.Collections;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolProcedure.
 */

// TODO: Student work
//       Include properties to characterize procedure calls

public class SymbolProcedure
    extends SymbolBase
{
   private int numParamFormales; 
   private ArrayList<String> parametros_formales; 
    /**
     * Constructor for SymbolProcedure.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolProcedure (ScopeIF scope,  String name, TypeIF type, ArrayList<String> lista_formales)
    {
        super (scope, name, type);
        this.numParamFormales = lista_formales.size();
        this.parametros_formales = lista_formales; 
    } 
    
    public ArrayList<String> getParametrosFormales(){
    	return this.parametros_formales; 
    }
    
    public boolean contieneParametrosFormales() {
    	return !parametros_formales.isEmpty(); 
    }
    
    public int getNumParametrosFormales() {
    	return this.numParamFormales; 
    	
    }
    
    
   
    
}
