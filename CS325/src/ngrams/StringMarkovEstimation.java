package ngrams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import smoothing.NoSmoothing;

public class StringMarkovEstimation {
	static public void main(String[] args) {
		Unigram unigram=new Unigram(new NoSmoothing());
		Bigram bigram=new Bigram(new NoSmoothing());
		
		try {
			IOUtils.readUnigrams(unigram, new FileInputStream("1grams.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			IOUtils.readBigrams(bigram, new FileInputStream("2grams.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] w={"he","came","to","my","school","to","study"};
		double p=unigram.getLikelihood(w[0]);
		
		for (int i=1;i<w.length;i++) {
			p=p*bigram.getLikelihood(w[i-1], w[i]);
		}
		
		System.out.println(p);
	}
}
