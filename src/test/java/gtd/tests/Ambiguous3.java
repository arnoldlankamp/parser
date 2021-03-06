package gtd.tests;

import gtd.Parser;
import gtd.generator.FromClassGenerator;
import gtd.generator.ParserStructure;
import gtd.grammar.structure.Alternative;
import gtd.grammar.symbols.Literal;
import gtd.grammar.symbols.Sort;
import gtd.result.AbstractNode;

/*
S ::= AA
A ::= aa | a
*/
public class Ambiguous3{
	
	public static Alternative[] S(){
		return new Alternative[]{
			new Alternative(new Sort("A"), new Sort("A"))
		};
	}
	
	public static Alternative[] A(){
		return new Alternative[]{
			new Alternative(new Literal("aa")),
			new Alternative(new Literal("a"))
		};
	}
	
	public static void main(String[] args){
		ParserStructure structure = new FromClassGenerator(Ambiguous3.class).generate();
		Parser a3 = new Parser("aaa".toCharArray(), structure);
		AbstractNode result = a3.parse("S");
		System.out.println(result);
		
		System.out.println("[S(A(\"a\"),A(\"aa\")),S(A(\"aa\"),A(\"a\"))] <- good");
	}
}
