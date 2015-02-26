package cs325.classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import smoothing.NoSmoothing;
import ngrams.Bigram;
import ngrams.Unigram;

public class NaiveBayes extends AbstractClassifier{
	private Unigram prior_table;
	private Map<String,Bigram> features_table;
	private double unseen_prob;
	
	public NaiveBayes(double epsilon) {
		prior_table=new Unigram(new NoSmoothing());
		features_table=new HashMap<>();
		unseen_prob=epsilon;
	}
	
	@Override
	public void addInstance(String label,List<StringFeature> features) {
		prior_table.add(label,1);
		
		for (StringFeature feature:features)
			features_table.computeIfAbsent(feature.getType(),key->new Bigram(new NoSmoothing())).add(label,feature.getValue(),1);
	}
	
	@Override
	public void train() {
		prior_table.estimateMaximumLikelihoods();
		
		for (Bigram m:features_table.values())
			m.estimateMaximumLikelihoods();
	}
	
	@Override
	public List<Prediction> predict(List<StringFeature> features) {
		List<Prediction> predictions=getPriorList();
		double score;
		Bigram map;
		
		for (StringFeature feature:features) {
			map=features_table.get(feature.getType());
			
			for (Prediction prediction:predictions) {
				score=map.getLikelihood(prediction.getLabel(), feature.getValue());
				if (score==0)
					score=unseen_prob;
				prediction.addScore(Math.log(score));
			}
		}
		
		return predictions;
	}
	
	private List<Prediction> getPriorList() {
		List<Prediction> predictions=new ArrayList<>();
		
		for (Entry<String,Double> entry:prior_table.getLikelihoodMap().entrySet())
			predictions.add(new Prediction(entry.getKey(),Math.log(entry.getValue())));
		
		return predictions;
	}
}
