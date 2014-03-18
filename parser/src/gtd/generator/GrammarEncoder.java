package gtd.generator;

import gtd.grammar.structure.Alternative;
import gtd.grammar.structure.IStructure;
import gtd.grammar.structure.Scope;
import gtd.grammar.symbols.AbstractConstruct;
import gtd.grammar.symbols.AbstractSymbol;
import gtd.grammar.symbols.CILiteral;
import gtd.grammar.symbols.Char;
import gtd.grammar.symbols.CharRange;
import gtd.grammar.symbols.Choice;
import gtd.grammar.symbols.Literal;
import gtd.grammar.symbols.Optional;
import gtd.grammar.symbols.PlusList;
import gtd.grammar.symbols.Sequence;
import gtd.grammar.symbols.Sort;
import gtd.grammar.symbols.StarList;
import gtd.util.ArrayList;
import gtd.util.IntegerKeyedHashMap;

// TODO:
// -Add nesting restrictions
public class GrammarEncoder{
	private final ArrayList<String> sortIndexMap;
	
	public GrammarEncoder(ArrayList<String> sortIndexMap) {
		super();
		
		this.sortIndexMap = sortIndexMap;
	}
	
	private int getContainerIndex(String sortName){
		int sortIndex = sortIndexMap.find(sortName);
		if(sortIndex == -1){
			sortIndex = sortIndexMap.size();
			sortIndexMap.add(sortName);
		}
		return sortIndex;
	}
	
	private int getNextFreeIndex() {
		int sortIndex = sortIndexMap.size();
		sortIndexMap.add(null);
		return sortIndex;
	}
	
	private static PlusList constructIdentifiedPlusList(AbstractSymbol idenfitiedSymbol, AbstractSymbol[] identifiedSeparators){
		if(idenfitiedSymbol instanceof Char){
			return new PlusList((Char) idenfitiedSymbol, identifiedSeparators);
		}else if(idenfitiedSymbol instanceof CharRange){
			return new PlusList((CharRange) idenfitiedSymbol, identifiedSeparators);
		}else if(idenfitiedSymbol instanceof Literal){
			return new PlusList((Literal) idenfitiedSymbol, identifiedSeparators);
		}else if(idenfitiedSymbol instanceof CILiteral){
			return new PlusList((CILiteral) idenfitiedSymbol, identifiedSeparators);
		}else if(idenfitiedSymbol instanceof IdentifiedSymbol){
			return new PlusList((IdentifiedSymbol) idenfitiedSymbol, identifiedSeparators);
		}else{
			throw new RuntimeException(String.format("Unsupported plus list symbol type: %s", idenfitiedSymbol.getClass().toString()));
		}
	}
	
	private static StarList constructIdentifiedStarList(AbstractSymbol idenfitiedSymbol, AbstractSymbol[] identifiedSeparators){
		if(idenfitiedSymbol instanceof Char){
			return new StarList((Char) idenfitiedSymbol, identifiedSeparators);
		}else if(idenfitiedSymbol instanceof CharRange){
			return new StarList((CharRange) idenfitiedSymbol, identifiedSeparators);
		}else if(idenfitiedSymbol instanceof Literal){
			return new StarList((Literal) idenfitiedSymbol, identifiedSeparators);
		}else if(idenfitiedSymbol instanceof CILiteral){
			return new StarList((CILiteral) idenfitiedSymbol, identifiedSeparators);
		}else if(idenfitiedSymbol instanceof IdentifiedSymbol){
			return new StarList((IdentifiedSymbol) idenfitiedSymbol, identifiedSeparators);
		}else{
			throw new RuntimeException(String.format("Unsupported star list symbol type: %s", idenfitiedSymbol.getClass().toString()));
		}
	}
	
