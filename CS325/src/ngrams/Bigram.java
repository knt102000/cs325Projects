package ngrams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Bigram {
	
	private Map<String,Unigram> m_unigrams;
	
	public Bigram() {
		m_unigrams=new HashMap<>();
	}
	
	public void add(String word1, String word2, long count) {
		m_unigrams.computeIfAbsent(word1,key->new Unigram()).add(word2, count);
	}
	
	public void estimateMaximumLikelihoods() {
		m_unigrams.values().stream().forEach(unigram->unigram.estimateMaximumLikelihoods());
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
}
