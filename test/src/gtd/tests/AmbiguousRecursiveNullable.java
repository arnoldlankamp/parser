package gtd.tests;

import gtd.SGTDBF;
import gtd.generator.FromClassGenerator;
import gtd.generator.ParserStructure;
import gtd.grammar.structure.Alternative;
import gtd.grammar.symbols.Literal;
import gtd.grammar.symbols.Sort;
import gtd.grammar.symbols.Epsilon;
import gtd.result.AbstractNode;

/*
S ::= SSS | SS | a | epsilon
*/
public class AmbiguousRecursiveNullable{
	
	public static Alternative[] S(){
		return new Alternative[]{
			new Alternative(new Sort("S"), new Sort("S"), new Sort("S")),
			new Alternative(new Sort("S"), new Sort("S")),
			new Alternative(new Literal("a")),
			new Alternative(new Epsilon())
		};
	}
	
	public static void main(String[] args){
		ParserStructure structure = new FromClassGenerator(AmbiguousRecursiveNullable.class).generate();
		SGTDBF arn = new SGTDBF("aa".toCharArray(), structure);
		AbstractNode result = arn.parse("S");
		System.out.println(result);
		
		System.out.println("[S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(a)],[S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(a)]),S([S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(a)],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(a)]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(a)],[S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(a)],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(a)],[S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1)),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S([S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(cycle(S,1),[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()],[S(cycle(S,1),cycle(S,1)),S(cycle(S,1),cycle(S,1),cycle(S,1)),S()]),S(a)])] <- good");
	}
}
