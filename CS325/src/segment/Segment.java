package segment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.ILanguageModel;

public class Segment extends AbstractSegment {
	
	public Segment(ILanguageModel model) {
		super(model);
	}
	
	@Override
	public Sequence segment(String s) {
		List<Sequence> list=new ArrayList<>();
		segmentAux(list,s,0,new Sequence());
		return Collections.max(list);
	}
	
	private void segmentAux(List<Sequence> list, String str, int beginIndex, Sequence sequence) {
		if (beginIndex==str.length()) {
			list.add(sequence);
			return;
		}
		
		String word1=sequence.getPreviousWord();
		int endIndex;
		int len=str.length();
		double likelihood;
		Sequence copy;
		String word2;
		
		for (endIndex=beginIndex+1;endIndex<=len;endIndex++) {
			word2=str.substring(beginIndex, endIndex);
			likelihood=l_model.getLikelihood(word1,word2);
			copy=new Sequence(sequence);
			copy.add(word2,likelihood);
			segmentAux(list,str,endIndex,copy);
		}
 	}
}
