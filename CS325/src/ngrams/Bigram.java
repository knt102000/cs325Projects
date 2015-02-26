package ngrams;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import smoothing.ISmoothing;

public class Bigram {
	
	private Map<String,Unigram> m_unigrams;
	private ISmoothing i_smoothing;
	
	public Bigram(ISmoothing smoothing) {
		m_unigrams=new HashMap<>();
		i_smoothing=smoothing;
	}
	
	public void add(String word1, String word2, long count) {
		m_unigrams.computeIfAbsent(word1,key->new Unigram(i_smoothing.createInstance())).add(word2, count);
	}
	
	public void estimateMaximumLikelihoods() {
		//m_unigrams.values().stream().forEach(unigram->unigram.estimateMaximumLikelihoods());
		i_smoothing.estimateMaximumLikelihoods(this);
	}
	
	public double getLikelihood(String word1, String word2) {
		Unigram unigram=m_unigrams.get(word1);
		
		if (unigram!=null)
			return unigram.getLikelihood(word2);
		else
			return 0d;

	}
	
	public Entry<String,Double> getBest(String word) {
		Unigram unigram=m_unigrams.get(word);
		
		if (unigram!=null)
			return unigram.getBest();
		else
			return null;
		
	}
	
	public List<Entry<String,Double>> toSortedList(String word) {
		Unigram unigram=m_unigrams.get(word);
		
		if (unigram!=null)
			return unigram.toSortedList();
		else
			return null;
		
	}
	
	public Map<String,Unigram> getUnigramMap() {
		return m_unigrams;
	}
	
	public boolean contains(String word1, String word2) {
		Unigram unigram=m_unigrams.get(word1);
		return (unigram!=null)&&unigram.contains(word2);
	}
	
	public Set<String> getWordSet() {
		Set<String> set=new HashSet<>();
		
		for (Entry<String,Unigram> entry:m_unigrams.entrySet()) {
			set.add(entry.getKey());
			set.addAll(entry.getValue().getCountMap().keySet());
		}
		
		return set;
	}
}
