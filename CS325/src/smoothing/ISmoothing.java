package smoothing;

import ngrams.Bigram;
import ngrams.Unigram;

public interface ISmoothing {
	void estimateMaximumLikelihoods(Unigram unigram);
	
	void estimateMaximumLikelihoods(Bigram bigram);
	
	double getUnseenLikelihood();
	
	ISmoothing createInstance();
}
