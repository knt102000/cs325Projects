package tagger;

import java.util.ArrayList;
import java.util.List;

import cs325.classifier.AbstractClassifier;
import cs325.classifier.StringFeature;

public abstract class AbstractTagger {
	protected AbstractClassifier classifier;
	
	public AbstractTagger() {}
	
	public AbstractTagger(AbstractClassifier classifier) {
		this.classifier=classifier;
	}
	
	public void addSentence(List<String> words,List<String> tags) {
		TagList list=new TagList(tags);
		List<StringFeature> features;
		int size=words.size();
		
		for (int i=0;i<size;i++) {
			features=getFeatures(words,list,i);
			classifier.addInstance(tags.get(i),features);
		}
	}
	
	public void train() {
		classifier.train();
	}
	
	protected List<StringFeature> getFeatures(List<String> words,TagList tags,int index) {
		List<StringFeature> features=new ArrayList<>();
		String t;
		
		features.add(new StringFeature("f0",words.get(index)));
		
		t=(index-1<0)?null:tags.getTag(index-1);
		features.add(new StringFeature("f1",t));
		
		return features;
	}
	
	public abstract List<TagList> decode(List<String> words);
}
