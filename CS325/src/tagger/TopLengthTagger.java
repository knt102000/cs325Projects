package tagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs325.classifier.AbstractClassifier;
import cs325.classifier.Prediction;
import cs325.classifier.StringFeature;

public class TopLengthTagger extends ExhaustiveTagger {
	int k;
	
	public TopLengthTagger(AbstractClassifier classifier) {
		super(classifier);
	}
	
	@Override
	protected List<Prediction> getPredictions(List<StringFeature> features,int index) {
		
		List<Prediction> TopK=new ArrayList<Prediction>();
		List<Prediction> list=classifier.predict(features);
		
		//System.out.println("index"+index);
		
		//System.out.println("list"+list.size());
		
		k=list.size()-(index);
		
		if (k<=0) {
			k=1;
		}
		
		//System.out.println("k"+k);
		
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
		//System.out.println(TopK.size());
		return TopK;
	}
}
