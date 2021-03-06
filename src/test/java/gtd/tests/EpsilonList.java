package gtd.tests;

import gtd.Parser;
import gtd.generator.FromClassGenerator;
import gtd.generator.ParserStructure;
import gtd.grammar.structure.Alternative;
import gtd.grammar.symbols.Epsilon;
import gtd.grammar.symbols.PlusList;
import gtd.grammar.symbols.Sort;
import gtd.result.AbstractNode;

/*
S ::= A+
A ::= epsilon
*/
public class EpsilonList{
	
	public static Alternative[] S(){
		return new Alternative[]{
			new Alternative(new PlusList(new Sort("A")))
		};
	}
	
	public static Alternative[] A(){
		return new Alternative[]{
			new Alternative(new Epsilon())
		};
	}
	
	public static void main(String[] args){
		ParserStructure structure = new FromClassGenerator(EpsilonList.class).generate();
		Parser el = new Parser("".toCharArray(), structure);
		AbstractNode result = el.parse("S");
		System.out.println(result);
		
		System.out.println("S(A+(repeat(A()))) <- good");
	}
}
