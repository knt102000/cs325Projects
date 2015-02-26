package model;

import ngrams.Bigram;
import ngrams.Unigram;

public class Interpolation implements ILanguageModel {
	private Unigram n_1gram;
	private Bigram n_2gram;
	private double d_1gramWeight;
	private double d_2gramWeight;
	
	public Interpolation (Unigram unigram,Bigram bigram,int unigramWeight,int bigramWeight) {
		n_1gram=unigram;
		n_2gram=bigram;
		d_1gramWeight=unigramWeight;
		d_2gramWeight=bigramWeight;
	}
	
	@Override
	public double getLikelihood (String word1, String word2) {
		return d_1gramWeight*n_1gram.getLikelihood(word2)+d_2gramWeight*n_2gram.getLikelihood(word1, word2);
	}
}
