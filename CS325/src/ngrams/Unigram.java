package ngrams;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Unigram {
	private Map<String,Double> m_likelihoods;
	private Map<String,Long> m_counts;
	private long t_counts;
	
	public Unigram() {
		m_counts=new HashMap<>();
		t_counts=0;
	}
	
	public void add (String word, long count) {
		m_counts.merge(word, count, (oldValue,newValue)->oldValue+newValue);
		t_counts=t_counts+count;
	}
	
	public double getLikelihood (String word) {
		double d=m_likelihoods.getOrDefault(word,0.0);
		return d;
	}
	
	public void estimateMaximumLikelihoods() {
		m_likelihoods=m_counts.entrySet().stream().collect(Collectors.toMap(entry->entry.getKey(),entry->(double) entry.getValue()/t_counts));
	}
	
	public Entry<String,Double> getBest() {
		Entry<String,Double> best=m_likelihoods.entrySet().stream().max(Entry.comparingByValue()).orElse(null);
		
		return best;
	}
	
	public List<Entry<String,Double>> toSortedList() {
		List<Entry<String,Double>> sortedList=m_likelihoods.entrySet().stream().sorted(Entry.comparingByValue(Collections.reverseOrder())).collect(Collectors.toList());
	
		return sortedList;
	}
	
	public long getTotalCount() {
		return t_counts;
	}
	
	public Map<String,Long> getCountMap() {
		return m_counts;
	}
	
	public void setLikelihoodMap(Map<String, Double> likelihoods) {
		m_likelihoods.putAll(likelihoods);
	}
}
