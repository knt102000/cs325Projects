package model;

import java.util.Map;

import utils.MathUtils;
import ngrams.Bigram;
import ngrams.Unigram;

public class Backoff implements ILanguageModel {
	private Unigram n_1gram;
	private Bigram n_2gram;
	private double d_1gramWeight;
	
	public Backoff(Unigram unigram,Bigram bigram,double alpha) {
		n_1gram=unigram;
		n_2gram=bigram;
		d_1gramWeight=getUnigramWeight(alpha);
	}
	
	public double getUnigramWeight(double alpha) {
		double uniAvg=MathUtils.average(n_1gram.getLikelihoodMap().values());
		Map<String,Double> map;
		double sum=0;
		int total=0;
		
		for (Unigram unigram:n_2gram.getUnigramMap().values()) {
			map=unigram.getLikelihoodMap();
			sum+=MathUtils.sum(map.values());
			total+=map.size();
		}
		
		double biAvg=sum/total;
		return alpha*biAvg/uniAvg;
	}
	
	@Override
	public double getLikelihood(String word1, String word2) {
		return n_2gram.contains(word1,word2)?n_2gram.getLikelihood(word1, word2):d_1gramWeight*n_1gram.getLikelihood(word2);
	}
}
