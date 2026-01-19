package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.ScopeIF;

/**
 * Abstract Class for Axiom non terminal.
 */
public abstract class Axiom
    extends NonTerminal
{
    /**
     * Constructor for Axiom.
     */
    public Axiom ()
    {
        super (); 
    }
    
    public abstract void setAmbitoCentral(ScopeIF ambito_central); 
    public abstract ScopeIF getAmbitoCentral(); 
    
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
