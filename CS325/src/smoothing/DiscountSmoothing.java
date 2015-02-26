package smoothing;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import ngrams.Bigram;
import ngrams.Unigram;

public class DiscountSmoothing implements ISmoothing{
	private double d_unseenlikelihood;
	private double d_alpha;
	
	public DiscountSmoothing(double alpha) {
		d_alpha=alpha;
	}
	
	@Override
	public void estimateMaximumLikelihoods(Unigram unigram) {
		Map<String,Long> countMap=unigram.getCountMap();
		long totalCount=unigram.getTotalCount();
		d_unseenlikelihood=d_alpha*Collections.min(countMap.values())/totalCount;
		Map<String, Double> map=countMap.entrySet().stream().collect(Collectors.toMap(entry->entry.getKey(),entry->(entry.getValue()-d_unseenlikelihood)/totalCount));
		unigram.setLikelihoodMap(map);
	}
	
	@Override
	public void estimateMaximumLikelihoods(Bigram bigram) {
		Map<String,Unigram> unigramMap=bigram.getUnigramMap();
		
		for (Unigram unigram:unigramMap.values())
			unigram.estimateMaximumLikelihoods();
		
		d_unseenlikelihood=d_alpha*min(unigramMap);
	}
	
	private double min(Map<String,Unigram> unigramMap) {
		double min=Double.MAX_VALUE;
		
		for (Unigram unigram:unigramMap.values())
			min=Math.min(min,unigram.getLikelihood(null));
		
		return min;
	}
	
	@Override
	public double getUnseenLikelihood() {
		return d_unseenlikelihood;
	}
	
	@Override
	public ISmoothing createInstance() {
		return new DiscountSmoothing(d_alpha);
	}
}
