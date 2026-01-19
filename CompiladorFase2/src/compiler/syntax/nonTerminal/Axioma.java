package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.ScopeIF;

public class Axioma extends Axiom {
	ScopeIF ambito_central; 
	public Axioma() {
		super(); 
		ambito_central = null; 
	}

	@Override
	public void setAmbitoCentral(ScopeIF ambito_central) {
		this.ambito_central = ambito_central; 
		
	}

	@Override
	public ScopeIF getAmbitoCentral() {
		return this.ambito_central; 
	}
}
