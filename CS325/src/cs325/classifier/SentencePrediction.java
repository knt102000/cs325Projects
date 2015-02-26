package cs325.classifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SentencePrediction {

	public static void main(String[] args) {
		double epsilon=0.001;
		List<StringFeature> feat = new ArrayList<StringFeature>();
		NaiveBayes dict=new NaiveBayes(epsilon);
		
		feat.add(new StringFeature("w","John"));
		feat.add(new StringFeature("t","nil"));
		
		dict.addInstance("noun",feat);
	
		feat.clear();
		feat.add(new StringFeature("w","is"));
		feat.add(new StringFeature("t","noun"));
		
		dict.addInstance("aux",feat);

		feat.clear();
		feat.add(new StringFeature("w","studying"));
		feat.add(new StringFeature("t","aux"));
		
		dict.addInstance("verb",feat);
		
		feat.clear();
		feat.add(new StringFeature("w","with"));
		feat.add(new StringFeature("t","verb"));

		dict.addInstance("prep",feat);
		
		feat.clear();
		feat.add(new StringFeature("w","Mary"));
		feat.add(new StringFeature("t","prep"));

		dict.addInstance("noun",feat);
		
		feat.clear();
		feat.add(new StringFeature("w","Mary"));
		feat.add(new StringFeature("t","nil"));

		dict.addInstance("noun", feat);
		
		feat.clear();
		feat.add(new StringFeature("w","does"));
		feat.add(new StringFeature("t","noun"));
		
		dict.addInstance("aux", feat);

		feat.clear();
		feat.add(new StringFeature("w","not"));
		feat.add(new StringFeature("t","aux"));
		
		dict.addInstance("adv", feat);
		
		feat.clear();
		feat.add(new StringFeature("w","like"));
		feat.add(new StringFeature("t","adv"));
		
		dict.addInstance("verb", feat);
		
		feat.clear();
		feat.add(new StringFeature("w","studying"));
		feat.add(new StringFeature("t","verb"));
		
		dict.addInstance("noun", feat);
		
		feat.clear();
		feat.add(new StringFeature("w","Susan"));
		feat.add(new StringFeature("t","nil"));
		
		dict.addInstance("noun", feat);
		
		feat.clear();
		feat.add(new StringFeature("w","is"));
		feat.add(new StringFeature("t","noun"));
		
		dict.addInstance("verb", feat);
		
		feat.clear();
		feat.add(new StringFeature("w","also"));
		feat.add(new StringFeature("t","verb"));
		
		dict.addInstance("adv", feat);
		
		feat.clear();
		feat.add(new StringFeature("w","like"));
		feat.add(new StringFeature("t","adv"));
		
		dict.addInstance("prep", feat);
		
		feat.clear();
		feat.add(new StringFeature("w","Mary"));
		feat.add(new StringFeature("t","prep"));
		
		dict.addInstance("noun", feat);
		
		dict.train();
		
		List<StringFeature> eachWord=new ArrayList<StringFeature>();
		
		String[] sentence={"John","did","not","like","playing","with","Tom"};
		String t_prev="nil";
		
		
		for (String word : sentence) {
			eachWord.add(new StringFeature("w",word));
			eachWord.add(new StringFeature("t",t_prev));
			
			List<Prediction> result=dict.predict(eachWord);
			
			Prediction current=Collections.max(result);
			
			t_prev=current.getLabel();
			
			System.out.println(word+":"+t_prev);
			
			eachWord.clear();
		}

	}

}
