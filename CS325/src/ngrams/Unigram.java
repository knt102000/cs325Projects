package ngrams;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Unigram {
	private Map<String,Double> m_likelihoods;
	private Map<String,Long> m_counts;
	private long t_counts;
	
	public Unigram() {
		m_counts=new HashMap<>();
		t_counts=0;
	}
	
	public void add (String word, long count) {
		m_counts.compute(word, (k,v)->(v==null) ? count:v+count);
		t_counts=+count;
	}
	
	public double getLikelihood (String word) {
		Double d=m_likelihoods.get(word);
		return (d != null) ? d : 0;
	}
	
	public void estimateMaximumLikeligoods() {
		m_likelihoods=new HashMap<>(m_counts.size());
		
		for (Entry<String,Long> entry : m_counts.entrySet())
			m_likelihoods.put(entry.getKey(), (double) entry.getValue()/t_counts);
	}
	
	public StringDoublePair getBest() {
		if (m_likelihoods.isEmpty()) return null;
		
		StringDoublePair p=new StringDoublePair(null,-1);
		
		for (Entry<String,Double> entry : m_likelihoods.entrySet()) {
			if (p.getDouble() < entry.getValue())
				p.set(entry.getKey(),entry.getValue());
		}
		
		return p;
	}
	
	
}
