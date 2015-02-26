package tagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs325.classifier.AbstractClassifier;
import cs325.classifier.Prediction;
import cs325.classifier.StringFeature;

public class TopKTagger extends ExhaustiveTagger {
	final int k;
	
	public TopKTagger(AbstractClassifier classifier, int k) {
		super(classifier);
		this.k=k;
	}
	
	@Override
	protected List<Prediction> getPredictions(List<StringFeature> features) {
		List<Prediction> TopK=new ArrayList<Prediction>();
		List<Prediction> list=classifier.predict(features);
		
		Collections.sort(list);
		Collections.reverse(list);
		
		Prediction previous=list.get(0);
		
		int counter=0;
		
		for (Prediction key:list) {
			if (previous.compareTo(key)!=0) {
				counter+=1;
				previous=key;
			}
			
			if (counter<k) TopK.add(key); else break;
		}
		
		return TopK;
	}
}
