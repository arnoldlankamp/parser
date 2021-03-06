package gtd.stack.filter.after;

import gtd.stack.filter.IAfterFilter;

public class StringFollowRestriction implements IAfterFilter{
	private final char[] string;
	
	public StringFollowRestriction(String string){
		super();
		
		this.string = string.toCharArray();
	}
	
	public boolean isFiltered(char[] input, int start, int end){
		if((end + string.length - 1) >= input.length) return false;
		
		for(int i = string.length - 1; i >= 0; --i){
			if(input[end + i] != string[i]) return false;
		}
		
		return true;
	}
	
	public int hashCode(){
		int hashCode = 4;
		for(int i = string.length - 1; i >= 0; --i){
			hashCode = hashCode << 21 | hashCode >>> 11;
			hashCode ^= string[i];
		}
		return hashCode;
	}
	
	public boolean equals(Object other){
		if(other == this) return true;
		if(other == null) return false;
		
		if(other instanceof StringFollowRestriction){
			StringFollowRestriction otherStringFollowRestriction = (StringFollowRestriction) other;
			
			char[] otherString = otherStringFollowRestriction.string;
			if(string.length != otherString.length) return false;
			
			for(int i = string.length - 1; i >= 0; --i){
				if(string[i] != otherString[i]) return false;
			}
			
			return true;
		}
		return false;
	}
}
