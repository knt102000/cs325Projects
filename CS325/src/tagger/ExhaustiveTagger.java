package tagger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import utils.DSUtils;
import cs325.classifier.AbstractClassifier;
import cs325.classifier.Prediction;
import cs325.classifier.StringFeature;

public class ExhaustiveTagger extends AbstractTagger {
	public ExhaustiveTagger(AbstractClassifier classifier) {
		super(classifier);
	}
	
	@Override
	public List<TagList> decode(List<String> words) {
		List<TagList> allTags=new ArrayList<>();
		decodeAux(allTags,words,new TagList(),0);
		return DSUtils.getBestList(allTags);
	}
	
	private void decodeAux(List<TagList> allTags,List<String> words,TagList tags,int index) {
		if (index>=words.size()) {
			allTags.add(tags);
			return;
		}
		
		List<StringFeature> features=getFeatures(words,tags,index);
		TagList copy;
		
		for(Prediction p:getPredictions(features,index)) {
			copy=new TagList(tags);
			copy.add(p);
			decodeAux(allTags,words,copy,index+1);
		}
	}
	
	protected List<Prediction> getPredictions(List<StringFeature> features,int num) {
		return classifier.predict(features);
	}
}
