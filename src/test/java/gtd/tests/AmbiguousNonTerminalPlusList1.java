package gtd.tests;

import gtd.Parser;
import gtd.generator.FromClassGenerator;
import gtd.generator.ParserStructure;
import gtd.grammar.structure.Alternative;
import gtd.grammar.symbols.Char;
import gtd.grammar.symbols.PlusList;
import gtd.grammar.symbols.Sort;
import gtd.result.AbstractNode;

/*
S ::= aA+ | A+a
A ::= a
*/
public class AmbiguousNonTerminalPlusList1{
	
	public static Alternative[] S(){
		return new Alternative[]{
			new Alternative(new Char('a'), new PlusList(new Sort("A"))),
			new Alternative(new PlusList(new Sort("A")), new Char('a'))
		};
	}
	
	public static Alternative[] A(){
		return new Alternative[]{
			new Alternative(new Char('a'))
		};
	}
	
	public static void main(String[] args){
		ParserStructure structure = new FromClassGenerator(AmbiguousNonTerminalPlusList1.class).generate();
		Parser nrpl1 = new Parser("aaa".toCharArray(), structure);
		AbstractNode result = nrpl1.parse("S");
		System.out.println(result);
		
		System.out.println("[S(A+(A('a'),A('a')),'a'),S('a',A+(A('a'),A('a')))] <- good");
	}
}
