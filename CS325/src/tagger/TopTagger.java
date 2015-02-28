package tagger;

import java.util.List;

import cs325.classifier.AbstractClassifier;
import cs325.classifier.Prediction;
import cs325.classifier.StringFeature;
import utils.DSUtils;

public class TopTagger extends ExhaustiveTagger {
	public TopTagger(AbstractClassifier classifier) {
		super(classifier);
	}
	
	@Override
	protected List<Prediction> getPredictions(List<StringFeature> features,int num) {
		return DSUtils.getBestList(classifier.predict(features));
	}
}
