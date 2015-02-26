package tagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs325.classifier.AbstractClassifier;
import cs325.classifier.StringFeature;

public class GreedyTagger extends AbstractTagger{
	public GreedyTagger(AbstractClassifier classifier) {
		super(classifier);
	}
	
	@Override
	public List<TagList> decode(List<String> words) {
		TagList tags=new TagList();
		
		List<StringFeature> features;

		int size=words.size();
		
		for (int i=0;i<size;i++) {
			features=getFeatures(words,tags,i);
			tags.add(Collections.max(classifier.predict(features)));
		}
		
		List<TagList> list=new ArrayList<>(1);
		list.add(tags);
		
		return list;
	}
}
