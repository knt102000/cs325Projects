//Credit to Jinho Choi's Segment.java for the basis of this program/code

package segment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.ILanguageModel;

public class TsuiSegment extends AbstractSegment {

	public TsuiSegment(ILanguageModel model) {
		super(model);	
	}
	
	@Override
	public Sequence segment(String tweet) {
		List<Sequence> allPossible=new ArrayList<Sequence>();
		tweet=tweet.substring(1,tweet.length()); //Removes the hash mark
		toSplit(allPossible,tweet,0,new Sequence());
		return Collections.max(allPossible);
	}
	
	private void toSplit(List<Sequence> totList,String tweet,int currInd,Sequence currSequence) {
		if (currInd==tweet.length()) {
			totList.add(currSequence);
			return;
		}
		
		currInd=checkStringInteger(currInd,tweet,currSequence); //Checks and handles if part of string is a number/integer
		
		String word1=currSequence.getPreviousWord();
		
		if (isInteger(word1)==true) word1=null; //If previous word segmented was a number, then set previous word to null
		
		int endInd;
		int len=tweet.length();
		double likelihood;
		Sequence copy;
		String word2;
		
		if (currInd+1<=len) {
			for (endInd=currInd+1;endInd<=len;endInd++) {
				word2=tweet.substring(currInd, endInd);
				likelihood=l_model.getLikelihood(word1,word2);
				copy=new Sequence(currSequence);
				if (isPlural(endInd,tweet)==true) {
					if (endInd==len-1) { //If last letter in tweet is 's', then the last word in sequence MUST be plural
						copy.add(word2.concat("s"),likelihood);
						totList.add(copy);
						return;
					}
					Sequence splitCopy=new Sequence(currSequence); //If next letter is 's', then there is an equally likely chance the last word added is plural
					splitCopy.add(word2.concat("s"), likelihood);
					toSplit(totList,tweet,endInd+1,splitCopy);
				}
				copy.add(word2,likelihood);
				toSplit(totList,tweet,endInd,copy);
			}
		}
		else {
			totList.add(currSequence);
			return;
		}
	}
	
/*	private int checkPastPresentTense(int currInd,String tweet) { //Work in progress to handle present and past tense words, not complete
		if (currInd+3<=tweet.length()) {
			if (tweet.substring(currInd, currInd+3).equals("ing")) {
				return currInd+3;
			}
		}
		return currInd+1;
	}*/
	
	private boolean isPlural(int nextInd,String tweet) { //Checks if a given word can be potentially plural, returns true if so
		if (nextInd<tweet.length()) {
			if (tweet.charAt(nextInd)=='s') {
				return true;
			}
		}
		return false;
	}
	
	private int checkStringInteger(int currInd,String tweet,Sequence currSequence) { //Handles a string that is potentially a number (incl. decimals) and returns a new indice
		String number="";
		boolean isNumber=true;
		
		while (isNumber && currInd<tweet.length()) {
			if (isInteger(tweet.substring(currInd,currInd+1))==true) {
				number+=tweet.charAt(currInd);
				currInd+=1;
			}
			else if (tweet.charAt(currInd)=='.') {
				isNumber=false;
				if (isInteger(tweet.substring(currInd+1,currInd+2))==true) {
					number+=tweet.substring(currInd, currInd+2);
					currInd+=2;
					isNumber=true;
				}
			}
			else isNumber=false;
		}
		
		if (number!="") {
			//Handles groups of years (such as 1990s) and cardinal numbers (1st, 2nd, 3rd, 4th...)
			if ((tweet.charAt(currInd)=='s'&&currInd==tweet.length()-1)) {
				number+=tweet.charAt(currInd);
				currInd+=1;
			}
			else if ((tweet.substring(currInd,currInd+2).equals("st")&&number.charAt(number.length()-1)=='1') || (tweet.substring(currInd,currInd+2).equals("th")&&currInd==tweet.length()-2) || tweet.substring(currInd,currInd+2).equals("nd") || tweet.substring(currInd,currInd+2).equals("rd")) {
				number+=tweet.substring(currInd,currInd+2);
				currInd+=2;
			}
			currSequence.add(number,1);
		}
		
		return currInd;
	}
	
	private boolean isInteger(String s) { //Checks if a given string is an integer, returns true if so
		try {
			Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}
