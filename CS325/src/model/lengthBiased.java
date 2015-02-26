package model;

import ngrams.Bigram;
import ngrams.Unigram;

public class lengthBiased implements ILanguageModel {
	private Unigram n_1gram;
	private Bigram n_2gram;
	//private double lengthWeight;
	//private double averageLength;
	
	public lengthBiased(Unigram unigram,Bigram bigram,double length) {
		n_1gram=unigram;
		n_2gram=bigram;
		//this.lengthWeight=(length);
		//averageLength=setLengthAvg();
	}
	
/*	private double setLengthAvg() {
		int totLen=0;
		int numWord=0;
		for (String word:n_1gram.getLikelihoodMap().keySet()) {
			if (word.length()>2) { 
				totLen+=word.length();
				numWord+=1;
			}
		}
		return (double)((totLen/numWord));
	}
*/	
	@Override
   	public double getLikelihood(String word1, String word2) {
		//if (word2.length()>averageLength) {
			return n_2gram.contains(word1,word2)?0.5*n_2gram.getLikelihood(word1, word2)+0.5*n_1gram.getLikelihood(word2):n_1gram.getLikelihood(word2);
		//}
		//return n_2gram.contains(word1, word2)?(0.5-lengthWeight)*n_2gram.getLikelihood(word1,word2)+(lengthWeight/averageLength)*word2.length()+0.5*n_1gram.getLikelihood(word2):(1-lengthWeight)*n_1gram.getLikelihood(word2)+(lengthWeight/averageLength)*word2.length();
	}
}