	private IdentifiedSymbol identifyConstruct(AbstractConstruct construct, String sortName, int scopedSortId){
		if(construct instanceof Optional){
			Optional optional = (Optional) construct;
			AbstractSymbol optionalSymbol = identifySymbol(optional.symbol, sortName, scopedSortId);
			Optional identifiedOptional = new Optional(optionalSymbol);
			return new IdentifiedSymbol(identifiedOptional , getNextFreeIndex());
		}else if(construct instanceof PlusList){
			PlusList plusList = (PlusList) construct;
			AbstractSymbol symbol = plusList.symbol;
			AbstractSymbol identifiedSymbol = identifySymbol(symbol, sortName, scopedSortId);
			AbstractSymbol[] identifiedSeparators = identifySymbols(plusList.separators, sortName, scopedSortId);
			
			PlusList identifiedPlusList = constructIdentifiedPlusList(identifiedSymbol, identifiedSeparators);
			return new IdentifiedSymbol(identifiedPlusList, getNextFreeIndex());
		}else if(construct instanceof StarList){
			StarList starList = (StarList) construct;
			AbstractSymbol symbol = starList.symbol;
			AbstractSymbol identifiedSymbol = identifySymbol(symbol, sortName, scopedSortId);
			AbstractSymbol[] identifiedSeparators = identifySymbols(starList.separators, sortName, scopedSortId);
			
			StarList identifiedStarList = constructIdentifiedStarList(identifiedSymbol, identifiedSeparators);
			return new IdentifiedSymbol(identifiedStarList, getNextFreeIndex());
		}else if(construct instanceof Choice){
			Choice choice = (Choice) construct;
			AbstractSymbol[] identifiedSymbols = identifySymbols(choice.symbols, sortName, scopedSortId);
			
			Choice identifiedChoice = new Choice(identifiedSymbols);
			return new IdentifiedSymbol(identifiedChoice , getNextFreeIndex());
		}else if(construct instanceof Sequence){
			Sequence sequence = (Sequence) construct;
			AbstractSymbol[] identifiedSymbols = identifySymbols(sequence.symbols, sortName, scopedSortId);
			
			Sequence identifiedSequence = new Sequence(identifiedSymbols);
			return new IdentifiedSymbol(identifiedSequence , getNextFreeIndex());
		}else{
			throw new RuntimeException(String.format("Unsupported construct type: %s", construct.getClass().toString()));
		}
	}
	
	private AbstractSymbol identifySymbol(AbstractSymbol symbol, String sortName, int scopedSortId){
		if(symbol instanceof Sort){
			Sort sort = (Sort) symbol;
			if(sort.sortName.equals(sortName)){
				return new IdentifiedSymbol(sort, scopedSortId);
			}else{
				return new IdentifiedSymbol(sort, getContainerIndex(sort.sortName));
			}
		}else if(symbol instanceof AbstractConstruct){
			return identifyConstruct((AbstractConstruct) symbol, sortName, scopedSortId);
		}
		return symbol;
	}
	
	private AbstractSymbol[] identifySymbols(AbstractSymbol[] symbols, String sortName, int scopedSortId){
		AbstractSymbol[] newSymbols = new AbstractSymbol[symbols.length];
		for(int i = symbols.length - 1; i >= 0; --i){
			newSymbols[i] = identifySymbol(symbols[i], sortName, scopedSortId);
		}
		return newSymbols;
	}
	
	private Alternative identifySortsAndConstructs(String sortName, int scopedSortId, Alternative alternative){
		return new Alternative(identifySymbols(alternative.alternative, sortName, scopedSortId));
	}
	
	private ArrayList<Alternative> encodeAlternatives(String sortName, int sortId, IStructure[] structures, IntegerKeyedHashMap<ArrayList<Alternative>> groupedAlternatives){
		ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
		for(int i = structures.length - 1; i >= 0; --i){
			IStructure structure = structures[i];
			if(structure instanceof Scope){
				ArrayList<Alternative> scopedAlternatives = encodeAlternatives(sortName, getNextFreeIndex(), ((Scope) structure).alternatives, groupedAlternatives);
				Object[] scopedAlternativesBackingArray = scopedAlternatives.getBackingArray();
				alternatives.addFromArray(scopedAlternativesBackingArray, 0, scopedAlternatives.size());
			}else{
				Alternative alternative = (Alternative) structure;
				alternatives.add(identifySortsAndConstructs(sortName, sortId, alternative));
			}
		}
		
		groupedAlternatives.putUnsafe(sortId, alternatives);
		
		return alternatives;
	}
	
	public IntegerKeyedHashMap<ArrayList<Alternative>> flatten(String sortName, IStructure[] structures){
		IntegerKeyedHashMap<ArrayList<Alternative>> groupedAlternatives = new IntegerKeyedHashMap<ArrayList<Alternative>>();
		
		encodeAlternatives(sortName, getContainerIndex(sortName), structures, groupedAlternatives);
		
		return groupedAlternatives;
	}
}