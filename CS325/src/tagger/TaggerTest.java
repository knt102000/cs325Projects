package tagger;

import java.util.List;

import org.junit.Test;

import cs325.classifier.NaiveBayes;
import utils.DSUtils;

public class TaggerTest {
	@Test
	public void testGreedyTagger() {
		testTagger(new GreedyTagger	(new NaiveBayes(0.0001)));
		testTagger(new ExhaustiveTagger (new NaiveBayes(0.0001)));
		testTagger(new TopTagger (new NaiveBayes(0.0001)));
		testTagger(new HMMTagger (0.0001));
		testTagger(new TopKTagger (new NaiveBayes(0.0001),3));
		testTagger(new TopKTagger (new NaiveBayes(0.0001),1));
	}
	
	void testTagger(AbstractTagger tagger) {
		List<String> words=DSUtils.createList("John","is","studying","with","Mary");
		List<String> goldTags=DSUtils.createList("noun","aux","verb","prep","noun");
		tagger.addSentence(words,goldTags);
		
		words=DSUtils.createList("Mary","does","not","like","studying");
		goldTags=DSUtils.createList("noun","aux","adv","verb","noun");
		tagger.addSentence(words,goldTags);
		
		words=DSUtils.createList("Susan","is","also","like","Mary");
		goldTags=DSUtils.createList("noun","verb","adv","prep","noun");
		tagger.addSentence(words,goldTags);
		
		tagger.train();
		
		words=DSUtils.createList("John","did","not","like","playing","with","Tom");
		goldTags=DSUtils.createList("noun","aux","adv","verb","verb","prep","noun");
		
		for (TagList sysTags:tagger.decode(words)) {
			printScore(goldTags,sysTags);
		}
	}
	
	void printScore(List<String> goldTags,TagList sysTags) {
		int correct=0;
		int total=goldTags.size();
		
		for (int i=0;i<total;i++) if (goldTags.get(i).equals(sysTags.getTag(i))) correct++;
		
		System.out.printf("%5.2f (%d/%d)\n",100d*correct/total,correct,total);
	}
}
