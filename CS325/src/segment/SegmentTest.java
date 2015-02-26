package segment;

import java.io.FileInputStream;

import org.junit.Test;

import smoothing.DiscountSmoothing;
import smoothing.NoSmoothing;
import model.Backoff;
import model.ILanguageModel;
import model.lengthBiased;
import ngrams.Bigram;
import ngrams.IOUtils;
import ngrams.Unigram;

public class SegmentTest {
	@Test
	public void test() throws Exception {
		Unigram unigram=new Unigram(new DiscountSmoothing(0.9));
		Bigram bigram=new Bigram(new NoSmoothing());
		
		IOUtils.readUnigrams(unigram,new FileInputStream("1grams.txt"));
		IOUtils.readBigrams(bigram,new FileInputStream("2grams.txt"));
		
		double unigramWeight=0.5;
		double lengthWeight=0.01;
		
		ILanguageModel model1=new lengthBiased(unigram,bigram,lengthWeight);
		ILanguageModel model2=new Backoff(unigram,bigram,unigramWeight);
		
		AbstractSegment segment1=new TsuiSegment(model1);
		AbstractSegment segment2=new TsuiSegment(model2);
		
		System.out.println("Results using own language model:");
		test(segment1,"#20thingsthatdontmix");
		test(segment1,"#10turnons");
		test(segment1,"#90s");
		test(segment1,"#alliwant");
		test(segment1,"#annoyingbios");
		test(segment1,"#aprilfoolsjokes");
//		test(segment1,"#artistseveryonelikesbutidont");
		
		System.out.println("");
		System.out.println("Results using backoff language model:");
		test(segment2,"#20thingsthatdontmix");
		test(segment2,"#10turnons");
		test(segment2,"#90s");
		test(segment2,"#alliwant");
		test(segment2,"#annoyingbios");
		test(segment2,"#aprilfoolsjokes");
//		test(segment2,"#artistseveryonelikesbutidont");
	}
	
	void test(AbstractSegment segment,String s) {
		Sequence sequence=segment.segment(s);
		System.out.println(sequence.getSequence()+" "+sequence.getMaximumLikelihood());
	}
}
