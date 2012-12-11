package gtd.tests;

import gtd.SGTDBF;
import gtd.grammar.structure.Alternative;
import gtd.grammar.symbols.Epsilon;
import gtd.grammar.symbols.Sort;
import gtd.result.AbstractNode;

/*
S ::= S T U | epsilon
T ::= T U S | epsilon
U ::= U S T | epsilon
*/
public class HiddenRecursionEmpty extends SGTDBF{

	public HiddenRecursionEmpty(char[] input){
		super(input);
	}
	
	public Alternative[] S(){
		return new Alternative[]{
			new Alternative(new Sort("S"), new Sort("T"), new Sort("U")),
			new Alternative(new Epsilon())
		};
	}
	
	public Alternative[] T(){
		return new Alternative[]{
			new Alternative(new Sort("T"), new Sort("U"), new Sort("S")),
			new Alternative(new Epsilon())
		};
	}
	
	public Alternative[] U(){
		return new Alternative[]{
			new Alternative(new Sort("U"), new Sort("S"), new Sort("T")),
			new Alternative(new Epsilon())
		};
	}
	
	public static void main(String[] args){
		HiddenRecursionEmpty hre = new HiddenRecursionEmpty("".toCharArray());
		AbstractNode result = hre.parse("S");
		System.out.println(result);
		
		System.out.println("[S(cycle(S,1),[T(cycle(T,1),[U(cycle(U,1),cycle(S,3),cycle(T,2)),U()],cycle(S,2)),T()],[U(cycle(U,1),cycle(S,2),[T(cycle(T,1),cycle(U,2),cycle(S,3)),T()]),U()]),S()] <- good");
	}
}
