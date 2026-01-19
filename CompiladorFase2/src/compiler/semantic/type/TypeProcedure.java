package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Class for TypeProcedure.
 */

// TODO: Student work
//       Include properties to characterize procedure declarations

public class TypeProcedure
    extends TypeBase
{   
	private int numParamFormarles; 
    private ArrayList<String> tipos_param_formales; 
   /**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope.
     */
    public TypeProcedure (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope
     * @param name The name of the procedure.
     */
    public TypeProcedure (ScopeIF scope, String name, ArrayList<String> tipos_param_formales)
    {
        super (scope, name);
        numParamFormarles = tipos_param_formales.size(); 
        if(numParamFormarles != 0) {
        	 this.tipos_param_formales = tipos_param_formales; 
        }else {
        	this.tipos_param_formales = null; 
        }
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
        // TODO: Student work
        return 1;
    }
}
