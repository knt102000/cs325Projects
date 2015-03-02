package tagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs325.classifier.AbstractClassifier;
import cs325.classifier.Prediction;
import cs325.classifier.StringFeature;

public class TopKRangeTagger extends ExhaustiveTagger {
	int k;
	final int a;
	final int b;
	
	public TopKRangeTagger(AbstractClassifier classifier, int a, int b) {
		super(classifier);
		this.a=a;
		this.b=b;
	}
	
	@Override
	protected List<Prediction> getPredictions(List<StringFeature> features,int index) {
		
		List<Prediction> TopK=new ArrayList<Prediction>();
		List<Prediction> list=classifier.predict(features);
		
		Collections.sort(list);
		Collections.reverse(list);
		
		Prediction previous=list.get(0);
		
		k=a+(int)(Math.random()*b);
		
		int counter=0;
		
		for (Prediction key:list) {
/*			if (counter<k) TopK.add(key); else break;
			counter+=1;*/
			
			if (previous.compareTo(key)!=0) {
				counter+=1;
				previous=key;
			}
			
			if (counter<k) TopK.add(key); else break;
		}
		//System.out.println(TopK.size());
		return TopK;
	}
}
