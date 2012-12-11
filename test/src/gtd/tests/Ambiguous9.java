package gtd.tests;

import gtd.SGTDBF;
import gtd.grammar.structure.Alternative;
import gtd.grammar.symbols.Literal;
import gtd.grammar.symbols.Sort;
import gtd.result.AbstractNode;

/*
S ::= E
E ::= E + E | E * E | 1

NOTE: This test, tests prefix sharing.
*/
public class Ambiguous9 extends SGTDBF{
	
	public Ambiguous9(char[] input){
		super(input);
	}
	
	public Alternative[] S(){
		return new Alternative[]{
			new Alternative(new Sort("E"))
		};
	}
	
	public Alternative[] E(){
		return new Alternative[]{
			new Alternative(new Sort("E"), new Literal("+"), new Sort("E")),
			new Alternative(new Sort("E"), new Literal("*"), new Sort("E")),
			new Alternative(new Literal("1"))
		};
	}
	
	public static void main(String[] args){
		Ambiguous9 a9 = new Ambiguous9("1+1+1".toCharArray());
		AbstractNode result = a9.parse("S");
		System.out.println(result);
		
		System.out.println("S([E(E(1),+,E(E(1),+,E(1))),E(E(E(1),+,E(1)),+,E(1))]) <- good");
	}
}
