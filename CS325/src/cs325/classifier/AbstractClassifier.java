package cs325.classifier;

import java.util.List;

public abstract class AbstractClassifier {
	public abstract void addInstance(String label,List<StringFeature> features);
	
	public abstract void train();
	
	public abstract List<Prediction> predict(List<StringFeature> features);
}
