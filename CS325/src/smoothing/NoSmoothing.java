package smoothing;

import java.util.Map;
import java.util.stream.Collectors;

import ngrams.Bigram;
import ngrams.Unigram;

public class NoSmoothing implements ISmoothing {
	@Override
	public double getUnseenLikelihood() {
		return 0;
	}
	
	@Override
	public void estimateMaximumLikelihoods(Unigram unigram) {
		Map<String,Long> countMap=unigram.getCountMap();
		Map<String,Double> map=countMap.entrySet().stream().collect(Collectors.toMap(entry->entry.getKey(), entry->(double)entry.getValue()/unigram.getTotalCount()));
		unigram.setLikelihoodMap(map);
	}
	
	@Override
	public void estimateMaximumLikelihoods(Bigram bigram) {
		Map<String,Unigram> unigramMap=bigram.getUnigramMap();
		for (Unigram unigram:unigramMap.values())
			unigram.estimateMaximumLikelihoods();
	}
	
	@Override
	public ISmoothing createInstance() {
		return new NoSmoothing();
	}
}
